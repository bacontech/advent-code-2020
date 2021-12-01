package com.bacontech.twentyone.day1;

import com.bacontech.helpers.Answer;
import java.util.List;

public class Day1 extends Answer {

    public static void main(String[] args) {

        String input = Day1.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day1 answer = new Day1();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public static Object solvePart2(String filename) {

        Day1 answer = new Day1();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }


    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }

    public long processAnswerPart1(List<String> lines) {
        // How many times did a depth increase from the previous
        long numberOfIncreases = 0L;
        long previousDepth = Long.parseLong(lines.get(0));
        for (String newDepth : lines) {
            long currentDepth = Long.parseLong(newDepth);
            if (currentDepth > previousDepth) {
                numberOfIncreases++;
            }
            previousDepth = currentDepth;
        }
        return numberOfIncreases;
    }

    public long processAnswerPart2(List<String> lines) {
        // Depth is now a 3 sliding window sum
        // How many times did a depth increase from the previous window
        long numberOfIncreases = 0L;
        long previousDepthA = Long.parseLong(lines.get(0));
        long previousDepthB = Long.parseLong(lines.get(1));
        long previousDepthC = Long.parseLong(lines.get(2));
        for (int i =3; i < lines.size(); i++) {
            long newDepth = Long.parseLong(lines.get(i));

            long previousSum = previousDepthA + previousDepthB + previousDepthC;
            long currentSum = previousDepthB + previousDepthC + newDepth;
            if (currentSum > previousSum) {
                numberOfIncreases++;
            }
            previousDepthA = previousDepthB;
            previousDepthB = previousDepthC;
            previousDepthC = newDepth;
        }
        return numberOfIncreases;
    }
}
