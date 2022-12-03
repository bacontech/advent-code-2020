package com.bacontech.twentytwo.day2;

import com.bacontech.helpers.Answer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Answer {
    public static void main(String[] args) {

        String input = Day2.FULL_PROMPT;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day2 answer = new Day2();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public long processAnswerPart1(List<String> lines) {

        long totalPoints = 0L;

        for (int i = 0; i < lines.size(); i++) {
            String[] game = lines.get(i).split(" ");
            String him = game[0];
            String me = game[1];

//            System.out.println(MessageFormat.format("Him: {0} - Me {1}", him, me));

            totalPoints += pointsForMyPosition(me);
            totalPoints += pointsForWinOrLose(him, me);
        }

        return totalPoints;
    }
    // A - Rock
    // B - Paper
    // C - Scissors

    // Response
    // X - Rock - 1pt
    // Y - Paper - 2pt
    // Z - Scissors -3pt

    // Points
    // 0 - loss
    // 3 - draw
    // 6 - win
    private long pointsForWinOrLose(String him, String me) {
        if ("A".equals(him)) { // Rock
            if ("X".equals(me)) {
                return 3L; // Rock
            }
            if ("Y".equals(me)) {
                return 6L; // Paper
            }
            if ("Z".equals(me)) {
                return 0L; // Scissors
            }
        }
        if ("B".equals(him)) { // Paper
            if ("X".equals(me)) {
                return 0L; // Rock
            }
            if ("Y".equals(me)) {
                return 3L; // Paper
            }
            if ("Z".equals(me)) {
                return 6L; // Scissors
            }
        }
        if ("C".equals(him)) { // Scissors
            if ("X".equals(me)) {
                return 6L; // Rock
            }
            if ("Y".equals(me)) {
                return 0L; // Paper
            }
            if ("Z".equals(me)) {
                return 3L; // Scissors
            }
        }
        throw new RuntimeException("Bad");
    }

    private long pointsForMyPosition(String me) {
        if ("X".equals(me)) {
            return 1;
        }
        if ("Y".equals(me)) {
            return 2;
        }
        if ("Z".equals(me)) {
            return 3;
        }
        return 0;
    }

    public static Object solvePart2(String filename) {
        Day2 answer = new Day2();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart2(lines);
    }

    public long processAnswerPart2(List<String> lines) {
        long totalPoints = 0L;


        for (int i = 0; i < lines.size(); i++) {
            String[] game = lines.get(i).split(" ");
            String him = game[0];
            String neededResult = game[1];

            String myPlay = determinePlay(him, neededResult);

            System.out.println(MessageFormat.format("Him: {0} - Me {1}", him, myPlay));

            totalPoints += pointsForMyPosition(myPlay);
            totalPoints += pointsForWinOrLose(him, myPlay);
        }
        return totalPoints;
    }

    private String determinePlay(String him, String neededResult) {
        // X - Need to Lose  -- ROCK
        // Y - Need to tie   -- PAPER
        // Z - Need to win   -- Scissors
        if ("A".equals(him)) { // Rock
            if ("X".equals(neededResult)) {
                return "Z"; // Lose - Scissors
            }
            if ("Y".equals(neededResult)) {
                return "X"; // Tie - Rock
            }
            if ("Z".equals(neededResult)) {
                return "Y"; // Win - Paper
            }
        }
        if ("B".equals(him)) { // Paper
            if ("X".equals(neededResult)) {
                return "X"; // Lose  - Rock
            }
            if ("Y".equals(neededResult)) {
                return "Y"; // Tie - Paper
            }
            if ("Z".equals(neededResult)) {
                return "Z"; // Win - Scissors
            }
        }
        if ("C".equals(him)) { // Scissors
            if ("X".equals(neededResult)) {
                return "Y"; // Lose - Paper
            }
            if ("Y".equals(neededResult)) {
                return "Z"; // Tie - Scissors
            }
            if ("Z".equals(neededResult)) {
                return "X"; // Win  - Rock
            }
        }
        throw new RuntimeException("bad");
    }


    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
