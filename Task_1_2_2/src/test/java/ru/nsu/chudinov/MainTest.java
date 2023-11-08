package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Some text.
 */
public class MainTest {

    @Test
    void adjencyMatrixTest() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        try {
            File file = new File("test.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int numberOfVertex = Integer.parseInt(bufferedReader.readLine());
            //добавляем вершины
            for (int i = 0; i < numberOfVertex; i++) {
                String currentString = bufferedReader.readLine();
                vertices.add(new Vertex<>(currentString));
            }
            int numberOfEdge = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < numberOfEdge; i++) {
                String[] currentSplitArray = bufferedReader.readLine().split(" ");
                edges.add(new Edge<>(Integer.parseInt(currentSplitArray[2]),
                        new Vertex<>(currentSplitArray[0]), new Vertex<>(currentSplitArray[1])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
        try {
            File file = new File("test.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int numberOfVertex = Integer.parseInt(bufferedReader.readLine());
            //добавляем вершины
            for (int i = 0; i < numberOfVertex; i++) {
                String currentString = bufferedReader.readLine();
                vertices.add(new Vertex<>(currentString));
            }
            int numberOfEdge = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < numberOfEdge; i++) {
                String[] currentSplitArray = bufferedReader.readLine().split(" ");
                edges.add(new Edge<>(Integer.parseInt(currentSplitArray[2]),
                        new Vertex<>(currentSplitArray[0]), new Vertex<>(currentSplitArray[1])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
        try {
            File file = new File("test.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int numberOfVertex = Integer.parseInt(bufferedReader.readLine());
            //добавляем вершины
            for (int i = 0; i < numberOfVertex; i++) {
                String currentString = bufferedReader.readLine();
                vertices.add(new Vertex<>(currentString));
            }
            int numberOfEdge = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < numberOfEdge; i++) {
                String[] currentSplitArray = bufferedReader.readLine().split(" ");
                edges.add(new Edge<>(Integer.parseInt(currentSplitArray[2]),
                        new Vertex<>(currentSplitArray[0]), new Vertex<>(currentSplitArray[1])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
