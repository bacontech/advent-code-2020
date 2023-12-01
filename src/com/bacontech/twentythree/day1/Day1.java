package com.bacontech.twentythree.day1;

import com.bacontech.helpers.Answer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 extends Answer {

    private static Map<String, Long> CONVERTER;

    private static void buildMap() {
        CONVERTER = new HashMap<>();
        CONVERTER.put("one", 1L);
        CONVERTER.put("two", 2L);
        CONVERTER.put("three", 3L);
        CONVERTER.put("four", 4L);
        CONVERTER.put("five", 5L);
        CONVERTER.put("six", 6L);
        CONVERTER.put("seven", 7L);
        CONVERTER.put("eight", 8L);
        CONVERTER.put("nine", 9L);
        CONVERTER.put("1", 1L);
        CONVERTER.put("2", 2L);
        CONVERTER.put("3", 3L);
        CONVERTER.put("4", 4L);
        CONVERTER.put("5", 5L);
        CONVERTER.put("6", 6L);
        CONVERTER.put("7", 7L);
        CONVERTER.put("8", 8L);
        CONVERTER.put("9", 9L);
    }


    public static void main(String[] args) {
        buildMap();

        String input = Day1.FULL_PROMPT;
//        String input = Day1.SAMPLE;
//
//        Object part1 = solvePart1(input);
//        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day1 answer = new Day1();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public long processAnswerPart1(List<String> lines) {
        long totalSum = 0L;
        // Grab 2-digit number, first and last
        for (String l : lines) {
            System.out.println("--- Line " + l);
            Long lineNumber = determineLineNumber(l);
            System.out.println(lineNumber);
            System.out.println("---");
            totalSum += lineNumber;
        }

        return totalSum;
    }
    static Long determineLineNumber(String l) {
        Long firstNumber = null;
        Long lastNumber = null;
        for (int i = 0; i < l.length(); i++) {

            if (firstNumber == null) {
                Long testFront = parseChar(l.charAt(i));
                if (testFront != null) {
                    firstNumber = testFront;
                }
            }

            if (lastNumber == null) {
                Long testBack = parseChar(l.charAt(l.length() -1 - i));
                if (testBack != null) {
                    lastNumber = testBack;
                }
            }
            if (firstNumber != null && lastNumber != null) {
                Long tens = firstNumber * 10L;
                return tens + lastNumber;
            }
        }
        throw new RuntimeException("Number Not Found");
    }

    static Long determineLineNumber2(String l) {
        // can be spelled or digit
        Long firstNumber = null;
        Long lastNumber = null;
//        System.out.println("test");
//        System.out.println(l);
        for (int i = 0; i < l.length(); i++) {
            String trimmedFront = l.substring(i);
            String trimmedBack = l.substring(0, l.length() - i);

//            System.out.println(trimmedFront);
            if (firstNumber == null) {
                for (String k : CONVERTER.keySet()) {
                    if (trimmedFront.startsWith(k)) {
                        firstNumber = CONVERTER.get(k);
                    }
                }
            }
            if (lastNumber == null) {
//            System.out.println(trimmedBack);
                for (String k : CONVERTER.keySet()) {
                    if (trimmedBack.endsWith(k)) {
                        lastNumber = CONVERTER.get(k);
                    }
                }
            }

            if (firstNumber != null && lastNumber != null) {
                Long tens = firstNumber * 10L;
                return tens + lastNumber;
            }
        }
        throw new RuntimeException("Number Not Found");
    }
    static Long parseChar(char testCar) {
        String testStr = String.valueOf(testCar);
//        System.out.println("Testing: " + testStr);
        try {
            Long converted = Long.valueOf(testStr);
//            System.out.println("Converted: " + converted);
            return converted;
        } catch (Exception e) {
//            System.out.println("Not Converted.");
            return null;
        }
    }

    public static Object solvePart2(String filename) {
        Day1 answer = new Day1();
        List<String> lines = answer.parse(filename);

        return answer.processAnswerPart2(lines);
    }

    public long processAnswerPart2(List<String> lines) {
        long totalSum = 0L;
        // Grab 2-digit number, first and last
        for (String l : lines) {
            System.out.println("--- Line " + l);
            Long lineNumber = determineLineNumber2(l);
            System.out.println(lineNumber);
            System.out.println("---");
            totalSum += lineNumber;
        }

        return totalSum;
    }


    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
