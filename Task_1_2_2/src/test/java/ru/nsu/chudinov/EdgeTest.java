package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EdgeTest {
    @Test
    @DisplayName("Empty edge")
    void test1() {
        Edge<Integer, String> edge = new Edge<>();
        assertNull(edge.getObject());
        assertNull(edge.getStartingVertex());
        assertNull(edge.getEndingVertex());
    }

    @Test
    @DisplayName("Vertex getter")
    void test2() {
        Edge<Integer, String> edge = new Edge<>();
        edge.changeVertexes(new Vertex<Integer>().changeData(10),
                new Vertex<Integer>().changeData(100));

        assertEquals(edge.getStartingVertex().getData(), 10);
        assertEquals(edge.getEndingVertex().getData(), 100);
    }

    @Test
    @DisplayName("Weight getter")
    void test3() {
        Edge<Integer, String> edge = new Edge<>();
        edge.changeWeight(100);

        assertEquals(100, edge.getWeight());
    }

    @Test
    @DisplayName("Object getter")
    void test4() {
        Edge<Integer, String> edge = new Edge<>();
        edge.changeObject("100");

        assertEquals("100", edge.getObject());
    }
}
