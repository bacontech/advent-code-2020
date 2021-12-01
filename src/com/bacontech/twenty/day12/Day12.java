package com.bacontech.twenty.day12;

import com.bacontech.helpers.BaconFileReader;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    public static void main (String[] args) {
        List<BoatActions> boatActions = parse("day12.txt");
//        List<BoatActions> boatActions = parse("sample.txt");

        long part1 = day12Part1(boatActions);
        System.out.println(part1);
        // 441

        long part2 = day12Part2(boatActions);
        System.out.println(part2);
        // sample 286
        // 40014
    }

    private static long day12Part2(List<BoatActions> boatActions) {
        Boat boat = new Boat();
        for(BoatActions action : boatActions) {
            if (action.getAction() == 'N') {
                boat.wayPointNorth(action.getValue());

            } else if (action.getAction() == 'S') {
                boat.wayPointSouth(action.getValue());

            } else if (action.getAction() == 'E') {
                boat.wayPointEast(action.getValue());

            } else if (action.getAction() == 'W') {
                boat.wayPointWest(action.getValue());

            } else if (action.getAction() == 'L') {
                boat.wayPointLeft(action.getValue());

            } else if (action.getAction() == 'R') {
                boat.wayPointRight(action.getValue());

            } else if (action.getAction() == 'F') {
                boat.moveToWaypoint(action.getValue());

            } else {
                System.out.println("Something bad");
            }
        }

        System.out.println("Boat is at: ");
        System.out.println(boat.getXCoord());
        System.out.println(boat.getYCoord());
        return (long)Math.abs(boat.getXCoord()) + (long)Math.abs(boat.getYCoord());
    }

    //    Action N means to move north by the given value.
//    Action S means to move south by the given value.
//    Action E means to move east by the given value.
//    Action W means to move west by the given value.
//    Action L means to turn left the given number of degrees.
//    Action R means to turn right the given number of degrees.
//    Action F means to move forward by the given value in the direction the ship is currently facing.

    // 40248 too high
    private static long day12Part1(List<BoatActions> boatActions) {
        Boat boat = new Boat();
        for(BoatActions action : boatActions) {
            if (action.getAction() == 'N') {
                boat.north(action.getValue());
            } else if (action.getAction() == 'S') {
                boat.south(action.getValue());
            } else if (action.getAction() == 'E') {
                boat.east(action.getValue());
            } else if (action.getAction() == 'W') {
                boat.west(action.getValue());
            } else if (action.getAction() == 'L') {
                boat.left(action.getValue());
            } else if (action.getAction() == 'R') {
                boat.right(action.getValue());
            } else if (action.getAction() == 'F') {
                boat.forward(action.getValue());
            } else {
                System.out.println("Something bad");
            }
        }

        System.out.println("Boat is at: ");
        System.out.println(boat.getXCoord());
        System.out.println(boat.getYCoord());
        return (long)Math.abs(boat.getXCoord()) + (long)Math.abs(boat.getYCoord());
    }

    static List<BoatActions> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day12", filename);

        List<BoatActions> actions = new ArrayList<>();
        lines.forEach(l -> actions.add(new BoatActions(l)));
        return actions;
    }

}
