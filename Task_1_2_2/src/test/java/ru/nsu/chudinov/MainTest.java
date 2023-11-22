package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class MainTest {

    @Test
    void adjencyMatrixTest() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        Graph.readGraphFromFile(vertices, edges, "test.txt");

        AdjencyMatrix<String> graph = new AdjencyMatrix<>(vertices, edges);
        graph.addVertex(new Vertex<>("E"));
        graph.addEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("E")));
        graph.removeEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("B")));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("E")),
                new Edge<>(11, new Vertex<>("A"), new Vertex<>("E")));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        ArrayList<Vertex<String>> answer = new ArrayList<>();
        answer.add(new Vertex<>("A"));
        answer.add(new Vertex<>("E"));
        answer.add(new Vertex<>("D"));
        answer.add(new Vertex<>("B"));
        ArrayList<Vertex<String>> result = graph.shortestPath(new Vertex<>("A"));
        assertArrayEquals(result.toArray(), answer.toArray());
    }

    @Test
    void adjencyListTest() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        Graph.readGraphFromFile(vertices, edges, "test.txt");

        AdjencyList<String> graph = new AdjencyList<>(vertices, edges);
        graph.addVertex(new Vertex<>("E"));
        graph.addEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("E")));
        graph.removeEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("B")));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("E")),
                new Edge<>(11, new Vertex<>("A"), new Vertex<>("E")));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));


        ArrayList<Vertex<String>> answer = new ArrayList<>();
        answer.add(new Vertex<>("A"));
        answer.add(new Vertex<>("E"));
        answer.add(new Vertex<>("D"));
        answer.add(new Vertex<>("B"));
        ArrayList<Vertex<String>> result = graph.shortestPath(new Vertex<>("A"));
        assertArrayEquals(result.toArray(), answer.toArray());
    }

    @Test
    void incedenceMatrix() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        Graph.readGraphFromFile(vertices, edges, "test.txt");

        IncidenceMatrix<String> graph = new IncidenceMatrix<>(vertices, edges);
        graph.addVertex(new Vertex<>("E"));
        graph.addEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("E")));
        graph.removeEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("B")));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(10, new Vertex<>("A"), new Vertex<>("E")),
                new Edge<>(11, new Vertex<>("A"), new Vertex<>("E")));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));


        ArrayList<Vertex<String>> answer = new ArrayList<>();
        answer.add(new Vertex<>("A"));
        answer.add(new Vertex<>("E"));
        answer.add(new Vertex<>("D"));
        answer.add(new Vertex<>("B"));
        ArrayList<Vertex<String>> result = graph.shortestPath(new Vertex<>("A"));
        assertArrayEquals(result.toArray(), answer.toArray());
    }

}
