package ru.nsu.chudinov;

import java.util.ArrayList;

public abstract class Graph<T> {
    private ArrayList<Vertex<T>> vertexList;
    private ArrayList<Edge<T>> edgeList;


    public abstract void addVertex (Vertex<T> vertex);

    public abstract void removeVertex (Vertex<T> vertex);

    public abstract void changeVertex (Vertex<T> vertex);

    public abstract void addEdge (Edge<T> edge);

    public abstract void removeEdge (Edge<T> edge);

    public abstract void changeEdge (Edge<T> edge);


}

