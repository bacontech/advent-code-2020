package com.bacontech.twentytwo.day4;

import com.bacontech.helpers.Answer;
import kotlin.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 extends Answer {
    public static void main(String[] args) {

        String input = Day4.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day4 answer = new Day4();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart1(lines);
    }

    public Integer processAnswerPart1(List<String> lines) {
        Integer totalValues = 0;
        // How many assignment pairs does one range fully contain the other?
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] pair = line.split(",");
            List<Integer> assignmentForLeft = getAssignment(pair[0]);
            List<Integer> assignmentForRight = getAssignment(pair[1]);
            if (assignmentForLeft.size() < assignmentForRight.size()) {
                if (assignmentForRight.containsAll(assignmentForLeft)) {
                    totalValues++;
                }
            } else {
                if (assignmentForLeft.containsAll(assignmentForRight)) {
                    totalValues++;
                }
            }
        }

        return totalValues;
    }

    static List<Integer> getAssignment(String assignmentString) {
        String[] startEnd = assignmentString.split("-");
        Integer startNum = Integer.parseInt(startEnd[0]);
        Integer endNum = Integer.parseInt(startEnd[1]);
        return IntStream.range(startNum, endNum+1).boxed().toList();
    }

    public static Object solvePart2(String filename) {
        Day4 answer = new Day4();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public Integer processAnswerPart2(List<String> lines) {
        Integer totalValues = 0;
        // How many assignment pairs overlap at all?
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] pair = line.split(",");
            List<Integer> assignmentForLeft = getAssignment(pair[0]);
            List<Integer> assignmentForRight = getAssignment(pair[1]);

            if (assignmentForLeft.stream().anyMatch(num -> assignmentForRight.contains(num))) {
                totalValues++;
            }
        }

        return totalValues;
    }
}

