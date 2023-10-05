package ru.nsu.chudinov;

public class Main {
    public static void main(String[] args) {
        Tree<String> t1 = new Tree<> ("A");
        t1.addChild(new String("B"));
        t1.addChild(new String("C"));
        Tree<String> t2 = new Tree<> ("D");
        t2.addChild(new String("E"));
        t1.addChild(t2);

        t1.printTree();
        System.out.println("_________________");

        t1.deleteSubTree();
        t1.printTree();



    }
}

