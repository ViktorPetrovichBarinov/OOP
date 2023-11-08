package ru.nsu.chudinov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Graph<T> {
    protected ArrayList<Vertex<T>> vertexList;
    protected ArrayList<Edge<T>> edgeList;


    public abstract void addVertex (Vertex<T> vertex);

    public abstract void removeVertex (Vertex<T> vertex);

    public abstract void changeVertex(Vertex<T> was, Vertex<T> became);

    public abstract void addEdge (Edge<T> edge);

    public abstract void removeEdge (Edge<T> edge);

    public abstract void changeEdge(Edge<T> was, Edge<T> became);

    public ArrayList<Vertex<T>> shortestPath(Vertex<T> vertex) {
        int vertexLength = vertexList.size();
        int[] distance = new int[vertexLength];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[vertexList.indexOf(vertex)] = 0;
        boolean[] mark = new boolean[vertexLength];
        Arrays.fill(mark, false);

        for (int i = 0; i < vertexLength; i++) {
            int shortest = -1;
            for (int j = 0; j < vertexLength; j++) {
                if (!mark[j] && (shortest == -1 || distance[shortest] > distance[j])) {
                    shortest = j;
                }
            }

            if (distance[shortest] == Integer.MAX_VALUE) {
                break;
            }
            mark[shortest] = true;

            ArrayList<Edge<T>> adjacentEdge = getEdge(this.vertexList.get(shortest));
            int edgeLen = adjacentEdge.size();
            for (int j = 0; j < edgeLen; j++) {
                Edge<T> cur = adjacentEdge.get(j);
                if (distance[shortest] + cur.getWeight()
                        < distance[this.vertexList.indexOf(cur.getEndingVertex())]) {
                    distance[this.vertexList.indexOf(cur.getEndingVertex())]
                            = distance[shortest] + cur.getWeight();
                }
            }
        }

        ArrayList<ConnectedVertex<T>> sortArray = new ArrayList<>();
        for (int i = 0; i < vertexLength; i++) {
            sortArray.add(new ConnectedVertex<>(this.vertexList.get(i), distance[i]));
        }
        Collections.sort(sortArray);
        ArrayList<Vertex<T>> result = new ArrayList<>();
        int len = sortArray.size();
        for (int i = 0; i < len; i++) {
            result.add(sortArray.get(i).vertex);
        }
        return result;
    }

    public ArrayList<Edge<T>> getEdge(Vertex<T> vertex) {
        ArrayList<Edge<T>> result = new ArrayList<>();
        int len = this.edgeList.size();
        for (int i = 0; i < len; i++) {
            if (this.edgeList.get(i).getStartingVertex().equals(vertex)) {
                result.add(this.edgeList.get(i));
            }
        }
        return result;
    }
}