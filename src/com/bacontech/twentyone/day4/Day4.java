package com.bacontech.twentyone.day4;

import com.bacontech.helpers.Answer;

import java.util.List;

public class Day4 extends Answer {
    public static void main(String[] args) {

        String input = SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        // Object part2 = solvePart2(input);
        // printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day4 answer = new Day4();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public static Object solvePart2(String filename) {

        Day4 answer = new Day4();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public long processAnswerPart1(List<String> lines) {
        return 0L;
    }

    public long processAnswerPart2(List<String> lines) {
        return 0L;
    }



    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
