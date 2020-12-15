package com.bacontech.day13;

import com.bacontech.helpers.BaconFileReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 {
    public static void main (String[] args) {
//        BusSchedule busSchedule = parse("sample.txt");
        BusSchedule busSchedule = parse("day13.txt");
        System.out.println(busSchedule.busIdsWithXs);
        // Part 2 - Sample - 1068781

//        long part1 = day13Part1(busSchedule);
//        System.out.println(part1);

        long part2 = day13Part2(busSchedule);
        System.out.println(part2);
    }
    static long day13Part2(BusSchedule busSchedule) {
        // Id, time to wait
        Map<Integer, Integer> busWaitMap = new HashMap<>();
        for (int i = 0 ; i < busSchedule.getBusIdsWithXs().size(); i++) {
            String busId = busSchedule.getBusIdsWithXs().get(i);
            if (busId.equalsIgnoreCase("x")) continue;
            busWaitMap.put(Integer.parseInt(busId), i);
        }

        long startAt = 0;
        long checkMultiple = 1;
        List<Rules> rules = new ArrayList<>();
        List<Long> candidates = new ArrayList<>();
        for (Integer busId : busWaitMap.keySet()) {
            rules.add(new Rules(busId, busWaitMap.get(busId)));
            System.out.println("Adding in " + busId);
            System.out.println("needs to wait: " + busWaitMap.get(busId));
            System.out.println();
            while (true) {
                startAt += checkMultiple;

//                System.out.println("Checking: " + startAt);
                if (currentRulesPass(startAt, rules)) {
                    System.out.println("Current Rules Pass at: " + startAt);
                    rules.forEach(r -> System.out.print(r + " "));
                    System.out.println();
                    candidates.add(startAt);
                }

                // Check all
                if (allWaitTimesAreHappy(startAt, busWaitMap)) {
                    System.out.println("You win");
                    System.out.println("Start At: " + startAt);
                    return startAt;
                }

                if (candidates.size() >= 3) {
                    checkMultiple = candidates.get(candidates.size()-1) - candidates.get(candidates.size()-2);

                    candidates = new ArrayList<>();
                    break;
                }
            }
        }
        return 0L;
    }

    private static boolean currentRulesPass(long startAt, List<Rules> rules) {
        for(Rules rule : rules) {
            if (!waitTimeIsOkay(rule.waitTime, rule.busId, startAt)) {
                return false;
            }
        }
        return true;
    }

    private static boolean allWaitTimesAreHappy(long startAt, Map<Integer, Integer> busWaitMap) {
        for(Integer busId : busWaitMap.keySet()) {
            Integer requiredWaitTime = busWaitMap.get(busId);
            if (!waitTimeIsOkay(requiredWaitTime, busId, startAt)) {
                return false;
            }
        }
        return true;
    }

    static boolean waitTimeIsOkay(Integer requiredWaitTime, Integer busId, long startAt) {
        long waiting = getWaitingMinutes(busId, startAt);
        if (requiredWaitTime == waiting) {
            return true;
        }
        if ((requiredWaitTime - waiting) % busId ==0) {
            return true;
        }
        return false;
    }

    private static long day13Part2_try1(BusSchedule busSchedule) {


        Integer largestBusId = busSchedule.busIds.stream().max(Integer::compareTo).orElseThrow();
        Integer largestNeedsToWait = busSchedule.busIdsWithXs.indexOf(largestBusId.toString());
        Integer secondLargest = busSchedule.busIds.stream().filter(id -> id != largestBusId).max(Integer::compareTo).orElseThrow();
        Integer secondWait = busSchedule.busIdsWithXs.indexOf(secondLargest.toString());
        Integer thirdLg = busSchedule.busIds.stream().filter(id -> (id != largestBusId && id !=secondLargest)).max(Integer::compareTo).orElseThrow();
        Integer thirdWait = busSchedule.busIdsWithXs.indexOf(thirdLg.toString());
        System.out.println("Largest ID: " + largestBusId);
        System.out.println("Needs to wait: " + largestNeedsToWait);
        System.out.println("");
        System.out.println("secondLargest ID: " + secondLargest);
        System.out.println("second Wait: " + secondWait);
        System.out.println("");

        Integer firstBus = busSchedule.busIds.get(0);
        Long checkMultiple = 0L;
        Long startAt = 0L;

        List<Long> possibleWinner = new ArrayList<>();

        int count = 0;
        Long possibleStart = (long) firstBus;
        nextStartTime:
        while (true) {
            long bigWaiting = getWaitingMinutes(largestBusId, possibleStart);
            long secondWaiting = getWaitingMinutes(secondLargest, possibleStart);
            long thirdWaiting = getWaitingMinutes(thirdLg, possibleStart);
            if (bigWaiting == largestNeedsToWait && secondWaiting == secondWait && thirdWaiting == thirdWait) {
                possibleWinner.add(possibleStart);
                count++;
                if (count >= 5) {
                    checkMultiple = possibleWinner.get(possibleWinner.size()-1) - possibleWinner.get(possibleWinner.size()-2);
                    startAt = possibleWinner.get(possibleWinner.size()-1);
                    break;
                }
                System.out.println("Possible winner: " + possibleStart);
                // Check all busses
                for (int i = 1; i < busSchedule.busIdsWithXs.size(); i++) {
                    String busId = busSchedule.getBusIdsWithXs().get(i);
                    if (busId.equalsIgnoreCase("x")) continue;
                    if (i != getWaitingMinutes(Integer.parseInt(busId), possibleStart)) {
                        possibleStart += firstBus;
                        continue nextStartTime;
                    }
                }
                System.out.println("No busses failed rules");
                System.out.println("Winner: " + possibleStart);

                break;
            }
            possibleStart += firstBus;
        }
        if (startAt == 0 || checkMultiple == 0) {
            System.out.println("Something went wrong");
            return 0L;
        }

        // Actually Check now.. with bigger loop cycle
        startAtLoop:
        while (true) {
            System.out.println("Possible winner: " + startAt);
            for (int i = 1; i < busSchedule.busIdsWithXs.size(); i++) {
                String busId = busSchedule.getBusIdsWithXs().get(i);
                if (busId.equalsIgnoreCase("x")) continue;
                if (i != getWaitingMinutes(Integer.parseInt(busId), startAt)) {
                    startAt += checkMultiple;
                    continue startAtLoop;
                }
            }
            System.out.println("No busses failed rules");
            System.out.println("Winner: " + startAt);
            return startAt;
        }
    }

    private static long day13Part1(BusSchedule busSchedule) {
        Integer earliestBusId = -1;
        long waitingMinutes = 9999;

        for (Integer busId : busSchedule.busIds) {
            long departureTime = (long) busSchedule.departureTime;

            long minutesAfter = getWaitingMinutes(busId, departureTime);
            if (minutesAfter < waitingMinutes) {
                earliestBusId = busId;
                waitingMinutes = minutesAfter;
            }
            System.out.println("Bus: " + busId + " will leave " + minutesAfter + " minutes after departure time");
        }

        System.out.println("Earliest Bus: " + earliestBusId);
        System.out.println("You will wait: " + waitingMinutes);
        Long answer = (long) waitingMinutes * earliestBusId;
        System.out.println("Answer: " + answer);

        // What is the ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus?


        return answer;
    }

    static long getWaitingMinutes(Integer busId, Long departureTime) {
        long mod = departureTime % busId;
        if (mod == 0) {
            return 0;
        }

        long minutesAfter = busId - mod;
        return minutesAfter;
    }

    static BusSchedule parse(String filename) {
        BusSchedule busSchedule = new BusSchedule();
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day13", filename);
        busSchedule.departureTime = Integer.parseInt(lines.get(0));

        String busses = lines.get(1);
        busSchedule.busIds = Arrays.stream(busses.split(","))
            .filter(busId -> !"x".equalsIgnoreCase(busId))
            .map(busId -> Integer.parseInt(busId))
            .collect(Collectors.toList());

        busSchedule.busIdsWithXs = Arrays.stream(busses.split(","))
            .collect(Collectors.toList());

        return busSchedule;
    }
}
