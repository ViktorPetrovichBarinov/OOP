package ru.nsu.chudinov;

public class Main {

    /**
     * Some text.
     *
     * @param args  - Some text.
     */
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        Tree<String> a = tree.addChild("A");
        Tree<String> b = a.addChild("B");
        tree.addChild(subtree);
        b.deleteSubTree();
        System.out.println(tree);
    }

}
