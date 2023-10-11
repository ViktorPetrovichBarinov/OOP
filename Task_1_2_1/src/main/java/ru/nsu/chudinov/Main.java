package ru.nsu.chudinov;

public class Main {
    public static void main(String[] args) {
        Tree<String> t1 = new Tree<> ("A");
        Tree<String> t2 = new Tree<> ("B");
        Tree<String> t3 = new Tree<> ("C");
        Tree<String> t4 = new Tree<> ("D");
        Tree<String> t5 = new Tree<> ("E");
        t1.addChild(t2);
        t2.addChild(t3);
        t2.addChild(t5);
        t3.addChild(t4);
        t1.printTree();
        System.out.println("_________________");

        for(String child : t1) {
            System.out.println(child);
        }

        t2.deleteThisElem();
        t1.printTree();



    }
}

