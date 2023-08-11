package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Assignment1_2 {

    public static List<Symbol> setUp() {

        String fileName = "Course3/Week3/src/main/resources/huffman.txt";
        List<Symbol> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine(); // Skip the first line
            while ((line = reader.readLine()) != null) {
                long weight = Long.parseLong(line);
                list.add(new Symbol(weight));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return list;
    }

    public static void printList(List<Symbol> list) {
        System.out.println("Printing list length " + list.size());
        for (Symbol symbol : list) {
            System.out.println("weight: " + symbol.getWeight());
        }
    }

    public static Symbol huffmanEncoding(List<Symbol> list) {
        PriorityQueue<Symbol> pq = new PriorityQueue<>(list);

        while (pq.size() > 1) {
            Symbol left = pq.poll();
            Symbol right = pq.poll();
            Symbol parent = new Symbol(left.getWeight() + right.getWeight());

            parent.setLeft(left);
            parent.setRight(right);
            pq.add(parent);
        }

        return pq.poll();
    }

    public static void printTree(Symbol root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        printTree(root.getLeft());
        printTree(root.getRight());
    }

    public static int maximumLengthCodeWord(Symbol root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maximumLengthCodeWord(root.getLeft()), maximumLengthCodeWord(root.getRight())) + 1;
    }

    public static int minimumLengthCodeWord(Symbol root) {
        if (root == null) {
            return 0;
        }
        return Math.min(minimumLengthCodeWord(root.getLeft()), minimumLengthCodeWord(root.getRight())) + 1;
    }


    public static void main(String[] args) {
        List<Symbol> list = setUp();
        Symbol root = huffmanEncoding(list);
        System.out.println(maximumLengthCodeWord(root) - 1);
        System.out.println(minimumLengthCodeWord(root) - 1);

    }

}
