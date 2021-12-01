package com.bacontech.twenty.day9;

import com.bacontech.helpers.BaconFileReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day9 {
    public static void main (String[] args) {
//        List<String> linesSample = BaconFileReader.getFileLines("src", "com", "bacontech", "day9", "sample.txt");
//        Day9Answer answerPt1Sample = day9(linesSample, 5);
        // sample - pt1 - 127

        // Continuous set of numbers that add to invalid number [15, 25, 47, 40]
        // Add low and high 62 = 15 + 40
        // sample - pt2 - 62

        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day9", "day9.txt");
//        Day9Answer answer = day9(lines, 25);
//        System.out.println(answer.firstWithoutAMatch);
        // 21806024

        long firstWithoutMatch = 21806024L;
        long sumOfWinners = day9Pt2(lines, firstWithoutMatch);


    }

    // 2909775 too low
    // 2909775
    private static long day9Pt2(List<String> lines, long firstWithoutMatch) {
        List<Long> numbers = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            numbers.add(Long.parseLong(lines.get(i)));
        }

        long sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            // Start the set
            long lowNumber = numbers.get(i);
            sum = lowNumber;
            for (int k = i+1; k < numbers.size(); k++) {
                long thisNumber = numbers.get(k);
                sum += thisNumber;
                if (sum == firstWithoutMatch) {
                    // Win
                    System.out.println("Range Found: " + i + " " + k);
                    List<Long> numbersInRange = numbers.subList(i, k+1);
                    Long smallestNumber = numbersInRange.stream().min(Long::compare).orElse(null);
                    Long biggestNumber = numbersInRange.stream().max(Long::compare).orElse(null);
                    System.out.println("Winners: " + smallestNumber + " " + biggestNumber);
                    long sumOfWinners = (smallestNumber + biggestNumber);
                    System.out.println("Sum of winners: " + sumOfWinners);
                    return sumOfWinners;
                } else if (sum > firstWithoutMatch) {
                    // Start over
                    break;
                }
            }
        }
        return -1L;
    }

    private static Day9Answer day9(List<String> lines, int sizeOfPreamble) {

        // lineNumber, matches
        Map<Integer, Set<Integer>> possibleMatches = new HashMap<>();
        Map<Integer, XmasNumber> numberMap = new HashMap<>();
        for(int i = 0; i < lines.size(); i++) {
            numberMap.put(i, new XmasNumber(i, Long.parseLong(lines.get(i))));
            possibleMatches.put(i, new HashSet<>());
        }

        for(int i = sizeOfPreamble; i < numberMap.size(); i++) {
            XmasNumber currentNumber = numberMap.get(i);
            int bottomRange = i - sizeOfPreamble;

            firstNumber:
            for (int j = i-1; j >= bottomRange; j--) {
                XmasNumber possibleMatch1 = numberMap.get(j);
                if (possibleMatch1.getValue() > currentNumber.getValue()) continue; // Already too big
                for (int k = i-1; k >= bottomRange; k--) {
                    if (j == k) continue; // cannot be the same number
                    XmasNumber possibleMatch2 = numberMap.get(k);
//                    System.out.println("Checking - " + currentNumber.getValue() + " = " + possibleMatch1.getValue() + " + " + possibleMatch2.getValue());
                    if (possibleMatch1.getValue() + possibleMatch2.getValue() == currentNumber.getValue()) {
//                        System.out.println("MATCH - " + currentNumber.getValue() + " = " + possibleMatch1.getValue() + " + " + possibleMatch2.getValue());
                        possibleMatches.get(j).add(i);
                        possibleMatches.get(k).add(i);
                        currentNumber.addMatch(possibleMatch1.getValue(), possibleMatch2.getValue());
                    }
                }
            }
        }

        System.out.println("Multiple Matches?");
        List<XmasNumber> multiMatchNumbers = numberMap.values().stream().filter(xmasNumber -> {
            return xmasNumber.getMatches().size() > 1;
        }).collect(Collectors.toList());
        if (multiMatchNumbers.size() > 0) {
            System.out.println(multiMatchNumbers.size() + " multiple matches!");
        } else {
            System.out.println("no");
        }

        System.out.println(numberMap);
        System.out.println(possibleMatches);

        //Find the first after preamble, with no matches
        XmasNumber firstWithoutMatch = numberMap.values().stream()
            .filter(xmasNumber -> (xmasNumber.getLineNumber() >= sizeOfPreamble && !xmasNumber.hasAMatch()))
            .findFirst().orElse(null);

//        System.out.println(firstWithoutMatch);

        // The first step of attacking the weakness in the XMAS data is to find the first number in the list
        // (after the preamble) which is not the sum of two of the 25 numbers before it.
        // What is the first number that does not have this property?
        Day9Answer answer = new Day9Answer();
        answer.firstWithoutAMatch = firstWithoutMatch.getValue();
        return answer;
    }


}
