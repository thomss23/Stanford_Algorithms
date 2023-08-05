package org.example;

public class Edge implements Comparable<Edge> {

    private final int node1;
    private final int node2;
    private final int cost;

    public Edge(int node1, int node2, int cost) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;

    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.getCost(), o.getCost());
    }
}
