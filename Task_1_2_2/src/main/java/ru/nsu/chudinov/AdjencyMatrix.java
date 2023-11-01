package ru.nsu.chudinov;

import java.util.ArrayList;

public class AdjencyMatrix<T> extends Graph<T> {
    private final ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private Integer size = 0;

    public AdjencyMatrix(ArrayList<Vertex<T>> vertexArrayList, ArrayList<Edge<T>> edgeArrayList) {
        this.vertexList = vertexArrayList;
        this.edgeList = edgeArrayList;
        this.size = vertexList.size();

        for (int i = 0; i < vertexList.size(); i++) {
            ArrayList<Integer> current = new ArrayList<>();
            for (int j = 0; j < vertexList.size(); j++) {
                current.add(0);
            }
            graph.add(current);
        }

        for (int i = 0; i < edgeList.size(); i++) {
            int row = vertexList.indexOf(edgeList.get(i).getStartingVertex());
            int column = vertexList.indexOf(edgeList.get(i).getEndingVertex());
            graph.get(row).set(column, edgeList.get(i).getWeight());
        }
    }

    @Override
    public Boolean addVertex(Vertex<T> vertex) {
        if (vertexList.contains(vertex)) {
            return false;
        }
        ArrayList<Integer> addList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            addList.add(0);
        }
        graph.add(addList);
        size++;
        for (int i = 0; i < size; i++) {
            graph.get(i).add(0);
        }
        vertexList.add(vertex);
        return true;
    }

    @Override
    public Boolean removeVertex(Vertex<T> vertex) {
        if(!vertexList.contains(vertex)) {
            return false;
        }
        int index = vertexList.indexOf(vertex);
        for (int i = 0; i < size; i++) {
            graph.get(i).remove(index);
        }
        graph.remove(index);
        vertexList.remove(vertex);
        for (int i = 0; i < edgeList.size(); i++) {
            Edge<T> currentEdge = edgeList.get(i);
            if (currentEdge.getStartingVertex().equals(vertex)
                    || currentEdge.getEndingVertex().equals(vertex)) {
                edgeList.remove(i);
                i--;
            }
        }
        size--;
        return true;
    }

    @Override
    public Boolean changeVertex(Vertex<T> was, Vertex<T> became) {
        if (!vertexList.contains(was) || vertexList.contains(became)) {
            return false;
        }
        int index = vertexList.indexOf(was);
        vertexList.set(index, became);
        for (int i = 0; i < size; i++) {
            Edge<T> currentEdge = edgeList.get(i);
            if(currentEdge.getStartingVertex().equals(was)) {
                currentEdge.setStartingVertex(became);
            }
            if(currentEdge.getEndingVertex().equals(was)) {
                currentEdge.setEndingVertex(became);
            }
        }
        return true;
    }

    @Override
    public Boolean addEdge(Edge<T> edge) {
        if (!vertexList.contains(edge.getStartingVertex())
        || !vertexList.contains(edge.getEndingVertex())) {
            return false;
        }
        graph.get(vertexList.indexOf(edge.getStartingVertex()))
                .set(vertexList.indexOf(edge.getEndingVertex()), edge.getWeight());
        edgeList.add(edge);

        return true;
    }

    @Override
    public Boolean removeEdge(Edge<T> edge) {
        if(!edgeList.contains(edge)) {
            return false;
        }
        graph.get(vertexList.indexOf(edge.getStartingVertex()))
                .set(vertexList.indexOf(edge.getEndingVertex()), 0);
        edgeList.remove(edge);

        return true;
    }

    @Override
    public Boolean changeEdge(Edge<T> was, Edge<T> became) {
        if(!was.getStartingVertex().equals(became.getStartingVertex())
                || !was.getEndingVertex().equals(became.getEndingVertex())
                || !edgeList.contains(was)) {
            return false;
        }

        graph.get(vertexList.indexOf(was.getStartingVertex()))
                .set(vertexList.indexOf(was.getEndingVertex()), became.getWeight());
        int index = edgeList.indexOf(was);
        edgeList.set(index, became);
        return true;
    }
}
