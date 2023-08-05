package org.example;


import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<List<Edge>> adjacencyList;

    private final int numberOfNodes;


    public Graph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.adjacencyList = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            this.adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdgeToNode(int node, Edge edge) {

        if (this.adjacencyList.get(node) == null) {
            this.adjacencyList.add(new ArrayList<>());
        }

        this.adjacencyList.get(node).add(edge);
    }

    public List<Edge> getEdges(int node) {
        return this.adjacencyList.get(node);
    }

    public int getNumberOfNodes(){
        return this.numberOfNodes;
    }


    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyList=" + adjacencyList.toString() +
                '}';
    }
}
