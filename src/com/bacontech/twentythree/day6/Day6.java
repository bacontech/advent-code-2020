package com.bacontech.twentythree.day6;

import com.bacontech.helpers.Answer;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day6 extends Answer {

    @Override
    public Object doPart1(List<String> lines) {
        Long marginMultiplied = 1L;

        List<Long> times = null;
        List<Long> distances = null;

        for (String line : lines) {
            if (line.startsWith("Time")) {
                times = Arrays.stream(line.substring(5).trim().split(" "))
                    .filter(StringUtils::isNotBlank)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            } else if (line.startsWith("Distance")) {
                distances = Arrays.stream(line.substring(9).trim().split(" "))
                    .filter(StringUtils::isNotBlank)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            }
        }

        List<RaceUnit> races = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            RaceUnit ru = new RaceUnit(times.get(i), distances.get(i));
            races.add(ru);
        }

        List<Long> winningCombos = new ArrayList<>();
        for (RaceUnit race : races) {
            Long waysToWin = 0L;
            for (int i = 0; i < race.distance; i++) {
                Long runTime = race.time - i;
                Long distanceRun = runTime * i;
                if (distanceRun > race.distance) {
                    System.out.println("Can win with " + i);
                    waysToWin++;
                }
            }
            winningCombos.add(waysToWin);
        }

        marginMultiplied = winningCombos.stream().reduce(1L, (a, b) -> a*b);
        return marginMultiplied;
    }


    @Override
    public Object doPart2(List<String> lines) {
        Long numberOfWins = 1L;

        Long time = null;
        Long dist = null;

        for (String line : lines) {
            if (line.startsWith("Time")) {
                String timeStr = line.substring(5).replaceAll(" ", "");
                time = Long.parseLong(timeStr);

            } else if (line.startsWith("Distance")) {
                String distStr = line.substring(9).replaceAll(" ", "");
                dist = Long.parseLong(distStr);
            }
        }
        System.out.println(time);
        System.out.println(dist);

        List<RaceUnit> races = new ArrayList<>();
        races.add(new RaceUnit(time, dist));

        Long firstWinningNumber = null;
        Long firstWinningNumberRev = null;

        for (RaceUnit race : races) {
            Long currentNumber = 1L;
            do {
                Long runTime = race.time - currentNumber;
                Long distanceRun = runTime * currentNumber;
                if (distanceRun > race.distance) {
                    System.out.println("First Winning number: " + currentNumber);
                    firstWinningNumber = currentNumber;
                    break;
                }
                currentNumber++;
            } while (currentNumber < race.distance);

            currentNumber = race.time;
            do {
                Long runTime = race.time - currentNumber;
                Long distanceRun = runTime * currentNumber;
                if (distanceRun > race.distance) {
                    System.out.println("Rev Winning number: " + currentNumber);
                    firstWinningNumberRev = currentNumber;
                    break;
                }
                currentNumber--;
            } while (currentNumber > 0);
        }

        numberOfWins = firstWinningNumberRev - firstWinningNumber + 1L;
        return numberOfWins;
    }

    // millis, millimeters
    record RaceUnit(Long time, Long distance) {
    }
    record Boat(Long millimetersPerMillisecond) {
        // Starting 0 mm/ms
        // increases at 1 mm/ms for each ms held down
    }
}
