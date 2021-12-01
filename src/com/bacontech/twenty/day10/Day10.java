package com.bacontech.twenty.day10;

import com.bacontech.helpers.BaconFileReader;

import java.util.*;

public class Day10 {
    public static void main (String[] args) {
        TreeSet<Integer> sortedAdapters = parseFileAndSort("day10.txt");

//        day10Part1(sortedAdapters);
        long part2 = day10Part2(sortedAdapters);
        System.out.println(part2);
    }

    static TreeSet<Integer> parseFileAndSort(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day10", filename);
//        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day10", "sample.txt");

        TreeSet<Integer> sortedAdapters = new TreeSet<>();
        lines.stream().map(Integer::parseInt).forEach(sortedAdapters::add);
        Integer builtInAdapter = sortedAdapters.last() + 3;
        sortedAdapters.add(builtInAdapter);
        return sortedAdapters;
    }

    // Pt 1- 2080
    static void day10Part1(TreeSet<Integer> sortedAdapters) {
        int oneJoltDifferences = 0;
        int threeJoltDifferences = 0;

        Integer lastAdapter = 0;

        for (Integer adapter : sortedAdapters) {
            int difference = adapter - lastAdapter;
            if (difference == 3) {
                threeJoltDifferences++;
            } else if (difference == 1) {
                oneJoltDifferences++;
            } else {
                System.out.println("We broke!!");
                System.out.println(lastAdapter);
                System.out.println(adapter);
            }
            lastAdapter = adapter;
        }

        System.out.println("Number of 1 jolt differences: " + oneJoltDifferences);
        System.out.println("Number of 3 jolt differences: " + threeJoltDifferences);

        long answerPt1 = (long) oneJoltDifferences * (long) threeJoltDifferences;
        System.out.println(answerPt1);

        // What is the number of 1-jolt differences multiplied by the number of 3-jolt differences?

    }

    // How many distinct Arrangements can you make?
    // you can connect to something 1, 2, or 3 jolts higher
    static long day10Part2(TreeSet<Integer> sortedAdapters) {
        sortedAdapters.add(0);

        // Loop through, discover how many hops each number can make on its own

        Map<Integer, Adapter> adaptersWithHopCount = buildAdapterMap(sortedAdapters);
        // Individual hops discovered
        System.out.println("hops discovered");
        List<List<Adapter>> allSets = buildComplexSets(adaptersWithHopCount);
        System.out.println("Hop sets discovered");

        long numberOfArrangements = 1;
        for(List<Adapter> set : allSets) {
            // Each hop set has a multiple of paths.
            long pathsOnThisSet = discoverNumberOfPaths(set);
            numberOfArrangements = numberOfArrangements * pathsOnThisSet;
        }


        // How many arrangements can you make. Can jump 1, 2, or 3.
        return numberOfArrangements;
    }

    static Map<Integer, Adapter> buildAdapterMap(TreeSet<Integer> sortedAdapters) {

        Map<Integer, Adapter> adaptersWithHopCount = new HashMap<>();
        sortedAdapters.forEach(adapter -> adaptersWithHopCount.put(adapter, new Adapter(adapter)));
        adaptersWithHopCount.keySet()
            .forEach(value -> {
                Adapter adapter = adaptersWithHopCount.get(value);
                if (adaptersWithHopCount.containsKey(value + 1)) adapter.getHops().add(adaptersWithHopCount.get(value + 1));
                if (adaptersWithHopCount.containsKey(value + 2)) adapter.getHops().add(adaptersWithHopCount.get(value + 2));
                if (adaptersWithHopCount.containsKey(value + 3)) adapter.getHops().add(adaptersWithHopCount.get(value + 3));
            });
        return adaptersWithHopCount;
    }

    // 4 5 6 7
    static long discoverNumberOfPaths(List<Adapter> set) {

        Adapter first = set.get(0);
        if (set.size() == 1 || first.getPossibleHops() == 1) {
            return 1;
        }
        if (first.getHops().stream().allMatch(adapter -> adapter.getPossibleHops() == 1)) {
            return first.getPossibleHops();
        }

        long numOfPaths = 0;
        List<Adapter> afterFirst = set.subList(1, set.size());
        long pathChild1 = discoverNumberOfPaths(afterFirst);
        numOfPaths += pathChild1;
        if (first.getPossibleHops() > 1) {
            List<Adapter> afterSecond = set.subList(2, set.size());
            long pathChild2 = discoverNumberOfPaths(afterSecond);
            numOfPaths += pathChild2;
        }
        if (first.getPossibleHops() > 2) {
            List<Adapter> afterThird = set.subList(3, set.size());
            long pathChild3 = discoverNumberOfPaths(afterThird);
            numOfPaths += pathChild3;
        }
        return numOfPaths;
    }

    static List<List<Adapter>> buildComplexSets(Map<Integer, Adapter> adaptorsWithHops) {
        List<List<Adapter>> allSets = new ArrayList<>();
        List<Adapter> currentSet = new ArrayList<>();
        for (Map.Entry<Integer, Adapter> withHop : adaptorsWithHops.entrySet()) {
            if (withHop.getValue().getPossibleHops() == 1) {
                if (currentSet.size() > 0) {
                    Adapter lastAdapter = currentSet.get(currentSet.size() -1); // get previous..
                    int lastNumber = lastAdapter.getValue();
                    if (adaptorsWithHops.containsKey(lastNumber +1)) currentSet.add(adaptorsWithHops.get(lastNumber + 1));
                    if (adaptorsWithHops.containsKey(lastNumber +2)) currentSet.add(adaptorsWithHops.get(lastNumber + 2));
                    if (adaptorsWithHops.containsKey(lastNumber +3)) currentSet.add(adaptorsWithHops.get(lastNumber + 3));
                    allSets.add(currentSet);
                }
                currentSet = new ArrayList<>();
            } else {
                currentSet.add(withHop.getValue());
            }
        }
        return allSets;
    }
}
