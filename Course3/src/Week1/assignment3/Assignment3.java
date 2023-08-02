package Week1.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

                Edge edge = new Edge(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

                graph.addEdgeToNode(Integer.parseInt(split[0]), edge);

                line = reader.readLine();
            }

            return graph;

        } catch (IOException e) {
            throw new RuntimeException("Problems reading file " + e.getMessage());
        }
    }


    private static int calculateTotalCostOfMSTPrimAlgorithm(Graph graph) {
        //TODO Implement Prim's MST Algorithm
        return 0;
    }

    public static void main(String[] args) {
        Graph graph = createGraphFromFile("Course3/src/Week1/assignment3/edges.txt");
        System.out.println(calculateTotalCostOfMSTPrimAlgorithm(graph));
    }
}
