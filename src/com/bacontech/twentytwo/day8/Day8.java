package com.bacontech.twentytwo.day8;

import com.bacontech.helpers.Answer;

import java.util.List;

public class Day8 extends Answer {
    public static void main(String[] args) {

        String input = Day8.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    private static Object solvePart1(String filename) {
        Day8 answer = new Day8();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart1(lines);
    }

    private static Object solvePart2(String filename) {
        Day8 answer = new Day8();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public String processAnswerPart1(List<String> lines) {

        return null;
    }

    public String processAnswerPart2(List<String> lines) {

        return null;
    }
}
