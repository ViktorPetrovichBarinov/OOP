package ru.nsu.chudinov;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс реализует дерево с переменным типом. Дерево связаного ациклического ориентированного графа.
 *
 * @param <T>
 */
public class Tree<T> implements Iterable<T>{
    // корневой элемент
    private T root = null;

    public T getRoot() {
        return root;
    }

    //родитель данного дерева/поддерева
    private Tree<T> parent = null;
    //дети даного дерева/поддерева
    private LinkedList<Tree<T>> children = new LinkedList<Tree<T>>();

    //Конструктор дерева, создаёт дерево из дного корневого элемента
    public Tree(T root) {
        this.root = root;
    }



    /**
     * Добавляет поддерево в список детей.
     *
     * @param subTree - поддерево, которое надо добавить
     * @return - вернёт тоже самое поддерево, что и принимал
     */
    public Tree<T> addChild(Tree<T> subTree) {
        subTree.parent = this;
        this.children.add(subTree);
        return subTree;
    }

    /**
     * Добавляет элемент в дерево, предварительно создав из этого элемента дерево из одного элемента.
     *
     * @param root - элемент, который мы хотим добавить в дерево
     * @return - ссылка на плоддерево, которое состоит из одно переданного как аргумент элемента.
     */
    public Tree<T> addChild(T root){
        Tree<T> newTree = new Tree<>(root);
        addChild(newTree);
        return newTree;
    }

    /**
     * удаляет всё поддерево, для котороого применён данный метод
     */
    public void deleteSubTree() {
        //запоминаем родителя
        Tree<T> parent = this.parent;
        //удаляем всех детей
        this.children.clear();
        //если у нас ссылка на родителя 0 -> данный экземпляр это всё дерево
        if(parent != null){
            //иначе нужно удалить ссылку на себя в родительском дереве
            parent.children.remove(this);
        }
    }
    public void deleteThisElem() {
        if (this.children.isEmpty() && this.parent != null) {
            this.parent.children.remove(this);
        }
        if (this.children.size() == 1) {
            this.root = this.children.get(0).root;
            this.children = this.children.get(0).children;
        }
        if (this.children.size() >= 2) {
            this.root = this.children.get(0).root;
            this.children.addAll(this.children.get(0).children);
            this.children.remove(0);
        }
    }













    public void printTree(){
        System.out.print(this.root + ": [ ");
        for (Tree<T> child: this.children) {
            System.out.print(child.root + " ");
        }
        System.out.print("]\n");
        for (Tree<T> child: this.children) {
            child.printTree();
        }
    }

    /*
        Чтобы мы могли использовать например цикл for each для нашего дерева,
        Класс дерева должен реализовывать интерфейс Iterable.
        В методе Iterable у нас нужно реализовать один метод iterator()
        Метод iterator() возвращает объект класса, который реализует интерфейс Iterator
        Iterator требует реализацию двух методов hasNext(), next()
        hasNext() - проверяет, есть ли следующий элемент для итерации
        next() - возвращает следующий элемент
     */
    @Override
    public Iterator<T> iterator() {
        return new BFSIterator();
    }

    private class BFSIterator implements Iterator<T> {
        private Queue<Tree<T>> queue = new LinkedList<Tree<T>>();
        public  BFSIterator() {
            //Tree.this обращается к внешнему экземпляру класса
            queue.add(Tree.this);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            //берём первый элемент из очереди и удаляем его от туда
            Tree<T> current = queue.poll();
            assert current != null;
            queue.addAll(current.children);
            return current.root;
        }
    }
}
