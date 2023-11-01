package ru.nsu.chudinov;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Graph<T> {
    protected ArrayList<Vertex<T>> vertexList;
    protected ArrayList<Edge<T>> edgeList;


    public abstract Boolean addVertex (Vertex<T> vertex);

    public abstract Boolean removeVertex (Vertex<T> vertex);

    public abstract Boolean changeVertex(Vertex<T> was, Vertex<T> became);

    public abstract Boolean addEdge (Edge<T> edge);

    public abstract Boolean removeEdge (Edge<T> edge);

    public abstract Boolean changeEdge(Edge<T> was, Edge<T> became);




    protected static class ConnectedVertex<T> implements Comparable<ConnectedVertex> {
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
}

