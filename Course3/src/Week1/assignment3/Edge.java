package Week1.assignment3;

public class Edge {

    private final int node;
    private final int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int getNode() {
        return node;
    }


    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "node=" + node +
                ", cost=" + cost +
                '}';
    }
}
