package ru.nsu.chudinov;

import java.net.CookieHandler;
import java.util.ArrayList;

public class AdjencyList<T>  extends Graph<T>{
    private ArrayList<ArrayList<ConnectedVertex<T>>> graph = new ArrayList<>();

    public AdjencyList(ArrayList<Vertex<T>> vertexList, ArrayList<Edge<T>> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        int size = vertexList.size();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<ConnectedVertex<T>>());
        }

        for (int i = 0; i < edgeList.size(); i++) {
            Edge<T> currentEdge = edgeList.get(i);
            Vertex<T> startVertex = currentEdge.getStartingVertex();
            Vertex<T> endVertex = currentEdge.getEndingVertex();
            Integer weight = currentEdge.getWeight();
            ConnectedVertex<T> connectedVertex = new ConnectedVertex<>(endVertex, weight);

            graph.get(vertexList.indexOf(currentEdge.getStartingVertex())).add(connectedVertex);
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (vertexList.contains(vertex)) {
            throw new IllegalArgumentException();
        }
        vertexList.add(vertex);
        ArrayList<ConnectedVertex<T>> connectedVertexArrayList = new ArrayList<>();
        graph.add(connectedVertexArrayList);
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        if(!vertexList.contains(vertex)) {
            throw new IllegalArgumentException();
        }

        int size = edgeList.size();
        for (int i = 0; i < size; i++) {
            Edge<T> currentEdge = edgeList.get(i);
            if (currentEdge.getStartingVertex().equals(vertex)
                    || currentEdge.getEndingVertex().equals(vertex)) {
                edgeList.remove(i);
                i--;
            }
        }

        size = vertexList.size();

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if(graph.get(i).get(j).vertex.equals(vertex)) {
                    graph.get(i).remove(j);
                    j--;
                }
            }
        }
        int index = vertexList.indexOf(vertex);
        graph.remove(index);
        vertexList.remove(index);
    }

    @Override
    public void changeVertex(Vertex<T> was, Vertex<T> became) {
        if (!vertexList.contains(was) || vertexList.contains(became)) {
            throw new IllegalArgumentException();
        }

        int index = vertexList.indexOf(was);
        vertexList.set(index, became);
        for(int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i).getStartingVertex().equals(was)) {
                edgeList.get(i).setStartingVertex(became);
            }

            if(edgeList.get(i).getEndingVertex().equals(was)) {
                edgeList.get(i).setEndingVertex(became);
            }
        }

        for(int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if(graph.get(i).get(j).vertex.equals(was)) {
                    graph.get(i).set(j, new ConnectedVertex<>(became, graph.get(i).get(j).weight));
                }
            }
        }
    }



    @Override
    public void addEdge(Edge<T> edge) {
        if(!vertexList.contains(edge.getStartingVertex())
                || !vertexList.contains(edge.getEndingVertex())) {
            throw new IllegalArgumentException();
        }

        edgeList.add(edge);
        int index = vertexList.indexOf(edge.getStartingVertex());
        var connectedVertex = new ConnectedVertex<>(edge.getEndingVertex(), edge.getWeight());
        graph.get(index).add(connectedVertex);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if(!edgeList.contains(edge)) {
            throw new RuntimeException();
        }
        edgeList.remove(edge);
        int row = vertexList.indexOf(edge.getStartingVertex());
        for(int i = 0; i < graph.get(row).size(); i++) {
            if (graph.get(row).get(i).vertex.equals(edge.getEndingVertex())) {
                graph.get(row).remove(i);
                break;
            }
        }
    }

    @Override
    public void changeEdge(Edge<T> was, Edge<T> became) {
        if(!was.getStartingVertex().equals(became.getStartingVertex())
                || !was.getEndingVertex().equals(became.getEndingVertex())
                || !edgeList.contains(was)) {
            throw new IllegalArgumentException();
        }

        edgeList.set(edgeList.indexOf(was), became);
        int index = vertexList.indexOf(was.getStartingVertex());

        for (int i = 0; i < graph.get(index).size(); i++) {
            if(graph.get(index).get(i).vertex.equals(was.getEndingVertex())) {
                graph.get(index).get(i).weight = became.getWeight();
            }
        }
    }
}
