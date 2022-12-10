package com.bacontech.twentytwo.day6;

import com.bacontech.helpers.Answer;

import java.util.*;
import java.util.regex.Matcher;

public class Day6 extends Answer {
    public static void main(String[] args) {

        String input = Day6.FULL_PROMPT;

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


    public Integer processAnswerPart1(List<String> lines) {

        // Start of marker - 4 sequential characters that are different.

        // Return the number of characters from the start of the string
        // to the start of the marker
        return findMessage(lines.get(0), 4);
    }
    public Integer findMessage(String line, int limit) {
        List<Character> last4Characters = new LinkedList<>();
        for (int i = 0; i < line.length(); i++) {
            Character character = line.charAt(i);
//            System.out.println(character);
            if (last4Characters.size() < (limit-1)) {
                last4Characters.add(character);
            } else {
                last4Characters.add(character);
//                System.out.println(last4Characters);
                if (!hasDuplicate(last4Characters)) {
                    // Not in the previous 3
                    return i + 1;
                }
                last4Characters.remove(0);
//                System.out.println(last4Characters);
            }

//            System.out.println("----");
        }
        return -1;
    }

    private boolean hasDuplicate(List<Character> last4Characters) {
        Set<Character> characters = new HashSet<>(last4Characters);
        return characters.size() < last4Characters.size();
    }

    public static Object solvePart2(String filename) {
        Day6 answer = new Day6();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }


    public Integer processAnswerPart2(List<String> lines) {

        return findMessage(lines.get(0), 14);
    }
}
