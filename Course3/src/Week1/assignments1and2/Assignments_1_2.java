package Week1.assignments1and2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Assignments_1_2 {

    private static List<Job> readJobsFromFile(String fileName) {
        try {

            List<String> allLines = Files.readAllLines(Paths.get(fileName));

            return allLines.stream().map(line -> {
                String[] split = line.split(" ");
                return new Job(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }).collect(Collectors.toList());


        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static long task1(List<Job> input) {
        input.sort((o1, o2) -> {
            int thisRatio = o1.getWeight() - o1.getLength();
            int otherRatio = o2.getWeight() - o2.getLength();

            if (thisRatio > otherRatio) {
                return -1; // The current job has a higher ratio, so it should be scheduled first (descending order).
            } else if (thisRatio < otherRatio) {
                return 1; // The other job has a higher ratio, so it should be scheduled first (descending order).
            } else {
                return Integer.compare(o2.getWeight(), o1.getWeight());
            }
        });

        long completionTime = 0;
        long weightedSum = 0;

        for (Job job : input) {
            completionTime += job.getLength();
            weightedSum += completionTime * job.getWeight();

        }
        return weightedSum;
    }

    public static long task2(List<Job> input) {
        input.sort((o1, o2) -> {
            float thisRatio = (float) o1.getWeight() / o1.getLength();
            float otherRatio = (float) o2.getWeight() / o2.getLength();

            return Float.compare(otherRatio, thisRatio);
        });

        long completionTime = 0;
        long weightedSum = 0;

        for (Job job : input) {
            completionTime += job.getLength();
            weightedSum += completionTime * job.getWeight();
        }

        return weightedSum;
    }

    public static void main(String[] args) {
        List<Job> input = readJobsFromFile("Course3/src/Week1/assignments1and2/input.txt");
        System.out.println(task1(input));
        System.out.println(task2(input));
    }
}