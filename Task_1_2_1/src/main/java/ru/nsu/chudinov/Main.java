package ru.nsu.chudinov;

public class Main {
    public static void main(String[] args) {
        Tree<String> t1 = new Tree<> ("A");
        Tree<String> t2 = new Tree<> ("B");
        Tree<String> t3 = new Tree<> ("C");
        Tree<String> t4 = new Tree<> ("D");
        Tree<String> t5 = new Tree<> ("E");
        Tree<String> t6 = new Tree<>("Right");
        Tree<String> t7 = new Tree<>("AA");
        Tree<String> t8 = new Tree<>("AB");
        Tree<String> t9 = new Tree<>("AC");
        t6.addChild(t7);
        t6.addChild(t9);
        t6.addChild(t8);
        t1.addChild(t2);
        t1.addChild(t6);
        t2.addChild(t3);
        t3.addChild(t5);
        t3.addChild(t4);
        t9.addChild(new Tree<>("AAA"));
        t9.addChild(new Tree<>("BBB"));
        t1.printTree();
        System.out.println("_________________");

        int index = 0;
        t1.setDFS();
        for(String child : t1){
            System.out.println(index + ": " + child);
            index++;
        }

        t2.deleteThisElem();
        t1.printTree();



    }
}

