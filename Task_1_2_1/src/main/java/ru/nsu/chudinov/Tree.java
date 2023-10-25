package ru.nsu.chudinov;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;




/**
 * Класс реализует дерево с переменным типом. Дерево связанного ациклического ориентированного
 * графа.
 *
 * @param <T> - Любой тип данных
 */
public class Tree<T> implements Iterable<T>, Cloneable {

    // корневой элемент
    private T root;
    //родитель данного дерева/поддерева
    private Tree<T> parent = null;
    //дети данного дерева/поддерева
    private LinkedList<Tree<T>> children = new LinkedList<>();
    //переменная отвечающая, за количество изменений дерева.
    private int modCount = 0;

    //enum ограниченный набор констант
    //тип данных для представления обхода
    enum TraversalType {
        BFS, DFS
    }

    private TraversalType traversalType = TraversalType.BFS;

    //метод устанавливает метод обхода в ширину
    public void setbfs() {
        this.traversalType = TraversalType.BFS;
    }

    //метод устанавливает метод обхода в глубину
    public void setdfs() {
        this.traversalType = TraversalType.DFS;
    }

    //getter для значения корня
    public T getRoot() {
        return root;
    }

    public int getModCount() {
        return modCount;
    }

    public LinkedList<Tree<T>> getChild() {
        return this.children;
    }

    public TraversalType getTraversalType() {
        return this.traversalType;
    }

    //Конструктор дерева, создаёт дерево из данного корневого элемента
    public Tree(T root) throws NullReferenceError {
        if (root == null) {
            throw new NullReferenceError();
        }
        this.root = root;
    }

    /**
     * Добавляет поддерево в список детей.
     *
     * @param subTree - поддерево, которое надо добавить
     * @return - вернёт то же самое поддерево, что и принимал
     */
    public Tree<T> addChild(Tree<T> subTree) throws NullReferenceError{
        if (subTree == null) {
            throw new NullReferenceError();
        }
        var clonedTree = subTree.clone();

        this.modCount++;

        clonedTree.parent = this;
        this.children.add(clonedTree);
        return clonedTree;
    }

    /**
     * Добавляет элемент в дерево,
     * предварительно создав из этого элемента дерево из одного элемента.
     *
     * @param root - элемент, который мы хотим добавить в дерево
     * @return - ссылка на поддерево, которое состоит из одно переданного как аргумент элемента.
     */
    public Tree<T> addChild(T root) throws NullReferenceError{
        Tree<T> newTree = new Tree<>(root);
        addChild(newTree);
        return newTree;
    }

    /**
     * Удаляет всё поддерево, для которого применён данный метод.
     */
    public void deleteSubTree() {
        plusChanges(this);

        //запоминаем родителя
        Tree<T> parent = this.parent;
        //удаляем всех детей
        this.children.clear();
        //если у нас ссылка на родителя 0 -> данный экземпляр это всё дерево
        if (parent != null) {
            //иначе нужно удалить ссылку на себя в родительском дереве
            parent.children.remove(this);
        }
    }

    /**
     * Удаляет только данный элемент, если у элемента были дети, то один из детей заменяет элемент,
     * который хотим удалить.
     */
    public void deleteThisElem() {
        plusChanges(this);

        //Если у элемента есть родители, но у элемента нет детей
        if (this.children.isEmpty() && this.parent != null) {
            this.parent.children.remove(this);
        }
        //Если у элемента, только один ребёнок,
        //то ребёнок встаёт на место этого элемента.
        if (this.children.size() == 1) {
            this.root = this.children.get(0).root;
            this.children = this.children.get(0).children;
        }
        //Если у элемента, несколько детей,
        //то самый первый ребёнок встаёт на место родителя
        //и дети нового элемента объединяются детьми удалённого элемента
        if (this.children.size() >= 2) {
            //заменяем значение корневого элемента
            this.root = this.children.get(0).root;
            //добавляем к детям корня всех детей первого ребенка
            this.children.addAll(this.children.get(0).children);
            //удаляем из детей сам корневой элемент
            this.children.remove(0);
        }
    }

    private void plusChanges(Tree<T> current) {
        while (current.parent != null) {
            current.modCount++;
            current = current.parent;
        }
        current.modCount++;
    }

    /**
     * Вспомогательная функция, печатает дерево в виде списка смежности.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.root).append(": [ ");
        for (Tree<T> child : this.children) {
            str.append(child.root + " ");
        }
        str.append("]\n");
        for (Tree<T> child : this.children) {
            str.append(child.toString());
        }
        return str.toString();
    }

    /*
        Чтобы мы могли использовать например цикл for each для нашего дерева,
        Класс дерева должен реализовывать интерфейс Iterable.
        В методе Iterable у нас нужно реализовать один метод iterator()
        Метод iterator() возвращает объект класса, который реализует интерфейс Iterator.
        Iterator требует реализацию двух методов hasNext(), next()
        hasNext() - проверяет, есть ли следующий элемент для итерации
        next() - возвращает следующий элемент
     */
    public Iterator<T> iterator() {
        return new TreeIterator(this);
    }




    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        LinkedList<T> fst = new LinkedList<>();
        LinkedList<T> snd = new LinkedList<>();
        if (other instanceof Tree) {
            Tree<T> tree = (Tree<T>) other;
            final TraversalType fstType = this.traversalType;
            final TraversalType sndType = tree.traversalType;
            tree.setbfs();
            this.setbfs();
            for (T child : this) {
                fst.add(child);
            }
            for (T child : tree) {
                snd.add(child);
            }
            if (fstType.equals(TraversalType.DFS)) {
                this.setdfs();
            }
            if (sndType.equals(TraversalType.DFS)) {
                tree.setdfs();
            }
            if (fst.size() != snd.size()) {
                return false;
            }
            for (int i = 0; i < fst.size(); i++) {
                if (!fst.get(i).equals(snd.get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Tree<T> clone(){
        Tree<T> resultTree = null;
        try {
            resultTree = new Tree<>(this.root);
        } catch (NullReferenceError e) {
            throw new RuntimeException(e);
        }
        for(Tree<T> baseTreeChildren : this.children) {
            Tree<T> newChildren = baseTreeChildren.clone();
            resultTree.children.add(newChildren);
        }
        return resultTree;
    };

}

