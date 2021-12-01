package com.bacontech.twenty.day2;

import com.bacontech.helpers.BaconFileReader;

import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public static void main (String[] args) {
        List<String> lines = BaconFileReader.getFileLines("src","com", "bacontech", "day2", "day2-input.txt");
        List<ProblemPassword> problemPasswords = lines.stream().map(line -> {
            String[] splitLine = line.split(": | |-");
            return new ProblemPassword(splitLine[3], splitLine[2], Integer.valueOf(splitLine[0]), Integer.valueOf(splitLine[1]), line);
        }).collect(Collectors.toList());

//        Integer numberOfValidPasswords = solveDay2Part2(problemPasswords);
        long numberOfValidPasswords = solveDay2Part2(problemPasswords);
        System.out.println("Good Passwords: " + numberOfValidPasswords);
        System.out.println("Bad Passwords: " + (problemPasswords.size() - numberOfValidPasswords));
    }

    private static Integer solveDay2Part1(List<ProblemPassword> problemPasswords) {
        int numberOfValidPasswords = 0;

        for (ProblemPassword problemPassword : problemPasswords) {
            int numberOfLetter = countLetters(problemPassword.password, problemPassword.ruleLetter);
            if (numberOfLetter >= problemPassword.minRule && numberOfLetter <= problemPassword.maxRule) {
                numberOfValidPasswords++;
                System.out.println("Good Password : " + problemPassword.original);
            }
        }
        return numberOfValidPasswords;
    }
    public static int countLetters(String word, String letter) {
        String regex = "[^" + letter + "]";
        String stripped = word.replaceAll(regex, "");
        return stripped.length();
    }

    private static long solveDay2Part2(List<ProblemPassword> problemPasswords) {
        return problemPasswords.parallelStream().filter(pass ->{
            Character firstLetter = pass.password.length() >= pass.minRule ? pass.password.charAt(pass.minRule -1) : null;
            Character secondLetter = pass.password.length() >= pass.maxRule ? pass.password.charAt(pass.maxRule -1) : null;

            Character rule = pass.ruleLetter.charAt(0);
            return rule.equals(firstLetter) ^ rule.equals(secondLetter);
        }).count();
    }
}
