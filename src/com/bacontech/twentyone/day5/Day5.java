package com.bacontech.twentyone.day5;

import com.bacontech.helpers.Answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day5 extends Answer {
    public static void main(String[] args) {

        String input = Answer.FULL_PROMPT;

//        Object part1 = solvePart1(input);
//        printSolution("part1", part1);


         Object part2 = solvePart2(input);
         printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day5 answer = new Day5();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }

    public static Object solvePart2(String filename) {

        Day5 answer = new Day5();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public long processAnswerPart1(List<String> lines) {
        // x, y, hit
        Map<Long, Map<Long, Long>> map = new HashMap<>();

        for(String line : lines) {
            // Parse
            String start = line.split(" -> ")[0];
            String end = line.split(" -> ")[1];
            Long x1 = Long.parseLong(start.split(",")[0]);
            Long y1 = Long.parseLong(start.split(",")[1]);
            Long x2 = Long.parseLong(end.split(",")[0]);
            Long y2 = Long.parseLong(end.split(",")[1]);


            // only consider vertical or horizontal lines
            if (x1.equals(x2)) {
                // place vents

                Long largerY = y1;
                Long smallerY = y2;
                if (y2 > y1) {
                    largerY = y2;
                    smallerY = y1;
                }
                for (long y = smallerY; y <= largerY; y++) {
                    Map<Long, Long> yMap = map.getOrDefault(x1, new HashMap<>());
                    long current = yMap.getOrDefault(y, 0L);
                    yMap.put(y, current + 1L);
                    map.put(x1, yMap);
                }
            } else if (y1.equals(y2)) {
                Long largerX = x1;
                Long smallerX = x2;
                if (x2 > x1) {
                    largerX = x2;
                    smallerX = x1;
                }
                for (long x = smallerX; x <= largerX; x++) {
                    Map<Long, Long> yMap = map.getOrDefault(x, new HashMap<>());
                    long current = yMap.getOrDefault(y1, 0L);
                    yMap.put(y1, current + 1L);
                    map.put(x, yMap);
                }
            } else {
                // This is not a qualified line
                System.out.println("Not qualified " + line);
            }
        }

        long numberOfMultiHits = 0L;

        for(Map<Long, Long> yMap : map.values()) {
            for (Long hits : yMap.values()) {
                if (hits > 1) {
                    numberOfMultiHits = numberOfMultiHits + 1;
                }
            }
        }
        return numberOfMultiHits;
    }

    public long processAnswerPart2(List<String> lines) {
        // x, y, hit
        Map<Long, Map<Long, Long>> map = new HashMap<>();

        for(String line : lines) {
            // Parse
            String start = line.split(" -> ")[0];
            String end = line.split(" -> ")[1];
            Long x1 = Long.parseLong(start.split(",")[0]);
            Long y1 = Long.parseLong(start.split(",")[1]);
            Long x2 = Long.parseLong(end.split(",")[0]);
            Long y2 = Long.parseLong(end.split(",")[1]);


            // only consider vertical or horizontal lines
            if (x1.equals(x2)) {
                // place vents

                Long largerY = y1;
                Long smallerY = y2;
                if (y2 > y1) {
                    largerY = y2;
                    smallerY = y1;
                }
                for (long y = smallerY; y <= largerY; y++) {
                    Map<Long, Long> yMap = map.getOrDefault(x1, new HashMap<>());
                    long current = yMap.getOrDefault(y, 0L);
                    yMap.put(y, current + 1L);
                    map.put(x1, yMap);
                }
            } else if (y1.equals(y2)) {
                Long largerX = x1;
                Long smallerX = x2;
                if (x2 > x1) {
                    largerX = x2;
                    smallerX = x1;
                }
                for (long x = smallerX; x <= largerX; x++) {
                    Map<Long, Long> yMap = map.getOrDefault(x, new HashMap<>());
                    long current = yMap.getOrDefault(y1, 0L);
                    yMap.put(y1, current + 1L);
                    map.put(x, yMap);
                }
            } else {
                // Must be diag
                List<Long> xValues = getValues(x1, x2);

                List<Long> yValues = getValues(y1, y2);

                for (int i = 0; i < xValues.size(); i++) {
                    Long xAxis = xValues.get(i);
                    Long yAxis = yValues.get(i);


                    Map<Long, Long> yMap = map.getOrDefault(xAxis, new HashMap<>());
                    long current = yMap.getOrDefault(yAxis, 0L);
                    yMap.put(yAxis, current + 1L);
                    map.put(xAxis, yMap);
                }
            }
        }

        long numberOfMultiHits = 0L;

        for(Map<Long, Long> yMap : map.values()) {
            for (Long hits : yMap.values()) {
                if (hits > 1) {
                    numberOfMultiHits = numberOfMultiHits + 1;
                }
            }
        }
        return numberOfMultiHits;
    }

    private List<Long> getValues (Long one, Long two) {
        List<Long> values = new ArrayList<>();
        if (one < two) {
            for (long i = one; i <= two ; i++) {
                values.add(i);
            }
        } else {
            for (long i = one; i >= two ; i--) {
                values.add(i);
            }
        }

        return values;
    }



    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }
}
