package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class EdgeTest {

    @Test
    @DisplayName("Проверка конструктора/геттера")
    void test1() {
        Vertex<String> start = new Vertex<>("Start");
        Vertex<String> end = new Vertex<>("End");
        Edge<String> edge = new Edge<>(5, start, end);

        assertEquals(edge.getStartingVertex(), start);
        assertEquals(edge.getEndingVertex(), end);
        assertEquals(edge.getWeight(), 5);
    }

    @Test
    @DisplayName("Проверка сеттеров")
    void test2() {
        Vertex<String> start = new Vertex<>("Start");
        Vertex<String> end = new Vertex<>("End");
        Edge<String> edge = new Edge<>(5, start, end);
        edge.setWeight(10);

        start.setData("Start123");
        edge.setEndingVertex(start);

        end.setData("End123");
        edge.setEndingVertex(end);

        assertEquals(edge.getStartingVertex(), start);
        assertEquals(edge.getEndingVertex(), end);
        assertEquals(edge.getWeight(), 10);
    }

    @Test
    @DisplayName("Проверка equals")
    void test3() {
        Vertex<String> start = new Vertex<>("Start");
        Vertex<String> end = new Vertex<>("End");

        Edge<String> e1 = new Edge<>(5, start, end);
        Edge<String> e2 = new Edge<>(6, end, end);

        assertTrue(e1.equals((Object) e1));
        assertFalse(e1.equals(null));
        assertFalse(e1.equals(e2));

        e2.setWeight(5);
        assertFalse(e1.equals(e2));
        e2.setStartingVertex(start);
        assertTrue(e1.equals(e2));
    }
}
