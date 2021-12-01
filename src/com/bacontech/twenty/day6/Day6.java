package com.bacontech.twenty.day6;

import com.bacontech.helpers.BaconFileReader;

import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public static void main (String[] args) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day6", "day6-input.txt");
//        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day6", "sample.txt");

        Long answer = day6(lines);
        System.out.println(answer);
        // 6310 - Part 1

        // 2 - Part 2 Guess
        // 3193 - Part 2

    }

    public static List<GroupAnswers> parseAnswers(List<String> lines) {
        List<GroupAnswers> allGroups = new ArrayList<>();
        GroupAnswers currentAnswers = new GroupAnswers();
        for (String line : lines) {
            if (line.isBlank()) {
                allGroups.add(currentAnswers);
                currentAnswers = new GroupAnswers();
            } else {
                currentAnswers.incrementGroupSize();
            }

            for(char answer : line.toCharArray()) {
                currentAnswers.addAnswer(answer);
            }
        }
        if (currentAnswers.getAnswers().keySet().size() > 0 ){
            allGroups.add(currentAnswers);
        }
        return allGroups;
    }

    public static long day6(List<String> lines) {
        List<GroupAnswers> allGroups = parseAnswers(lines);
        long number = 0L;
//        Part 1
//        for(GroupAnswers groupAnswer : allGroups) {
//            number = number + groupAnswer.getAnswers().keySet().size();
//        }
        for (GroupAnswers groupAnswers: allGroups) {
            for (Integer answered : groupAnswers.getAnswers().values()) {
                if (answered == groupAnswers.getSize()) {
                    number++;
                }
            }
        }
        return number;
    }
}
