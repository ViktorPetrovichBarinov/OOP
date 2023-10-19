package ru.nsu.chudinov;

public class Edge<V, E> {
    private double weight;
    private E object;
    private Vertex<V> startingVertex;
    private Vertex<V> endingVertex;

    //При создании ребро не инициализировано
    //И нужно задать ему вес в дальнейшем
    public Edge (){
        this.weight = -1;
        this.object = null;
        this.startingVertex = null;
        this.endingVertex = null;
    }

    public void changeVertexes(Vertex<V> startingVertex, Vertex<V> endingVertex) {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
    }

    public void changeWeight(double newWeight) {
        this.weight = newWeight;
    }

    public void changeObject(E newObject) {
        this.object = newObject;
    }

    public double getWeight() {
        return weight;
    }

    public E getObject() {
        return object;
    }

    public Vertex<V> getStartingVertex() {
        return startingVertex;
    }

    public Vertex<V> getEndingVertex() {
        return endingVertex;
    }
}