package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VertexTest {

    @Test
    @DisplayName("Empty vertex")
    void test1() {
        Vertex<Integer> vertex = new Vertex<>();

        assertNull(vertex.getData());
    }

    @Test
    @DisplayName("Integer vertex")
    void test2() {
        Vertex<Integer> vertex = new Vertex<>();
        vertex.changeData(15);

        assertEquals(15, vertex.getData());
    }

    @Test
    @DisplayName("String vertex")
    void test3() {
        Vertex<String> vertex = new Vertex<>();
        vertex.changeData("Hello");

        assertEquals(vertex.getData(), "Hello");
    }
}
