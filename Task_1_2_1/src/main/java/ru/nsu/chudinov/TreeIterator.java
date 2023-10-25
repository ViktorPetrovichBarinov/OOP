package ru.nsu.chudinov;

import java.util.*;

public class TreeIterator<T> implements Iterator<T> {

    //для исключений
    private final int expectedModCount;
    //Очередь для BFS
    private final Queue<Tree<T>> queue = new LinkedList<>();
    //Cтек для DFS
    private final Stack<Tree<T>> stack = new Stack<>();
    private final Tree<T> tree;
    public TreeIterator(Tree<T> tree) {
        this.tree = tree;
        expectedModCount = tree.getModCount();
        if (tree.getTraversalType() == Tree.TraversalType.BFS) {
            //Tree.this обращается к внешнему экземпляру класса
            queue.add(tree);
        } else {
            stack.push(tree);
        }
    }

    public boolean hasNext() {
        if (expectedModCount != tree.getModCount()) {
            throw new ConcurrentModificationException();
        }

        return !queue.isEmpty() || !stack.isEmpty();
    }

    public T next() {
        if (expectedModCount != tree.getModCount()) {
            throw new ConcurrentModificationException();
        }

        Tree<T> current;
        if (tree.getTraversalType() == Tree.TraversalType.BFS) {
            //берём первый элемент из очереди и удаляем его от туда
            current = queue.poll();
            queue.addAll(current.getChild());
        } else {
            //берём первый элемент из очереди и удаляем его от туда
            current = stack.pop();
            stack.addAll(current.getChild());
        }
        return current.getRoot();
    }
}