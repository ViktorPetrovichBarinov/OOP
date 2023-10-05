package ru.nsu.chudinov;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс для теста деревьев.
 */
public class TreeTest {

    @Test
    void generateTree(){
        Tree<String> t1 = new Tree<>("asd");
        assertEquals("asd", t1.getRoot());
    }
}
