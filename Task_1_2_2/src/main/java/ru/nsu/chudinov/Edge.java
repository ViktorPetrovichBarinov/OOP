package ru.nsu.chudinov;

public class Edge<V> {
    private Integer weight;
    private Vertex<V> startingVertex;
    private Vertex<V> endingVertex;

    //При создании ребро не инициализировано
    //и нужно задать ему вес в дальнейшем
    public Edge (Integer weight, Vertex<V> start, Vertex<V> end){
        this.weight = weight;
        this.startingVertex = start;
        this.endingVertex = end;
    }

    public void setWeight(Integer newWeight) {
        this.weight = newWeight;
    }

    public void setStartingVertex(Vertex<V> startingVertex) {
        this.startingVertex = startingVertex;
    }

    public void setEndingVertex(Vertex<V> endingVertex) {
        this.endingVertex = endingVertex;
    }

    public Integer getWeight() {
        return weight;
    }

    public Vertex<V> getStartingVertex() {
        return startingVertex;
    }

    public Vertex<V> getEndingVertex() {
        return endingVertex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Проверка на ссылку
        if (obj == null || getClass() != obj.getClass()) return false;  // Проверка на null и класс

        Edge<?> edge = (Edge<?>) obj;  // Приведение объекта к типу Edge

        if (!weight.equals(edge.weight)) return false;  // weight
        if (!startingVertex.equals(edge.startingVertex)) return false;  // startingVertex
        return endingVertex.equals(edge.endingVertex);  // endingVertex
    }

}