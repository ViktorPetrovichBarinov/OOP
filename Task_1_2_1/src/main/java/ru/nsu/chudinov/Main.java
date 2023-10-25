package ru.nsu.chudinov;

/**
 * Some text.
 */
public class Main {

    /**
     * Some text.
     *
     * @param args  - Some text.
     */
    public static void main(String[] args) throws CloneNotSupportedException, NullReferenceError {
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
