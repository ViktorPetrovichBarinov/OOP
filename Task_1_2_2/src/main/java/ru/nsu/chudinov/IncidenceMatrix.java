package ru.nsu.chudinov;

import java.util.ArrayList;

public class IncidenceMatrix<T> extends Graph<T> {
    private final ArrayList<ArrayList<ConnectedEdge<T>>> graph = new ArrayList<>();

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
                    currentEdgeList.add(new ConnectedEdge<>(edgeList.get(j), ConnectedEdge.Direction.LOOP));
                    continue;
                }
                if(currentVertex.equals(start)) {
                    currentEdgeList.add(new ConnectedEdge<>(edgeList.get(j), ConnectedEdge.Direction.FROM_VERTEX));
                    continue;
                }
                if(currentVertex.equals(end)) {
                    currentEdgeList.add(new ConnectedEdge<>(edgeList.get(j), ConnectedEdge.Direction.IN_VERTEX));
                    continue;
                }
                currentEdgeList.add(new ConnectedEdge<>(null, ConnectedEdge.Direction.NULL));
            }
            graph.add(currentEdgeList);
        }
    }


    @Override
    public void addVertex(Vertex<T> vertex) {
        if(vertexList.contains(vertex)) {
            throw new IllegalArgumentException();
        }
        vertexList.add(vertex);
        ArrayList<ConnectedEdge<T>> currentArrayVertex = new ArrayList<>();
        for (int i = 0; i < edgeList.size(); i++) {
            currentArrayVertex.add(new ConnectedEdge<>(null, ConnectedEdge.Direction.NULL));
        }
        graph.add(currentArrayVertex);
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        if(!vertexList.contains(vertex)) {
            throw new IllegalArgumentException();
        }
        int indexOfVertex = vertexList.indexOf(vertex);
        for (int i = 0; i < edgeList.size(); i++) {
            graph.get(indexOfVertex).set(i, new ConnectedEdge<>(null, ConnectedEdge.Direction.NULL));
        }

        for (int i = 0; i < edgeList.size(); i++) {
            int counter = 0;
            for (int j = 0; j < vertexList.size(); j++) {
                ConnectedEdge.Direction direction = graph.get(j).get(i).direction;
                if (direction == ConnectedEdge.Direction.FROM_VERTEX ||  direction == ConnectedEdge.Direction.IN_VERTEX) {
                    counter++;
                }
                if (direction == ConnectedEdge.Direction.LOOP) {
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
    }

    @Override
    public void changeVertex(Vertex<T> was, Vertex<T> became) {
        if(!vertexList.contains(was) || vertexList.contains(became)) {
            throw new IllegalArgumentException();
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
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!vertexList.contains(edge.getStartingVertex())
        ||  !vertexList.contains(edge.getEndingVertex())) {
            throw new IllegalArgumentException();
        }

        Vertex<T> startVertex = edge.getStartingVertex();
        Vertex<T> endVertex = edge.getEndingVertex();
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).equals(startVertex) && startVertex.equals(endVertex)) {
                graph.get(i).add(new ConnectedEdge<>(edge, ConnectedEdge.Direction.LOOP));
                continue;
            }
            if (vertexList.get(i).equals(startVertex)) {
                graph.get(i).add(new ConnectedEdge<>(edge, ConnectedEdge.Direction.FROM_VERTEX));
                continue;
            }
            if (vertexList.get(i).equals(endVertex)) {
                graph.get(i).add(new ConnectedEdge<>(edge, ConnectedEdge.Direction.IN_VERTEX));
                continue;
            }
            graph.get(i).add(new ConnectedEdge<>(null, ConnectedEdge.Direction.NULL));
        }
        edgeList.add(edge);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if (!edgeList.contains(edge)) {
            throw new IllegalArgumentException();
        }
        int index = edgeList.indexOf(edge);
        for (int i = 0; i < vertexList.size(); i++) {
            graph.get(i).remove(index);
        }
        edgeList.remove(index);
    }

    @Override
    public void changeEdge(Edge<T> was, Edge<T> became) {
        if(!was.getStartingVertex().equals(became.getStartingVertex())
                || !was.getEndingVertex().equals(became.getEndingVertex())
                || !edgeList.contains(was)) {
            throw new IllegalArgumentException();
        }
        int index = edgeList.indexOf(was);
        edgeList.set(index, became);
        for (int i = 0; i < vertexList.size(); i++) {
            if (graph.get(i).get(index).direction != ConnectedEdge.Direction.NULL) {
                graph.get(i).get(index).edge = became;
            }
        }
    }

    private static class ConnectedEdge<T> {
        public enum Direction {
            FROM_VERTEX,//из вершины 1
            IN_VERTEX,//в вершину -1
            LOOP,//2
            NULL//0
        }
        private Edge<T> edge;
        private final Direction direction;

        ConnectedEdge(Edge<T> edge, Direction direction) {
            this.edge = edge;
            this.direction = direction;
        }
    }
}
