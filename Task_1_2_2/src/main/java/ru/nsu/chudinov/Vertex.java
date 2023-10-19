package ru.nsu.chudinov;

public class Vertex<V> {
    private V data;

    public Vertex() {
        this.data = null;
    }

    public V getData() {
        return this.data;
    }

    public Vertex<V> changeData(V newData) {
        this.data = newData;
        return this;
    }
}
