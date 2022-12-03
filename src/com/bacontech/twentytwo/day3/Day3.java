package com.bacontech.twentytwo.day3;

import com.bacontech.helpers.Answer;
import kotlin.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day3 extends Answer {
    public static void main(String[] args) {

        String input = Day3.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day3 answer = new Day3();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart1(lines);
    }

    private static char findDuplicate(String first, String second) {
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            if (second.indexOf(c) >= 0) {
                return c;
            }
        }
        throw new RuntimeException("No duplicate found");
    }

    public static Integer itemValue(char item) {
        int aciiValue = (int) item;
        if (aciiValue >  96) {
            return aciiValue - 96;
        }
        return aciiValue - 38;
    }

    public static Pair<String, String> splitBag(String bag) {
        int l = bag.length();
        int half = l / 2;
        String bag1 = bag.substring(0, half);
        String bag2 = bag.substring(half);
        return new Pair<>(bag1, bag2);
    }

    public Integer processAnswerPart1(List<String> lines) {
        Integer totalValues = 0;
        for (int i = 0; i < lines.size(); i++) {
            String fullBag = lines.get(i);
            Pair<String, String> bags = splitBag(fullBag);
            char duplicate = findDuplicate(bags.getFirst(), bags.getSecond());
            Integer value = itemValue(duplicate);
            totalValues += value;
        }


        return totalValues;
    }
    public static String uniqueLetters(String s) {
        return Arrays.stream(s.split("")).distinct().collect(Collectors.joining());
    }

    public static Object solvePart2(String filename) {
        Day3 answer = new Day3();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public Integer processAnswerPart2(List<String> lines) {
        Integer totalValues = 0;

        Map<Character, Integer> itemCount = new HashMap<>();

        String elf1 = null;
        String elf2 = null;
        String elf3 = null;
        for (int i = 0; i < lines.size(); i++) {
            if (elf1 == null) {
                elf1 = uniqueLetters(lines.get(i));
            } else if (elf2 == null) {
                elf2 = uniqueLetters(lines.get(i));
            } else {
                elf3 = uniqueLetters(lines.get(i));

                // Do logic
                for (char item : elf1.toCharArray()) {
                    Integer currentCount = itemCount.getOrDefault(item, 0);
                    itemCount.put(item, currentCount+1);
                }
                for (char item : elf2.toCharArray()) {
                    Integer currentCount = itemCount.getOrDefault(item, 0);
                    itemCount.put(item, currentCount+1);
                }
                for (char item : elf3.toCharArray()) {
                    Integer currentCount = itemCount.getOrDefault(item, 0);
                    itemCount.put(item, currentCount+1);
                }
                List<Character> badges = itemCount.entrySet().stream()
                    .filter(es -> es.getValue().equals(3))
                    .map(es -> es.getKey())
                    .collect(Collectors.toList());

                for (Character badge : badges) {
//                    System.out.println(badge);
                    Integer value = itemValue(badge);
                    totalValues += value;
                }

                // Reset
                elf1 = null;
                elf2 = null;
                elf3 = null;
                itemCount = new HashMap<>();
            }
        }

        return totalValues;
    }
}

