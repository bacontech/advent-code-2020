package com.bacontech.twentythree.day2;

import com.bacontech.helpers.Answer;

import java.text.MessageFormat;
import java.util.List;

public class Day2 extends Answer {
    public static void main(String[] args) {

        String input = Day2.FULL_PROMPT;

//        Object part1 = solvePart1(input);
//        printSolution("part1", part1);


//        Object part2 = solvePart2(input);
//        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day2 answer = new Day2();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public long processAnswerPart1(List<String> lines) {

        long sum = 0L;
        Long numOfRed = 12L;
        Long numOfGreen = 13L;
        Long numOfBlue = 14L;

        for (int i = 0; i < lines.size(); i++) {
            String idString = lines.get(i).split(":")[0].trim();
            Long id = Long.parseLong(idString.substring(5));
//            System.out.println(idString);
            System.out.println("--");
            System.out.println(id);
            String results = lines.get(i).split(":")[1].trim();
            String[] turns = results.split(";");

            boolean isValidTurn = true;

            for (int j = 0; j < turns.length; j++) {
                String turn = turns[j].trim();
                System.out.println(turn);
                String[] colors = turn.split(",");

                for (int k = 0; k < colors.length; k++) {
                    String color = colors[k].strip();
//                    System.out.println(color);
                    Long value = Long.parseLong(color.split(" ")[0].trim());
                    String type = color.split(" ")[1].trim();
                    if ("green".equals(type) && value > numOfGreen) {
                        isValidTurn = false;
                        break;
                    }
                    if ("blue".equals(type) && value > numOfBlue) {
                        isValidTurn = false;
                        break;
                    }
                    if ("red".equals(type) && value > numOfRed) {
                        isValidTurn = false;
                        break;
                    }
                }

            }
            System.out.println("isValidTurn: " + isValidTurn);
            if (isValidTurn) {
                sum += id;
            }
        }

        return sum;
    }


    public static Object solvePart2(String filename) {
        Day2 answer = new Day2();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart2(lines);
    }

    public long processAnswerPart2(List<String> lines) {
        long sum = 0L;
        Long numOfRed = 12L;
        Long numOfGreen = 13L;
        Long numOfBlue = 14L;

        // Game
        for (int i = 0; i < lines.size(); i++) {
            Long requiredRed = 0L;
            Long requiredGreen = 0L;
            Long requiredBlue = 0L;
            String idString = lines.get(i).split(":")[0].trim();
            Long id = Long.parseLong(idString.substring(5));
            String results = lines.get(i).split(":")[1].trim();
            String[] turns = results.split(";");

            for (int j = 0; j < turns.length; j++) {
                String turn = turns[j].trim();
                System.out.println(turn);
                String[] colors = turn.split(",");

                for (int k = 0; k < colors.length; k++) {
                    String color = colors[k].strip();
//                    System.out.println(color);
                    Long value = Long.parseLong(color.split(" ")[0].trim());
                    String type = color.split(" ")[1].trim();
                    if ("green".equals(type) && value > requiredGreen) {
                        requiredGreen = value;
                    }
                    if ("blue".equals(type) && value > requiredBlue) {
                        requiredBlue = value;
                    }
                    if ("red".equals(type) && value > requiredRed) {
                        requiredRed = value;
                    }
                }

            }
            Long power = requiredRed * requiredGreen * requiredBlue;

            System.out.println("Power: " + power);
            sum += power;
        }

        return sum;
    }


    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
