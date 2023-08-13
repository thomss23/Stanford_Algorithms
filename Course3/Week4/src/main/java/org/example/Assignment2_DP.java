package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assignment2_DP {
    public static void main(String[] args) {
        try {
            String fileName = "Course3/Week4/src/main/resources/knapsack_big.txt";
            List<Item> input = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = reader.readLine(); // Skip the first line
                String[] split = line.split(" ");

                int W = Integer.parseInt(split[0]);
                int n = Integer.parseInt(split[1]);

                while ((line = reader.readLine()) != null) {
                    String[] split2 = line.split(" ");
                    input.add(new Item(Integer.parseInt(split2[0]), Integer.parseInt(split2[1])));
                }

                HashMap<Integer, Long> dp = new HashMap<>();
                dp.put(0, 0L);

                for (int i = 1; i <= n; i++) {
                    HashMap<Integer, Long> newDP = new HashMap<>(dp);
                    for (int x : dp.keySet()) {
                        if (x + input.get(i - 1).getWeight() <= W) {
                            newDP.put(x + input.get(i - 1).getWeight(),
                                    Math.max(newDP.getOrDefault(x + input.get(i - 1).getWeight(), 0L),
                                            dp.get(x) + input.get(i - 1).getValue()));
                        }
                    }
                    dp = newDP;
                }

                long max = 0;
                for (long value : dp.values()) {
                    max = Math.max(max, value);
                }

                System.out.println(max);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
