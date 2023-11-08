package ru.nsu.chudinov;

/**
 * Some text.
 *
 * @param <T> - Some text.
 */
public class ConnectedVertex<T> implements Comparable<ConnectedVertex> {
    protected Vertex<T> vertex;
    protected Integer weight;

    ConnectedVertex(Vertex<T> vertex, Integer weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(ConnectedVertex vertex) {
        return weight - vertex.weight;
    }
}