package Week1.assignment3;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<List<Edge>> adjacencyList;

    private final int numberOfNodes;

    private final int numberOfEdges;


    public Graph(int numberOfNodes, int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
        this.numberOfNodes = numberOfNodes;
        this.adjacencyList = new ArrayList<>();

        for (int i = 0; i <= numberOfNodes; i++) {
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

    public int getNumberOfEdges() {
        return this.numberOfEdges;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyList=" + adjacencyList.toString() +
                '}';
    }
}
