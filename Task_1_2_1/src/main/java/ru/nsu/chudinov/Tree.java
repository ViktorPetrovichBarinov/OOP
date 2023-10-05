package ru.nsu.chudinov;

import java.util.LinkedList;

/**
 * Класс реализует дерево с переменным типом. Дерево связаного ациклического ориентированного графа.
 *
 * @param <T>
 */
public class Tree<T>{
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
     * Добавляет поддерево.
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
        Tree<T> newTree = new Tree(root);
        addChild(newTree);
        return newTree;
    }

    /**
     * удаляет всё поддерево, для котороого применён данный метод
     *
     * @return
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
        if (this.children.size() == 0 && this.parent != null) {
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
    };


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
}
