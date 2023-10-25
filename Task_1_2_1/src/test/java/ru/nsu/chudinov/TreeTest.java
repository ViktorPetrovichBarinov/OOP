package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Класс для тестов класса Tree.
 */
public class TreeTest {
    @Test
    @DisplayName("Create one element tree(String)")
    void test1() {
        Tree<String> t1 = new Tree<>("String");

        assertEquals(t1.getRoot(), "String");
    }

    @Test
    @DisplayName("Create one element tree(Integer)")
    void test2() {
        Tree<Integer> t1 = new Tree<>(15);

        assertEquals(t1.getRoot(), 15);
    }


    @Test
    @DisplayName("Add Integer child with T root")
    void test3() throws NullReferenceError {
        Tree<Integer> t1 = new Tree<>(15);
        t1.addChild(14);
        assertEquals(t1.getRoot(), 15);
        assertEquals(t1.getChild().get(0).getRoot(), 14);
    }

    @Test
    @DisplayName("Add Integer child with Tree<T> root")
    void test4() throws NullReferenceError{
        Tree<Integer> t1 = new Tree<>(15);
        Tree<Integer> t2 = new Tree<>(14);
        t1.addChild(t2);
        assertEquals(t1.getRoot(), 15);
        assertEquals(t1.getChild().get(0).getRoot(), 14);
    }

    @Test
    @DisplayName("Add Integer child null")
    void test5() throws NullReferenceError{
        Tree<Integer> t1 = new Tree<>(15);
        Tree<Integer> t2 = new Tree<>(null);
        t1.addChild(t2);
        assertEquals(t1.getRoot(), 15);
        assertNull(t1.getChild().get(0).getRoot());
    }


    /**
     * СОЗДАЁТ ДЕРЕВО:
     * LVL0(ROOT)             root
     *                      /  |   \
     *                   /     |      \
     *                /        |         \
     *             /           |            \
     * LVL1      1             2             3
     *          /|\           /|\           /|\
     *         / | \         / | \         / | \
     *        /  |  \       /  |  \       /  |  \
     * LVL2 1_1 1_2 1_3   2_1 2_2 2_3   3_1 3_2 3_3
     *
     * @return - ссылку на root.
     */
    @SuppressWarnings("java:S100")
    private Tree<String> createTree() throws NullReferenceError{
        Tree<String> root = new Tree<>("Root");

        Tree<String> lvl1_1 = new Tree<>("lvl1_1");
        Tree<String> lvl1_2 = new Tree<>("lvl1_2");
        Tree<String> lvl1_3 = new Tree<>("lvl1_3");
        root.addChild(lvl1_1);
        root.addChild(lvl1_2);
        root.addChild(lvl1_3);

        Tree<String> lvl2_1_1 = new Tree<>("lvl2_1_1");
        Tree<String> lvl2_1_2 = new Tree<>("lvl2_1_2");
        Tree<String> lvl2_1_3 = new Tree<>("lvl2_1_3");
        lvl1_1.addChild(lvl2_1_1);
        lvl1_1.addChild(lvl2_1_2);
        lvl1_1.addChild(lvl2_1_3);

        Tree<String> lvl2_2_1 = new Tree<>("lvl2_2_1");
        Tree<String> lvl2_2_2 = new Tree<>("lvl2_2_2");
        Tree<String> lvl2_2_3 = new Tree<>("lvl2_2_3");
        lvl1_2.addChild(lvl2_2_1);
        lvl1_2.addChild(lvl2_2_2);
        lvl1_2.addChild(lvl2_2_3);

        Tree<String> lvl2_3_1 = new Tree<>("lvl2_3_1");
        Tree<String> lvl2_3_2 = new Tree<>("lvl2_3_2");
        Tree<String> lvl2_3_3 = new Tree<>("lvl2_3_3");
        lvl1_3.addChild(lvl2_3_1);
        lvl1_3.addChild(lvl2_3_2);
        lvl1_3.addChild(lvl2_3_3);

        return root;
    }


    @Test
    @DisplayName("Equal of identical tree")
    void test6() throws NullReferenceError{
        Tree<String> root1 = createTree();
        Tree<String> root2 = createTree();
        assertTrue(root1.equals(root2));
    }

    @Test
    @DisplayName("Equal of identical tree but one is OBJECT")
    void test7() throws NullReferenceError{
        Tree<String> root1 = createTree();
        Object root2 = createTree();
        assertTrue(root1.equals(root2));
    }

    @Test
    @DisplayName("Equal of not identical tree")
    void test8() throws NullReferenceError{
        Tree<String> root1 = createTree();
        Tree<String> root2 = new Tree<>("Str1");
        assertFalse(root1.equals(root2));
    }

    @Test
    @DisplayName("Equal different objects")
    void test9() throws NullReferenceError{
        Tree<String> root1 = createTree();
        String root2 = new String("Str1");
        assertFalse(root1.equals(root2));
    }

    @Test
    @DisplayName("Equal different objects")
    void test10()throws NullReferenceError {
        Tree<String> root1 = createTree();
        Tree<String> root2 = createTree();
        root1.setdfs();
        root2.setdfs();
        assertTrue(root1.equals(root2));

    }

    @Test
    @DisplayName("Delete SubTree LVL1 1-st elem 2-nd elem and 3-rd elem")
    void test11() throws NullReferenceError{
        Tree<String> root1 = createTree();
        root1.getChild().get(0).deleteSubTree();
        root1.getChild().get(0).deleteSubTree();
        root1.getChild().get(0).deleteSubTree();
        Tree<String> root2 = new Tree<>("Root");
        assertTrue(root1.equals(root2));
    }

    @Test
    @DisplayName("Delete only one elem LVL1 1-st elem 2-nd elem and 3-rd elem")
    @SuppressWarnings("java:S100")
    void test12() throws NullReferenceError{
        Tree<String> root1 = createTree();
        root1.getChild().get(0).deleteThisElem();
        root1.getChild().get(1).deleteThisElem();
        root1.getChild().get(2).deleteThisElem();
        Tree<String> root = new Tree<>("Root");

        Tree<String> lvl2_1_1 = new Tree<>("lvl2_1_1");
        Tree<String> lvl2_1_2 = new Tree<>("lvl2_1_2");
        Tree<String> lvl2_1_3 = new Tree<>("lvl2_1_3");
        root.addChild(lvl2_1_1);
        lvl2_1_1.addChild(lvl2_1_2);
        lvl2_1_1.addChild(lvl2_1_3);

        Tree<String> lvl2_2_1 = new Tree<>("lvl2_2_1");
        Tree<String> lvl2_2_2 = new Tree<>("lvl2_2_2");
        Tree<String> lvl2_2_3 = new Tree<>("lvl2_2_3");
        root.addChild(lvl2_2_1);
        lvl2_2_1.addChild(lvl2_2_2);
        lvl2_2_1.addChild(lvl2_2_3);

        Tree<String> lvl2_3_1 = new Tree<>("lvl2_3_1");
        Tree<String> lvl2_3_2 = new Tree<>("lvl2_3_2");
        Tree<String> lvl2_3_3 = new Tree<>("lvl2_3_3");
        root.addChild(lvl2_3_1);
        lvl2_3_1.addChild(lvl2_3_2);
        lvl2_3_1.addChild(lvl2_3_3);

        assertTrue(root.equals(root1));
    }

    @Test
    @DisplayName("Delete one elem without child")
    void test13() throws NullReferenceError{
        var root = new Tree<>(15);
        var child = new Tree<>(10);
        root.addChild(child);
        child.deleteSubTree();
        assertEquals(0, root.getChild().size());
    }

    @Test
    @DisplayName("Delete one elem with one child")
    void test14() throws NullReferenceError {
        var root = new Tree<>(15);
        var child = new Tree<>(10);
        var childOfChild = new Tree<>(5);
        root.addChild(child);
        child.addChild(childOfChild);
        child.deleteThisElem();
        assertEquals(5, root.getChild().get(0).getRoot());
        assertEquals(1, root.getChild().size());
        assertEquals(0, child.getChild().size());
    }

    @Test
    @DisplayName("print test")
    void test15() throws NullReferenceError{


        // Сохраняем текущий стандартный поток вывода
        final PrintStream originalOut = System.out;
        // Создаем новый поток вывода
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream);
        // Перенаправляем стандартный поток вывода
        System.setOut(newOut);


        Tree<String> tree = new Tree<>("R1");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        Tree<String> a = tree.addChild("A");
        Tree<String> b = a.addChild("B");
        tree.addChild(subtree);
        b.deleteSubTree();
        System.out.println(tree);

        // Возвращаем стандартный поток вывода в исходное состояние
        System.setOut(originalOut);
        String expectedOutput = "R1: [ A R2 ]\n"
                + "A: [ ]\n"
                + "R2: [ C D ]\n"
                + "C: [ ]\n"
                + "D: [ ]"; // Замените на ожидаемый вывод
        assertEquals(expectedOutput, outputStream.toString().trim());
    }
}
