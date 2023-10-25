package ru.nsu.chudinov;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    /**
     * Some text.
     *
     * @param args  - Some text.
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Tree<String> tree = new Tree<>("R1");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        Tree<String> a = tree.addChild("A");
        Tree<String> b = a.addChild("B");
        tree.addChild(subtree);
        b.deleteSubTree();
        System.out.println(tree);

        Tree<String> cloneTree = tree.clone();
        System.out.println(tree == cloneTree);

        System.out.println(tree.getChild().get(0) == cloneTree.getChild().get(0));
        System.out.println(tree.getChild().get(0));
        System.out.println(cloneTree.getChild().get(0));

        System.out.println(tree.getChild().get(1) == cloneTree.getChild().get(1));
        System.out.println(tree.getChild().get(1).getChild().get(0) == cloneTree.getChild().get(1).getChild().get(0));
        System.out.println(tree.getChild().get(1).getChild().get(1) == cloneTree.getChild().get(1).getChild().get(1));
    }

}
