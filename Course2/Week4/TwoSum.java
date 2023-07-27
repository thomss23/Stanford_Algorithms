package Week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TwoSum {

    public static long[] readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        long[] input = new long[1000005]; // Assuming the input file contains 1 million entries
        int size = 0;
        String line;
        
        while ((line = reader.readLine()) != null && size < 1000005) {
            input[size++] = Long.parseLong(line);
        }
        
        reader.close();
        return input;
    }

    public static int twoSum(long[] input) {
        HashSet<Long> hashSet = new HashSet<>();
        for (long element : input) {
            hashSet.add(element);
        }
        
        int count = 0;

        for (long t = -10000; t <= 10000; t++) {
            for (long element : input) {
                long complement = t - element;
                if (hashSet.contains(complement)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String filename = "Week4/input.txt";
        try {
            long[] input = readFromFile(filename);
            System.out.println(filename + " read successfully.");

            // Split the input array into 5 parts
            int chunkSize = input.length / 5;
            List<Callable<Integer>> tasks = new ArrayList<>();
            ExecutorService executor = Executors.newFixedThreadPool(5);

            for (int i = 0; i < 5; i++) {
                int start = i * chunkSize;
                int end = (i == 4) ? input.length : (i + 1) * chunkSize;
                long[] chunk = Arrays.copyOfRange(input, start, end);

                Callable<Integer> task = () -> twoSum(chunk);
                tasks.add(task);
            }

            // Execute tasks in parallel
            List<Future<Integer>> results = executor.invokeAll(tasks);
            executor.shutdown();

            // Sum up the counts from each thread
            int totalCount = 0;
            for (Future<Integer> result : results) {
                totalCount += result.get();
            }

            System.out.println("Count: " + totalCount);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error executing tasks: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
