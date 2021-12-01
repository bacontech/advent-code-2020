package com.bacontech.twenty.day15;

import com.bacontech.helpers.BaconFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day15 {
    public static void main (String[] args) {
        List<String> lines = parse("day15.txt");
//        List<String> sample = new ArrayList();
//            sample.add("0");
//            sample.add("3");
//            sample.add("6");

        Long answerPt1 = day15pt1(lines);
        System.out.println(answerPt1);
    }

    static Long day15pt1(List<String> lines) {

        HashMap<String, Integer> lastSpokeMap = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            lastSpokeMap.put(lines.get(i), i+1);
        }
        lastSpokeMap.remove(lines.get(lines.size()-1));

        int target = 30000000;
//        int target = 2020;
        int size = lines.size();
        String lastNumberSpoken = null;
        while (lines.size() < target) {
            lastNumberSpoken = lines.get(lines.size()-1);
            int thisTurn = lines.size();

            if (lastSpokeMap.containsKey(lastNumberSpoken)) {
                int index = lastSpokeMap.get(lastNumberSpoken);
                int diff = thisTurn - index;
                lines.add(String.valueOf(diff));
            } else {
                lines.add("0");
            }
            lastSpokeMap.put(lastNumberSpoken, thisTurn);
        }


        // 866
        int num = target - 1;
        // what is the 2020th number spoken
        return Long.valueOf(lines.get(num));
    }

    static List<String> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day15", filename);
        List<String> numbers = new ArrayList<>();
        for (String num : lines.get(0).split(","))  {
            numbers.add(num);
        }
        return numbers;
    }
}
