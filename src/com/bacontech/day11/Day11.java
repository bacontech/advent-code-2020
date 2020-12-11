package com.bacontech.day11;

import com.bacontech.helpers.BaconFileReader;

import java.util.Arrays;
import java.util.List;


public class Day11 {
    public static void main (String[] args) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day11", "day11.txt");
        SeatingChart seatingChart = parseToSeatingChart(lines);
//        sitAccordingToRulesPt1(seatingChart);
        // Pt1 - 2166
        sitAccordingToRulesPt2(seatingChart);
        // Pt2 - 1955
    }

    static void sitAccordingToRulesPt1(SeatingChart seatingChart) {
        System.out.println(seatingChart.toString());

        SeatingChart chartAfterRules = new SeatingChart(seatingChart.numRows, seatingChart.numCols);

        for (int row = 0; row < seatingChart.numRows; row++) {
            for (int col = 0; col < seatingChart.numCols; col++) {

                char newState;

                if (seatingChart.isAisle(row, col)) {
                    newState = SeatingChart.AISLE; // always an aisle
                } else if (seatingChart.isFreeSeat(row, col)) {
                    if (seatingChart.numberOfPeopleSeatedAroundMe(row, col) == 0) {
                        newState = SeatingChart.SEATED; // Sit
                    } else {
                        newState = SeatingChart.EMPTY; // Nah
                    }
                } else if (seatingChart.isOccupied(row, col)) {
                    if (seatingChart.numberOfPeopleSeatedAroundMe(row, col) >= 4) {
                        newState = SeatingChart.EMPTY; // Leave
                    } else {
                        newState = SeatingChart.SEATED; // Stay Seated
                    }
                } else {
                    throw new RuntimeException("Something Broke");
                }
                chartAfterRules.seats[row][col] = newState;
            }
        }
//
//        System.out.println("");
//        System.out.println(chartAfterRules.toString());

        boolean chartsAreIdentical = true;
        // Compare to previous
        rowForLoop:
        for (int row = 0; row < seatingChart.numRows; row++) {
            for (int col = 0; col < seatingChart.numCols; col++) {
                if (seatingChart.seats[row][col] != chartAfterRules.seats[row][col]) {
                    chartsAreIdentical = false;
                    break rowForLoop;
                }
            }
        }
        if (chartsAreIdentical) {
            System.out.println("We have reached our final seating state");
            // Part 1 - How many seats end up occupied?

            long numberOfPeopleSeated = Arrays.stream(seatingChart.seats)
                .map(String::valueOf) // a row is now a string
                .map(row -> row.replaceAll("[^#]", ""))  //Only seated
                .map(seated -> seated.length())  // Number seated
                .reduce(0, (a, b) -> a + b);
            System.out.println(numberOfPeopleSeated + " of people are seated");
        } else {
            // Another round..
            sitAccordingToRulesPt1(chartAfterRules);
        }
    }

    static void sitAccordingToRulesPt2(SeatingChart seatingChart) {
        System.out.println(seatingChart.toString());

        SeatingChart chartAfterRules = new SeatingChart(seatingChart.numRows, seatingChart.numCols);

        for (int row = 0; row < seatingChart.numRows; row++) {
            for (int col = 0; col < seatingChart.numCols; col++) {

                char newState;

                if (seatingChart.isAisle(row, col)) {
                    newState = SeatingChart.AISLE; // always an aisle
                } else if (seatingChart.isFreeSeat(row, col)) {
                    if (seatingChart.numberOfSeatsCanSee(row, col) == 0) {
                        newState = SeatingChart.SEATED; // Sit
                    } else {
                        newState = SeatingChart.EMPTY; // Nah
                    }
                } else if (seatingChart.isOccupied(row, col)) {
                    if (seatingChart.numberOfSeatsCanSee(row, col) >= 5) {
                        newState = SeatingChart.EMPTY; // Leave
                    } else {
                        newState = SeatingChart.SEATED; // Stay Seated
                    }
                } else {
                    throw new RuntimeException("Something Broke");
                }
                chartAfterRules.seats[row][col] = newState;
            }
        }
//
//        System.out.println("");
//        System.out.println(chartAfterRules.toString());

        boolean chartsAreIdentical = true;
        // Compare to previous
        rowForLoop:
        for (int row = 0; row < seatingChart.numRows; row++) {
            for (int col = 0; col < seatingChart.numCols; col++) {
                if (seatingChart.seats[row][col] != chartAfterRules.seats[row][col]) {
                    chartsAreIdentical = false;
                    break rowForLoop;
                }
            }
        }
        if (chartsAreIdentical) {
            System.out.println("We have reached our final seating state");
            // Part 1 - How many seats end up occupied?

            long numberOfPeopleSeated = Arrays.stream(seatingChart.seats)
                .map(String::valueOf) // a row is now a string
                .map(row -> row.replaceAll("[^#]", ""))  //Only seated
                .map(seated -> seated.length())  // Number seated
                .reduce(0, (a, b) -> a + b);
            System.out.println(numberOfPeopleSeated + " of people are seated");
        } else {
            // Another round..
            sitAccordingToRulesPt2(chartAfterRules);
        }
    }

    static SeatingChart parseToSeatingChart(List<String> lines) {
        int numRows = lines.size();
        int numCols = lines.get(0).length();

        // We are told to assume perfect grid
        SeatingChart seatingChart = new SeatingChart(numRows, numCols);
        for(int row = 0; row < numRows; row++) {
            String rowAsString = lines.get(row);
            seatingChart.addSeatingRow(row, rowAsString);
        }
        return seatingChart;
    }
}
