package org.example;

import org.jgrapht.alg.util.UnionFind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignment2 {

    private UnionFind<BitSet> unionFind;
    private HashSet<BitSet> uniqueElementsAddedSet;
    public Assignment2() {
        this.unionFind = new UnionFind<>(new HashSet<>());
        this.uniqueElementsAddedSet = new HashSet<>();
    }

    public void setUp() {


        try (BufferedReader reader = new BufferedReader(new FileReader("Course3/Week2/src/main/resources/clustering_big.txt"))) {
            String line = reader.readLine(); // Skip the first line
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\s", "");
                BitSet bitSet = new BitSet(24);
                for (int i = 0; i < 24; i++) {
                    if (line.charAt(i) == '1') {
                        bitSet.set(i);
                    }
                }
                if (!uniqueElementsAddedSet.contains(bitSet)) {
                    unionFind.addElement(bitSet);
                    uniqueElementsAddedSet.add(bitSet);
                } else {
                    System.out.println("Duplicate element found");
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private List<BitSet> generatePermutationsWith1HammingDistance(BitSet bitSet) {
        List<BitSet> permutationsWith1HammingDistance = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            BitSet bs = (BitSet) bitSet.clone();
            bs.flip(i);
            if (calculateHammingDistanceBetweenTwoBitSets(bitSet, bs) == 1) {
                permutationsWith1HammingDistance.add(bs);
            }
        }

        return permutationsWith1HammingDistance;
    }

    private List<BitSet> generatePermutationsWith2HammingDistance(BitSet bitSet) {
        List<BitSet> permutationsWith2HammingDistance = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            for (int j = i + 1; j < 24; j++) {
                BitSet bs = (BitSet) bitSet.clone();
                bs.flip(i);
                bs.flip(j);
                if (calculateHammingDistanceBetweenTwoBitSets(bitSet, bs) == 2) {
                    permutationsWith2HammingDistance.add(bs);
                }
            }
        }
        return permutationsWith2HammingDistance;
    }

    private int calculateHammingDistanceBetweenTwoBitSets(BitSet bitSet1, BitSet bitSet2) {
        BitSet bs = (BitSet) bitSet1.clone();
        bs.xor(bitSet2);
        return bs.cardinality();
    }

    public int findKMaxClusters() {
        for (BitSet bitSet : uniqueElementsAddedSet) {

            List<BitSet> permutationsWith1HammingDistance = generatePermutationsWith1HammingDistance(bitSet);
            List<BitSet> permutationsWith2HammingDistance = generatePermutationsWith2HammingDistance(bitSet);

            for (BitSet element : permutationsWith1HammingDistance) {
                if (uniqueElementsAddedSet.contains(element)) {
                    unionFind.union(bitSet, element);
                }
            }

            for (BitSet element : permutationsWith2HammingDistance) {
                if (uniqueElementsAddedSet.contains(element)) {
                    unionFind.union(bitSet, element);
                }
            }
        }

        return unionFind.numberOfSets();
    }

    public static void main(String[] args) {
        Assignment2 assignment2 = new Assignment2();
        assignment2.setUp();
        int k = assignment2.findKMaxClusters();
        System.out.println("Number of k-max clusters: " + k);
    }
}