package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class VertexTest {

    @Test
    @DisplayName("Проверка конструктора")
    void test1() {
        Vertex<Integer> vertex = new Vertex<>(5);

        assertEquals(5, vertex.getData());
    }

    @Test
    @DisplayName("Проверка сеттера на дату")
    void test2() {
        Vertex<Integer> vertex = new Vertex<>(5);
        vertex.setData(15);

        assertEquals(15, vertex.getData());
    }

    @Test
    @DisplayName("Проверка на другой тип")
    void test3() {
        Vertex<String> vertex = new Vertex<>("");
        vertex.setData("Hello");

        assertEquals(vertex.getData(), "Hello");
    }

    @Test
    @DisplayName("Equals тест")
    void test4() {
        Vertex<String> vertex1 = new Vertex<>("Hello");
        Vertex<String> vertex2 = new Vertex<>("Hello");
        assertTrue(vertex1.equals((Object) vertex1));
        assertFalse(vertex1.equals(null));

        assertTrue(vertex1.equals(vertex2));
    }
}
