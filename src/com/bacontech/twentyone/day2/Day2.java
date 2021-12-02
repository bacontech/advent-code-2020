package com.bacontech.twentyone.day2;

import com.bacontech.helpers.Answer;

import java.util.List;

public class Day2 extends Answer {
    public static void main(String[] args) {

        // SAMPLE vs FULL_PROMPT
        String input = FULL_PROMPT;

//        Object part1 = solvePart1(input);
//        printSolution("part1", part1);


         Object part2 = solvePart2(input);
         printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day2 answer = new Day2();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public static Object solvePart2(String filename) {

        Day2 answer = new Day2();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public long processAnswerPart1(List<String> lines) {
        long horizontalPos = 0L;
        long depthPos = 0L;

        for(String command : lines) {
            String[] parts = command.split(" ");
            String direction = parts[0];
            long units = Long.parseLong(parts[1]);

            if ("forward".equals(direction)) {
                horizontalPos += units;
            }
            if ("down".equals(direction)) {
                depthPos += units;
            }
            if ("up".equals(direction)) {
                depthPos -= units;
            }
        }

        System.out.println(horizontalPos);
        System.out.println(depthPos);
        return horizontalPos * depthPos;
    }

    public long processAnswerPart2(List<String> lines) {
        long horizontalPos = 0L;
        long depthPos = 0L;
        long aim = 0L;

        for(String command : lines) {
            String[] parts = command.split(" ");
            String direction = parts[0];
            long units = Long.parseLong(parts[1]);

            if ("down".equals(direction)) {
                aim += units;
            }
            if ("up".equals(direction)) {
                aim -= units;
            }

            if ("forward".equals(direction)) {
                horizontalPos += units;
                long deltaDepth = aim * units;
                depthPos += deltaDepth;
            }
        }

        System.out.println(horizontalPos);
        System.out.println(depthPos);
        System.out.println(aim);
        return horizontalPos * depthPos;
    }



    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
