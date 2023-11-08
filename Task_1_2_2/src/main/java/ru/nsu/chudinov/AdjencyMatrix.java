package ru.nsu.chudinov;

import java.util.ArrayList;

/**
 * Some text.
 * @param <T> - Some text.
 */
public class AdjencyMatrix<T> extends Graph<T> {
    private final ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private Integer size;

    /**
     * Some text.
     *
     * @param vertexArrayList - Some text.
     * @param edgeArrayList - Some text.
     */
    public AdjencyMatrix(ArrayList<Vertex<T>> vertexArrayList, ArrayList<Edge<T>> edgeArrayList) {
        this.vertexList = vertexArrayList;
        this.edgeList = edgeArrayList;
        this.size = vertexList.size();

        for (int i = 0; i < vertexList.size(); i++) {
            ArrayList<Integer> current = new ArrayList<>();
            for (int j = 0; j < vertexList.size(); j++) {
                current.add(0);
            }
            graph.add(current);
        }

        for (int i = 0; i < edgeList.size(); i++) {
            int row = vertexList.indexOf(edgeList.get(i).getStartingVertex());
            int column = vertexList.indexOf(edgeList.get(i).getEndingVertex());
            graph.get(row).set(column, edgeList.get(i).getWeight());
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (vertexList.contains(vertex)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> addList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            addList.add(0);
        }
        graph.add(addList);
        size++;
        for (int i = 0; i < size; i++) {
            graph.get(i).add(0);
        }
        vertexList.add(vertex);
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        if (!vertexList.contains(vertex)) {
            throw new IllegalArgumentException();
        }
        int index = vertexList.indexOf(vertex);
        for (int i = 0; i < size; i++) {
            graph.get(i).remove(index);
        }
        graph.remove(index);
        vertexList.remove(vertex);
        for (int i = 0; i < edgeList.size(); i++) {
            Edge<T> currentEdge = edgeList.get(i);
            if (currentEdge.getStartingVertex().equals(vertex)
                    || currentEdge.getEndingVertex().equals(vertex)) {
                edgeList.remove(i);
                i--;
            }
        }
        size--;
    }

    @Override
    public void changeVertex(Vertex<T> was, Vertex<T> became) {
        if (!vertexList.contains(was) || vertexList.contains(became)) {
            throw new IllegalArgumentException();
        }
        int index = vertexList.indexOf(was);
        vertexList.set(index, became);
        for (int i = 0; i < edgeList.size(); i++) {
            Edge<T> currentEdge = edgeList.get(i);
            if (currentEdge.getStartingVertex().equals(was)) {
                currentEdge.setStartingVertex(became);
            }
            if (currentEdge.getEndingVertex().equals(was)) {
                currentEdge.setEndingVertex(became);
            }
        }
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!vertexList.contains(edge.getStartingVertex())
                || !vertexList.contains(edge.getEndingVertex())) {
            throw new IllegalArgumentException();
        }
        graph.get(vertexList.indexOf(edge.getStartingVertex()))
                .set(vertexList.indexOf(edge.getEndingVertex()), edge.getWeight());
        edgeList.add(edge);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if (!edgeList.contains(edge)) {
            throw new IllegalArgumentException();
        }
        graph.get(vertexList.indexOf(edge.getStartingVertex()))
                .set(vertexList.indexOf(edge.getEndingVertex()), 0);
        edgeList.remove(edge);
    }

    @Override
    public void changeEdge(Edge<T> was, Edge<T> became) {
        if (!was.getStartingVertex().equals(became.getStartingVertex())
                || !was.getEndingVertex().equals(became.getEndingVertex())
                || !edgeList.contains(was)) {
            throw new IllegalArgumentException();
        }

        graph.get(vertexList.indexOf(was.getStartingVertex()))
                .set(vertexList.indexOf(was.getEndingVertex()), became.getWeight());
        int index = edgeList.indexOf(was);
        edgeList.set(index, became);
    }
}
