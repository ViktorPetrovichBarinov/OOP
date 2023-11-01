package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
