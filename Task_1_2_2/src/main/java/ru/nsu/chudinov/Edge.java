package ru.nsu.chudinov;

public class Edge<V> {
    private double weight;
    private Vertex<V> startingVertex;
    private Vertex<V> endingVertex;

    //При создании ребро не инициализировано
    //И нужно задать ему вес в дальнейшем
    public Edge (int weight, Vertex<V> start, Vertex<V> end){
        this.weight = weight;
        this.startingVertex = start;
        this.endingVertex = end;
    }

    public void changeVertexes(Vertex<V> startingVertex, Vertex<V> endingVertex) {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
    }




    public void setWeight(double newWeight) {
        this.weight = newWeight;
    }

    public void setStartingVertex(Vertex<V> startingVertex) {
        this.startingVertex = startingVertex;
    }

    public void setEndingVertex(Vertex<V> endingVertex) {
        this.endingVertex = endingVertex;
    }

    public double getWeight() {
        return weight;
    }

    public Vertex<V> getStartingVertex() {
        return startingVertex;
    }

    public Vertex<V> getEndingVertex() {
        return endingVertex;
    }
}