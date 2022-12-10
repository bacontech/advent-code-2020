package com.bacontech.twentytwo.day7;

import com.bacontech.helpers.Answer;

import java.util.List;

public class Day7 extends Answer {
    public static void main(String[] args) {

        String input = Day7.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    private static Object solvePart1(String filename) {
        Day7 answer = new Day7();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart1(lines);
    }

    private static Object solvePart2(String filename) {
        Day7 answer = new Day7();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public String processAnswerPart1(List<String> lines) {

        // Start of marker - 4 sequential characters that are different.

        // Return the number of characters from the start of the string
        // to the start of the marker
        return null;
    }

    public String processAnswerPart2(List<String> lines) {

        // Start of marker - 4 sequential characters that are different.

        // Return the number of characters from the start of the string
        // to the start of the marker
        return null;
    }
}
