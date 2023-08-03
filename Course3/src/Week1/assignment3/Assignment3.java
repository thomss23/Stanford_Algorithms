package Week1.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Assignment3 {

    private static Graph createGraphFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            String[] split = line.split(" ");
            Graph graph = new Graph(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

            line = reader.readLine();
            while (line != null) {

                split = line.split(" ");

                int node1 = Integer.parseInt(split[0]);
                int node2 = Integer.parseInt(split[1]);
                int cost = Integer.parseInt(split[2]);

                // Add edge to both nodes' adjacency lists
                Edge edge = new Edge(node2, cost);
                graph.addEdgeToNode(node1, edge);

                edge = new Edge(node1, cost);
                graph.addEdgeToNode(node2, edge);

                line = reader.readLine();
            }
            return graph;
        } catch (IOException e) {
            throw new RuntimeException("Problems reading file " + e.getMessage());
        }
    }



    private static long calculateTotalCostOfMSTPrimAlgorithm(Graph graph) {
        //TODO Implement Prim's MST Algorithm
        int[] visited = new int[graph.getNumberOfNodes() + 1];
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Edge(1,0)); //start from vertex 1

        long sum = 0;

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            if(visited[edge.getNode()] == 1) {
                continue;
            }

            sum = sum + edge.getCost();
            visited[edge.getNode()] = 1;

            for (Edge edgeOfCurrentVertex : graph.getEdges(edge.getNode())) {
                if(visited[edgeOfCurrentVertex.getNode()] == 0) {
                    priorityQueue.add(edgeOfCurrentVertex);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Graph graph = createGraphFromFile("Course3/src/Week1/assignment3/edges.txt");
        System.out.println(calculateTotalCostOfMSTPrimAlgorithm(graph));
    }
}
