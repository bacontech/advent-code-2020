package com.bacontech.twentytwo.day6;

import com.bacontech.helpers.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;

public class Day6 extends Answer {
    public static void main(String[] args) {

        String input = Day6.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day6 answer = new Day6();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart1(lines);
    }


    public String processAnswerPart1(List<String> lines) {

        // Start of marker - 4 sequential characters that are different.

        // Return the number of characters from the start of the string
        // to the start of the marker
        return null;
    }

    public static Object solvePart2(String filename) {
        Day6 answer = new Day6();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }


    public String processAnswerPart2(List<String> lines) {

        return null;
    }
}
