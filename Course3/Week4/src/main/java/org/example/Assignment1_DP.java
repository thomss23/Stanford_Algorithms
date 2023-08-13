package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assignment1_DP {




    public static void main(String[] args)
    {
        try{

            String fileName = "Course3/Week4/src/main/resources/knapsack1.txt";
            List<Item> input = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

                String line = reader.readLine(); // Skip the first line
                String[] split = line.split(" ");
                Integer W = Integer.parseInt(split[0]);
                Integer n = Integer.parseInt(split[1]);

                while ((line = reader.readLine()) != null) {
                    String[] split2 = line.split(" ");
                    input.add(new Item(Integer.parseInt(split2[0]), Integer.parseInt(split2[1])));

                }

                int[][] dp = new int[n][W + 1];

                for(int i = 0; i <= W; i++) {
                    dp[0][i] = 0;
                }

                for (int i = 0; i < n; i++) {
                    for(int x = 0; x <= W; x++) {
                        if (i - 1 < 0) {
                            dp[i][x] = 0;
                        }
                        else if (x - input.get(i).getWeight() < 0) {
                            dp[i][x] = dp[i - 1][x];
                        }
                        else {
                            dp[i][x] = Math.max(dp[i - 1][x], dp[i - 1][x - input.get(i).getWeight()] + input.get(i).getValue());
                        }
                    }
                }

                System.out.println(dp[n-1][W]);
            }


        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}