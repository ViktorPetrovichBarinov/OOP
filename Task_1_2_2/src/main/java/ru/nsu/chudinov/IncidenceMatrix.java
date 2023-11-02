package ru.nsu.chudinov;

import java.util.ArrayList;

public class IncidenceMatrix<T> extends Graph<T> {
    private ArrayList<ArrayList<ConnectedEdge<T>>> graph = new ArrayList<>();

    IncidenceMatrix(ArrayList<Vertex<T>> vertexList, ArrayList<Edge<T>> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;

        for (int i = 0; i < vertexList.size(); i++) {
            ArrayList<ConnectedEdge<T>> currentEdgeList = new ArrayList<>();
            for (int j = 0; j < edgeList.size(); j++) {
                Vertex<T> start = edgeList.get(j).getStartingVertex();
                Vertex<T> end = edgeList.get(j).getEndingVertex();
                Vertex<T> currentVertex = vertexList.get(i);
                if(currentVertex.equals(start) && currentVertex.equals(end)) {
                    currentEdgeList.add(new ConnectedEdge<>(edgeList.get(j), 2));
                    continue;
                }
                if(currentVertex.equals(start)) {
                    currentEdgeList.add(new ConnectedEdge<>(edgeList.get(j), 1));
                    continue;
                }
                if(currentVertex.equals(end)) {
                    currentEdgeList.add(new ConnectedEdge<>(edgeList.get(j), -1));
                    continue;
                }
                currentEdgeList.add(new ConnectedEdge<>(null, 0));
            }
            graph.add(currentEdgeList);
        }
    }


    @Override
    public Boolean addVertex(Vertex<T> vertex) {
        if(vertexList.contains(vertex)) {
            return false;
        }
        vertexList.add(vertex);
        ArrayList<ConnectedEdge<T>> currentArrayVertex = new ArrayList<>();
        for (int i = 0; i < edgeList.size(); i++) {
            currentArrayVertex.add(new ConnectedEdge<>(null, 0));
        }
        graph.add(currentArrayVertex);

        return true;
    }

    @Override
    public Boolean removeVertex(Vertex<T> vertex) {
        if(!vertexList.contains(vertex)) {
            return false;
        }
        int indexOfVertex = vertexList.indexOf(vertex);
        for (int i = 0; i < edgeList.size(); i++) {
            graph.get(indexOfVertex).set(i, new ConnectedEdge<>(null, 0));
        }

        for (int i = 0; i < edgeList.size(); i++) {
            int counter = 0;
            for (int j = 0; j < vertexList.size(); j++) {
                int flag = graph.get(j).get(i).flag;
                if (flag == 1 ||  flag == -1) {
                    counter++;
                }
                if (flag == 2) {
                    counter += 2;
                }
            }
            if(counter < 2) {
                removeEdge(edgeList.get(i));
                i--;
            }
        }
        graph.remove(indexOfVertex);
        vertexList.remove(vertex);
        return true;
    }

    @Override
    public Boolean changeVertex(Vertex<T> was, Vertex<T> became) {
        if(!vertexList.contains(was) || !vertexList.contains(became)) {
            return false;
        }
        int index = vertexList.indexOf(was);
        vertexList.set(index, became);
        for (int i = 0; i < edgeList.size(); i++) {
            if (edgeList.get(i).getStartingVertex().equals(was)) {
                edgeList.get(i).setStartingVertex(became);
            }
            if (edgeList.get(i).getEndingVertex().equals(was)) {
                edgeList.get(i).setEndingVertex(became);
            }
        }

        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                if (graph.get(i).get(j).edge != null) {
                    if (graph.get(i).get(j).edge.getStartingVertex().equals(was)) {
                        graph.get(i).get(j).edge.setStartingVertex(became);
                    }
                    if (graph.get(i).get(j).edge.getEndingVertex().equals(was)) {
                        graph.get(i).get(j).edge.setEndingVertex(became);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public Boolean addEdge(Edge<T> edge) {
        if (!vertexList.contains(edge.getStartingVertex())
        ||  !vertexList.contains(edge.getEndingVertex())) {
            return false;
        }

        Vertex<T> startVertex = edge.getStartingVertex();
        Vertex<T> endVertex = edge.getEndingVertex();
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).equals(startVertex) && startVertex.equals(endVertex)) {
                graph.get(i).add(new ConnectedEdge<>(edge, 2));
                continue;
            }
            if (vertexList.get(i).equals(startVertex)) {
                graph.get(i).add(new ConnectedEdge<>(edge, 1));
                continue;
            }
            if (vertexList.get(i).equals(endVertex)) {
                graph.get(i).add(new ConnectedEdge<>(edge, -1));
                continue;
            }
            graph.get(i).add(new ConnectedEdge<>(null, 0));
        }
        edgeList.add(edge);

        return true;
    }

    @Override
    public Boolean removeEdge(Edge<T> edge) {
        if (!edgeList.contains(edge)) {
            return false;
        }
        int index = edgeList.indexOf(edge);
        for (int i = 0; i < vertexList.size(); i++) {
            graph.get(i).remove(index);
        }
        edgeList.remove(index);
        return true;
    }

    @Override
    public Boolean changeEdge(Edge<T> was, Edge<T> became) {
        if(!was.getStartingVertex().equals(became.getStartingVertex())
                || !was.getEndingVertex().equals(became.getEndingVertex())
                || !edgeList.contains(was)) {
            return false;
        }
        int index = edgeList.indexOf(was);
        edgeList.set(index, became);
        for (int i = 0; i < vertexList.size(); i++) {
            if (graph.get(i).get(index).flag != 0) {
                graph.get(i).get(index).edge = became;
            }
        }


        return true;
    }

    private class ConnectedEdge<T> {
        private Edge<T> edge;
        private int flag;

        ConnectedEdge(Edge<T> edge, int flag) {
            this.edge = edge;
            this.flag = flag;
        }
    }
}
