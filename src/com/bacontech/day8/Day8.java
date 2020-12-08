package com.bacontech.day8;

import com.bacontech.helpers.BaconFileReader;

import java.util.*;

public class Day8 {
    public static void main (String[] args) {
//        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day8", "sample.txt");
        // Sample - Part 1 - expected X
        // Sample - Part 2 - expected XX

        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day8", "day8.txt");

        Day8Answer answer = day8(lines);
        System.out.println(answer.getAccumulator());
        // 1200

        Day8Answer answer2 = day8Part2Brute(lines);
//         Sample - 8
        System.out.println(answer2.isLoopHit());
        System.out.println(answer2.getAccumulator());
        // 1023
    }

    private static Day8Answer day8Part2Brute(List<String> lines) {
        Map<Integer, GameCommand> mappedLines = new HashMap<>();
        for(int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String operation = line.split(" ")[0];
            int argument = Integer.parseInt(line.split(" ")[1]);
            GameCommand gameCommand = new GameCommand(i, operation, argument);
            mappedLines.put(i, gameCommand);
        }

        Day8Answer answerWithoutFlips = testMapForInfinite(mappedLines);
        if (!answerWithoutFlips.isLoopHit()) {
            System.out.println("without flips: " + answerWithoutFlips.getAccumulator());
            return answerWithoutFlips;
        }

        int flipRow = 0;
        do {
            GameCommand originalCommand = mappedLines.get(flipRow);
            if (originalCommand.getOperation().equals("acc")) {
                flipRow++;
                continue;
            }

            GameCommand flipped
                = new GameCommand(flipRow, originalCommand.getFlippedOperation(), originalCommand.getArgument());
            mappedLines.put(flipRow, flipped);

            Day8Answer answer = testMapForInfinite(mappedLines);
            if (!answer.isLoopHit()) {
                System.out.println("Row to flip: " + flipRow);
                return answer;
            }
            mappedLines.put(flipRow, originalCommand);

            flipRow++;
        } while ( flipRow < lines.size());
        return null;
    }



    private static Day8Answer day8(List<String> lines) {
        Map<Integer, GameCommand> mappedLines = new HashMap<>();
        for(int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String operation = line.split(" ")[0];
            int argument = Integer.parseInt(line.split(" ")[1]);
            GameCommand gameCommand = new GameCommand(i, operation, argument);
            mappedLines.put(i, gameCommand);
        }

        Day8Answer answer = testMapForInfinite(mappedLines);
        // operation (acc jmp nop) - argument (signed number)

        return answer;
    }

    private static Day8Answer testMapForInfinite(Map<Integer, GameCommand> mappedLines) {
        Set<Integer> alreadyExecuted = new HashSet<>();

        int TARGET_NUMBER = mappedLines.size() -1;
//        System.out.println(TARGET_NUMBER);
        Long accumulator = 0L;
        boolean infiniteLoopHit = false;

        Integer currentLineNum = 0;
        GameCommand currentLine = mappedLines.get(currentLineNum);
        do {
            String operation = currentLine.getOperation();
            int argument = currentLine.getArgument();


            if (alreadyExecuted.contains(currentLineNum)) {
                infiniteLoopHit = true;
                break;
            }

            alreadyExecuted.add(currentLineNum);

            if (operation.equals("acc")) {
                accumulator += argument;
                currentLineNum++;
                currentLine = mappedLines.get(currentLineNum);
            } else if (operation.equals("jmp")) {
                currentLineNum = currentLineNum + argument;
                currentLine = mappedLines.get(currentLineNum);
            } else if (operation.equals("nop")) {
                currentLineNum++;
                currentLine = mappedLines.get(currentLineNum);
            } else {
                System.out.println("SOMETHING BROKE");
                System.out.println(currentLine);
            }
            if (currentLineNum == TARGET_NUMBER) {
                break;
            }

        } while (true);
        Day8Answer answer = new Day8Answer(accumulator, infiniteLoopHit);
//        System.out.println(answer.isLoopHit() + " - " + answer.getAccumulator());
        return answer;
    }
}
