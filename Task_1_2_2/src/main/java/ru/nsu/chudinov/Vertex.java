package ru.nsu.chudinov

public class Vertex<V> {
    private V data;

    public Vertex<V> {
        this.data = null;
    }

    public V getData {
        return this.data;
    }

    public void changeData(V newData) {
        this.data = newData;
    }
}
