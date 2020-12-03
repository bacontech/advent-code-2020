package com.bacontech.day1;

import com.bacontech.helpers.BaconFileReader;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) {
        //        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        // read input file
        List<String> lines = BaconFileReader.getFileLines("src","com", "bacontech", "day1", "day1-input.txt");
        List<Integer> input = lines.stream().map(Integer::valueOf).collect(Collectors.toList());

//        Integer answer1 = firstAnswer(input);
//        System.out.println(answer1);
//
	    Integer answer2 = day1part1(input, 2020);
        System.out.println(answer2);

//        Integer answerPart2 = day1part2bruteForce(input, 2020);
//        System.out.println(answerPart2);
    }




    /** Find two integers that sum to 2020, then multiply them, and return
     * Answer - nums: 1479 541
     * Answer: 800139
     */
    public static Integer day1part1(List<Integer> input, int target) {
        int totalIncrements = 1;
        TreeSet<Integer> sortedSet = new TreeSet<>();
        input.forEach(sortedSet::add);

        Iterator<Integer> bigToSmall = sortedSet.descendingIterator();
        Iterator<Integer> smallToBig = sortedSet.iterator();
        Integer largeNumber = bigToSmall.next();
        Integer firstSmallNumber = smallToBig.next();
        while (true) {
            totalIncrements++;
            int sum = largeNumber + firstSmallNumber;
//            System.out.println(largeNumber + " + " + firstSmallNumber + " = " + sum);
            if (sum == target) {
                System.out.println("Total Increments: " + totalIncrements);
                System.out.println("Answer: " + largeNumber + " " + firstSmallNumber);
                return largeNumber * firstSmallNumber;
            } else if (sum > target) {
                largeNumber = bigToSmall.next();
                continue;
            } else {
                firstSmallNumber = smallToBig.next();
            }
        }
    }

    public static Integer firstAnswer(List<Integer> input) {
        int totalIncrements = 0;
        TreeSet<Integer> sortedSet = new TreeSet<>();
        input.forEach(sortedSet::add);
        bigToSmallLoop:
        for (Iterator<Integer> bigToSmall = sortedSet.descendingIterator(); bigToSmall.hasNext(); ) {
            totalIncrements++;
            Integer largeNumber = bigToSmall.next();
            for (Iterator<Integer> smallToBig = sortedSet.iterator(); smallToBig.hasNext();) {
                totalIncrements++;
                Integer smallNumber = smallToBig.next();
                Integer sum = largeNumber + smallNumber;
//                System.out.println(largeNumber + " + " + smallNumber + " = " + sum);
                if(sum == 2020) {
                    System.out.println("Total Increments: " + totalIncrements);
                    return largeNumber * smallNumber;
                } else if (sum > 2020) {
                    continue bigToSmallLoop;
                }
            }
        }
        return null;
    }

    // Find 3 numbers that sum to the target number
    // found : 285 131 1604
    // 59885340
    public static Integer day1part2bruteForce(List<Integer> input, int target) {
        for (int a = 0; a < input.size(); a++) {
            for (int b = 0; b < input.size(); b++) {
                if (b == a) continue;
                for (int c = 0; c < input.size(); c++) {
                    if (c == a || c == b) continue;
                    int sum = input.get(a) + input.get(b) + input.get(c);
                    if (sum == target) {
                        System.out.println("found : " + input.get(a) + " " + input.get(b) + " " + input.get(c));
                        return input.get(a) * input.get(b) * input.get(c);
                    }
                }
            }
        }
        return null;
    }
}
