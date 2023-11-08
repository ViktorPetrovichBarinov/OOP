package ru.nsu.chudinov;

import java.util.Objects;

/**
 * Some text.
 *
 * @param <V> - Some text.
 */
public class Vertex<V> {
    private V data;

    public Vertex(V data) {
        this.data = data;
    }

    public V getData() {
        return this.data;
    }

    public void setData(V newData) {
        this.data = newData;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Проверка на ссылку
        }
        if (obj == null || getClass() != obj.getClass()) { // Проверка на null и класс
            return false;
        }

        Vertex<?> vertex = (Vertex<?>) obj;  // Приведение объекта к типу Vertex

        return Objects.equals(data, vertex.data);  // Сравнение полей data
    }

}
