package ru.nsu.chudinov;

public abstract class Graph<V, E> {


    public abstract void addVertex (Vertex<V> vertex);

    public abstract void removeVertex (Vertex<V> vertex);

    public abstract void addEdge (Edge<V, E> edge);

    public abstract void removeEdge (Edge<V,E> edge);


}

