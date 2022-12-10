package com.bacontech.twentytwo.day5;

import com.bacontech.helpers.Answer;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day5 extends Answer {

    private static String REGEX = "^move (\\d+) from (\\d+) to (\\d+)$";
    private static Pattern PATTERN = Pattern.compile(REGEX);

    public static void main(String[] args) {

        String input = Day5.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day5 answer = new Day5();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart1(lines);
    }



    public String processAnswerPart1(List<String> lines) {
        // After the rearrangement procedure, what crate ends up on top of each stack?

        List<String> crates = new ArrayList<>();
        List<String> procedures = new ArrayList<>();
        boolean isCrates = true;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if ("".equals(line)) {
                isCrates = false;
                continue;
            }
            if (isCrates) {
                crates.add(line);
            } else {
                procedures.add(line);
            }
        }



        // Build List of Stacks
        List<Stack<String>> stacks = buildCrateStacks(crates);

        // Follow Procedure
        procedures.forEach(p -> {
            Matcher matcher = PATTERN.matcher(p);
            matcher.find();
            Integer numberToMove = Integer.parseInt(matcher.group(1));
            Integer origin = Integer.parseInt(matcher.group(2)); // One Based
            origin--; // Zero Based
            Integer destination = Integer.parseInt(matcher.group(3)); // One Based
            destination--; // Zero Based

            for (Integer i = 0; i < numberToMove; i++) {
                String itemToMove = stacks.get(origin).pop();
                stacks.get(destination).push(itemToMove);
            }
        });

        // Build Answer
        StringBuilder topOfStack = new StringBuilder();
        for (int i = 0; i < stacks.size(); i++) {
            topOfStack.append(stacks.get(i).peek());
        }
        return topOfStack.toString();
    }

    static List<Stack<String>> buildCrateStacks(List<String> crateLines) {
        int crateWidth = 3;
        int numberOfStacks = determineNumberOfStacks(crateLines.get(0), crateWidth);

        // Create Stacks
        List<Stack<String>> stacks = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++) {
            stacks.add(new Stack<>());
        }

        // Loop backwards - add to stacks
        for (int i = crateLines.size() -1; i > -1; i--) {
            String line = crateLines.get(i);
            if (line.startsWith(" 1   2   ")) {
                continue;
            }
            for (int j = 0; j < numberOfStacks; j++) {
                int start = (crateWidth + 1) * j;
                int end = (crateWidth + 1) * (j+1) -1;
                String crate = line.substring(start, end);
                if (!StringUtils.isBlank(crate)) {
                    crate = crate.replaceAll("[\\[\\]]", "");
                    stacks.get(j).push(crate);
                }
            }
        }

        return stacks;
    }

    static int determineNumberOfStacks(String s, int crateWidth) {
        int stringLength = s.length();
        stringLength++; // Add an extra buffer
        return stringLength / (crateWidth +1);
    }


    public static Object solvePart2(String filename) {
        Day5 answer = new Day5();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public String processAnswerPart2(List<String> lines) {
        // After the rearrangement procedure, what crate ends up on top of each stack?

        List<String> crates = new ArrayList<>();
        List<String> procedures = new ArrayList<>();
        boolean isCrates = true;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if ("".equals(line)) {
                isCrates = false;
                continue;
            }
            if (isCrates) {
                crates.add(line);
            } else {
                procedures.add(line);
            }
        }



        // Build List of Stacks
        List<Stack<String>> stacks = buildCrateStacks(crates);

        // Follow Procedure
        for (String p : procedures) {
            stacks.forEach(s -> {
//                System.out.println(s.stream().toList());
            });
//            System.out.println("------");

            Matcher matcher = PATTERN.matcher(p);
            matcher.find();
            Integer numberToMove = Integer.parseInt(matcher.group(1));
            Integer origin = Integer.parseInt(matcher.group(2)); // One Based
            origin--; // Zero Based
            Integer destination = Integer.parseInt(matcher.group(3)); // One Based
            destination--; // Zero Based

            List<String> itemsToMove = new ArrayList<>();
            for (Integer i = 0; i < numberToMove; i++) {
                String itemToMove = stacks.get(origin).pop();
                itemsToMove.add(itemToMove);
            }
            if (itemsToMove != null) {
                for (int i = itemsToMove.size() -1; i > -1; i--) {
                    String item = itemsToMove.get(i);
                    stacks.get(destination).push(item);
                }
            }
        }

        // Build Answer
        StringBuilder topOfStack = new StringBuilder();
        for (int i = 0; i < stacks.size(); i++) {
            topOfStack.append(stacks.get(i).peek());
        }
        return topOfStack.toString();
    }
}

