package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Assignment3 {

    public static HashMap<Integer, Integer> setUp() {

        String fileName = "Course3/Week3/src/main/resources/mwis.txt";
        int verticeNo = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip the first line
            while ((line = reader.readLine()) != null) {
                int weight = Integer.parseInt(line);
                System.out.println("Vertex " + verticeNo + " weight: " + weight);
                map.put(verticeNo, weight);
                verticeNo++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return map;
    }

    public static long[] findMaxWeightIndependentSet(HashMap<Integer, Integer> map) {
        long[] maxWeightIndependentSet = new long[map.size() + 1];
        maxWeightIndependentSet[0] = 0;
        maxWeightIndependentSet[1] = map.get(1);
        for (int i = 2; i < maxWeightIndependentSet.length; i++) {
            maxWeightIndependentSet[i] = Math.max(maxWeightIndependentSet[i - 1], maxWeightIndependentSet[i - 2] + map.get(i));
        }
        return maxWeightIndependentSet;
    }

    public static HashSet<Integer> reconstructSet(long[] maxWeightIndependentSet, HashMap<Integer, Integer> vertices) {
        HashSet<Integer> maxSet = new HashSet<>();
        int i = maxWeightIndependentSet.length - 1;
        while (i >= 1) {

            if (i == 1) {
                System.out.println("Adding vertex " + i + " to maxSet");
                maxSet.add(i);
                break;
            }
            if (maxWeightIndependentSet[i - 1] >= maxWeightIndependentSet[i - 2] + vertices.get(i)) {
                System.out.println("Skipping vertex " + i);
                i--;
            } else {
                System.out.println("Adding vertex " + i + " to maxSet");
                maxSet.add(i);
                i -= 2;
            }
        }
        return maxSet;
    }



    public static void main(String[] args) {

        HashMap<Integer, Integer> map = setUp();

        long[] maxWeightIndependentSet = findMaxWeightIndependentSet(map);
        System.out.printf("Max weight independent set: %d\n", maxWeightIndependentSet[maxWeightIndependentSet.length - 1]);

        HashSet<Integer> maxSet = reconstructSet(maxWeightIndependentSet, map);
        System.out.println(maxSet);

        int[] verticesToCheck = {1, 2, 3, 4, 17, 117, 517, 997};
        for (int j : verticesToCheck) {
            if (maxSet.contains(j)) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println();
    }
}
