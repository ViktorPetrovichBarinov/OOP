package ru.nsu.chudinov;

import java.util.ArrayList;

public abstract class Graph<T> {
    protected ArrayList<Vertex<T>> vertexList;
    protected ArrayList<Edge<T>> edgeList;


    public abstract Boolean addVertex (Vertex<T> vertex);

    public abstract Boolean removeVertex (Vertex<T> vertex);

    public abstract Boolean changeVertex(Vertex<T> was, Vertex<T> became);

    public abstract Boolean addEdge (Edge<T> edge);

    public abstract Boolean removeEdge (Edge<T> edge);

    public abstract Boolean changeEdge(Edge<T> was, Edge<T> became);
}

