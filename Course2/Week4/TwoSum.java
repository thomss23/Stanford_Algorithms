package Week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class TwoSum {

    public static long[] readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        long[] input = new long[1000005]; // Assuming the input file contains 1 million entries
        int size = 0;
        String line;
        
        while ((line = reader.readLine()) != null) {
            input[size++] = Long.parseLong(line);
        }
        
        reader.close();
        return input;
    }

    public static int twoSum(long[] input) {
        HashSet<Long> hashSet = new HashSet<>();

        int count = 0;

        for (long t = -10000; t <= 10000; t++) {
            for (long element : input) {
                long complement = t - element;
                if (hashSet.contains(complement) && complement != element) {
                    count++;
                    break;
                }
                hashSet.add(element);
            }

            hashSet.clear();
        }

        return count;
    }

    

    public static void main(String[] args) {
        String filename = "D:\\Programming Projects\\Stanford_Algorithms\\Course2\\Week4\\input.txt";

        try {

            long[] input = readFromFile(filename);
            System.out.println(filename + " read successfully.");
            long totalCount = twoSum(input);


            System.out.println("Count: " + totalCount);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
