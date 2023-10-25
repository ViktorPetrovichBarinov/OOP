package ru.nsu.chudinov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Класс для тестов класса Tree.
 */
public class TreeTest {
    @Test
    @DisplayName("BFS setter")
    void test1() throws NullReferenceError {
        Tree<String> t1 = new Tree<>("111");

        t1.setbfs();

        assertEquals(t1.getSearchType(), Tree.TraversalType.BFS);
    }

    @Test
    @DisplayName("DFS setter")
    void test2() throws NullReferenceError {
        Tree<String> t1 = new Tree<>("111");

        t1.setdfs();

        assertEquals(t1.getSearchType(), Tree.TraversalType.DFS);
    }

    @Test
    @DisplayName("root getter")
    void test3() throws NullReferenceError {
        String ans = "111";
        Tree<String> t1 = new Tree<>(ans);

        assertEquals(ans, t1.getRoot());
    }

    @Test
    @DisplayName("modCount getter")
    void test4() throws NullReferenceError {
        Tree<String> t1 = new Tree<>("111");

        assertEquals(0, t1.getModCount());
    }

    @Test
    @DisplayName("modCount getter")
    void test5() throws NullReferenceError {
        Tree<String> t1 = new Tree<>("111");
        Tree<String> t2 = new Tree<>("222");
        t1 = t1.addChild(t2);

        t2.deleteThisElem();
        assertEquals(1, t1.getModCount());
    }
    @Test
    @DisplayName("argument is null")
    public void test6() {
        try {
            new Tree<>(null);
        } catch (NullReferenceError e) {
            assertEquals("incorrect argument", e.getMessage());
        }
    }

    @Test
    @DisplayName("argument isn't null")
    public void test7() throws NullReferenceError {
        Tree<String> tree = new Tree<>("Root");
        assertNotNull(tree);
    }


    @Test
    @DisplayName("argument isn't null")
    public void test8() throws NullReferenceError {
        Tree<String> tree = new Tree<>("Root");
        Tree<String> subtree = new Tree<>("Sub element");
        tree = tree.addChild(subtree);
        assertEquals(tree.toString(), "Root: [ Sub element ]\nSub element: [ ]\n");
    }

    @Test
    @DisplayName("Child test")
    public void test9() throws NullReferenceError {
        //создаю дерево с одним подэлементом
        Tree<String> tree = new Tree<>("Root");
        Tree<String> subtree = new Tree<>("Sub element");
        tree = tree.addChild(subtree);
        //несмотря на то что ссылка ана элемент который добовляли сохранилась,
        //после удаления элемента, дерево куда добавляли элементы не пострадало
        subtree.deleteThisElem();
        assertEquals(tree.toString(), "Root: [ Sub element ]\nSub element: [ ]\n");
    }

    @Test
    @DisplayName("Child test")
    public void test10() throws NullReferenceError {
        Tree<String> tree = new Tree<>("Root");
        Tree<String> subtree = new Tree<>("Sub element");
        tree = tree.addChild(subtree);

        tree.deleteSubTree();
        assertEquals(tree.toString(), "Root: [ ]\n");
    }

    @Test
    @DisplayName("Child test")
    public void test11() throws NullReferenceError {
        Tree<String> tree = new Tree<>("Root");
        Tree<String> subtree = new Tree<>("Sub element");
        Tree<String> subSubTree = new Tree<>("SSElement");
        subtree = subtree.addChild(subSubTree);
        tree = tree.addChild(subtree);

        tree.deleteThisElem();
        assertEquals(tree.toString(), "Sub element: [ SSElement ]\nSSElement: [ ]\n");
    }

    @Test
    @DisplayName("BFS")
    public void test12() throws NullReferenceError {
        Tree<Integer> lvl2Left = new Tree<>(5);
        lvl2Left.addChild(4);
        lvl2Left.addChild(3);
        Tree<Integer> lvl2Right = new Tree<>(8);
        lvl2Right.addChild(7);
        lvl2Right.addChild(6);
        Tree<Integer> tree = new Tree<>(0);
        tree.addChild(lvl2Left);
        tree.addChild(lvl2Right);
        ArrayList<Integer> resultOfBFS = new ArrayList<>();
        for (Integer child : tree) {
            resultOfBFS.add(child);
        }
        assertEquals(resultOfBFS.toString(), "[0, 5, 8, 4, 3, 7, 6]");
    }

    @Test
    @DisplayName("DFS")
    public void test13() throws NullReferenceError {
        Tree<Integer> lvl2Left = new Tree<>(5);
        lvl2Left.addChild(4);
        lvl2Left.addChild(3);
        Tree<Integer> lvl2Right = new Tree<>(8);
        lvl2Right.addChild(7);
        lvl2Right.addChild(6);
        Tree<Integer> tree = new Tree<>(0);
        tree.addChild(lvl2Left);
        tree.addChild(lvl2Right);
        tree.setdfs();
        ArrayList<Integer> resultOfBFS = new ArrayList<>();
        for (Integer child : tree) {
            resultOfBFS.add(child);
        }
        assertEquals(resultOfBFS.toString(), "[0, 8, 6, 7, 5, 3, 4]");
    }

    @Test
    @DisplayName("equals")
    public void test14() throws NullReferenceError {
        Tree<Integer> lvl2Left = new Tree<>(5);
        lvl2Left.addChild(4);
        lvl2Left.addChild(3);
        Tree<Integer> lvl2Right = new Tree<>(8);
        lvl2Right.addChild(7);
        lvl2Right.addChild(6);
        Tree<Integer> tree1 = new Tree<>(0);
        tree1.addChild(lvl2Left);
        tree1.addChild(lvl2Right);
        tree1.setdfs();

        lvl2Left = new Tree<>(5);
        lvl2Left.addChild(4);
        lvl2Left.addChild(3);
        lvl2Right = new Tree<>(8);
        lvl2Right.addChild(7);
        lvl2Right.addChild(6);
        Tree<Integer> tree2 = new Tree<>(0);
        tree2.addChild(lvl2Left);
        tree2.addChild(lvl2Right);
        tree2.setdfs();

        assertTrue(tree1.equals(tree2));
    }
}
