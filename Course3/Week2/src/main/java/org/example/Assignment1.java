package org.example;

import org.jgrapht.alg.util.UnionFind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Assignment1 {

    private static List<Edge> createGraphFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            String[] split = line.split(" ");
            List<Edge> edgeList = new ArrayList<>();

            line = reader.readLine();
            while (line != null) {

                split = line.split(" ");

                int node1 = Integer.parseInt(split[0]);
                int node2 = Integer.parseInt(split[1]);
                int cost = Integer.parseInt(split[2]);

                // Add edge to both nodes' adjacency lists
                Edge edge = new Edge(node1 ,node2, cost);
                edgeList.add(edge);

                line = reader.readLine();
            }
            return edgeList;
        } catch (IOException e) {
            throw new RuntimeException("Problems reading file " + e.getMessage());
        }
    }

    private static int maximizeKClustering(List<Edge> edgeList, int k) {

        BitSet a = new BitSet(25);
        System.out.println(a.toString());
        return 0;
    }


    public static void main(String[] args) {

        List<Edge> edgeList = createGraphFromFile("Course3/Week2/src/main/resources/clustering_input.txt");

        System.out.println(maximizeKClustering(edgeList, 4));

    }
}