package com.bacontech.day3;

import com.bacontech.helpers.BaconFileReader;

import java.math.BigInteger;
import java.util.List;

public class Day3 {
    public static void main (String[] args) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day3", "day3-input.txt");
//        right 3 - down 1
        long answer =
            day3(lines, 1, 1) *
            day3(lines, 1, 3) *
            day3(lines, 1, 5) *
            day3(lines, 1, 7) *
            day3(lines, 2, 1);

        System.out.println(answer);
    }

    // 286 part 1

    // 60
    // 286
    // 76
    // 62
    // 45
    // 3638606400
    public static long day3(List<String> lines, int numDown, int numRight) {
        int col = 0;
        int trees = 0;

        for (int i = 0; i < lines.size(); i = i + numDown) {
            String line = lines.get(i);
            if (col >= line.length()) {
                col = col - line.length();
            }
            char spot = line.charAt(col);
//            System.out.println(spot);
            if (spot == '#') {
                trees++;
            }
            col = col + numRight;
        }
        System.out.println(trees);
        return trees;
    }
}
