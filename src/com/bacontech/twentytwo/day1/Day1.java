package com.bacontech.twentytwo.day1;

import com.bacontech.helpers.Answer;

import java.util.List;

public class Day1 extends Answer {
    public static void main(String[] args) {

        String input = Day1.FULL_PROMPT;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


//        Object part2 = solvePart2(input);
//        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day1 answer = new Day1();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public long processAnswerPart1(List<String> lines) {
        // How many times did a depth increase from the previous
        lines.add("");
        long mostCalories = 0L;

        long caloriesThisElf = 0L;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.equals("")) {
                if (caloriesThisElf > mostCalories) {
                    mostCalories = caloriesThisElf;
                }
                caloriesThisElf = 0L;
            } else {
                Long calories = Long.parseLong(line);
                caloriesThisElf += calories;
            }
        }
        return mostCalories;
    }

    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
