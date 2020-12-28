package com.bacontech.day17;

import com.bacontech.helpers.BaconFileReader;

import java.util.*;

public class Day17 {
    public static void main(String[] args) {
//        List<Cube> cubesAt0 = parse("day17.txt");
//        Long answerPt1 = day17pt1(cubesAt0, 6);
//        System.out.println(answerPt1);
//         Pt1: 247

        List<HyperCube> cubesAt00 = parsePt2("day17.txt");
        Long answerPt2 = day17pt2(cubesAt00, 6);
        System.out.println(answerPt2);

    }

    private static Long day17pt2(List<HyperCube> cubesAt0, int numberOfCycles) {
        HyperCubeMap map = new HyperCubeMap(cubesAt0);
        System.out.println("Initial Cube Map Created");

        for (int cycle = 0; cycle < numberOfCycles; cycle ++) {
            // build a new map
            HyperCubeMap thisCycleMap = new HyperCubeMap();

            for (HyperCube cube : map.getAllCubes()) {
                // Check each neighbor?
                for (int rowAdj = -1; rowAdj < 2; rowAdj++) {
                    for (int colAdj = -1; colAdj < 2; colAdj++) {
                        for (int zAdj = -1; zAdj < 2; zAdj++) {
                            for (int wAdj = -1; wAdj < 2; wAdj++) {
                                if (rowAdj == 0 && colAdj == 0 && zAdj == 0 && wAdj == 0) continue; // skip self
                                int row = cube.getRow() + rowAdj;
                                int col = cube.getCol() + colAdj;
                                int z = cube.getZIndex() + zAdj;
                                int w = cube.getWIndex() + wAdj;
                                if (thisCycleMap.getCube(row, col, z, w) != null) continue; // Already processed

                                HyperCube nearByCube = Optional.ofNullable(map.getCube(row, col, z, w))
                                    .orElse(new HyperCube(row, col, z, w, false));

                                int numberOfActiveNeighbors = map.numberOfActiveNeighbors(nearByCube);
                                boolean active = nearByCube.determineIfActiveNext(numberOfActiveNeighbors);
                                thisCycleMap.setCube(new HyperCube(row, col, z, w, active));
                            }
                        }
                    }
                }
            }

            System.out.println("Cycle finished: " + cycle);
            map = thisCycleMap; // Prep next cycle
        }
        return map.getAllCubes().stream().filter(HyperCube::getIsActive).count();
    }

    private static Long day17pt1(List<Cube> cubesAt0, int numberOfCycles) {
        CubeMap map = new CubeMap(cubesAt0, numberOfCycles);
        System.out.println("Initial Cube Map Created");

        for (int cycle = 0; cycle < numberOfCycles; cycle ++) {
            // build a new map
            CubeMap thisCycleMap = new CubeMap();

            for (Cube cube : map.getAllCubes()) {
                // Check each neighbor?
                for (int rowAdj = -1; rowAdj < 2; rowAdj++) {
                    for (int colAdj = -1; colAdj < 2; colAdj++) {
                        for (int zAdj = -1; zAdj < 2; zAdj++) {
                            if (rowAdj == 0 && colAdj == 0 && zAdj == 0) continue; // skip self
                            int row = cube.getRow() + rowAdj;
                            int col = cube.getCol() + colAdj;
                            int z = cube.getZIndex() + zAdj;
                            if (thisCycleMap.getCube(row, col, z) != null) continue; // Already processed

                            Cube nearByCube = Optional.ofNullable(map.getCube(row, col, z))
                                .orElse(new Cube(row, col, z, false));

                            int numberOfActiveNeighbors = map.numberOfActiveNeighbors(nearByCube);
                            boolean active = nearByCube.determineIfActiveNext(numberOfActiveNeighbors);
                            thisCycleMap.setCube(new Cube(row, col, z, active));
                        }
                    }
                }
            }

            System.out.println("Cycle finished: " + cycle);
            map = thisCycleMap; // Prep next cycle
        }
        return map.getAllCubes().stream().filter(Cube::getIsActive).count();
    }

    static List<Cube> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day17", filename);
        List<Cube> cubes = new ArrayList<>();

        int row = 0;
        for (String line : lines) {
            int col = 0;
            for (Character colChar : line.toCharArray()) {

                cubes.add(new Cube(row, col, 0, colChar));
                col++;
            }
            row++;
        }
        return cubes;
    }

    static List<HyperCube> parsePt2(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day17", filename);
        List<HyperCube> cubes = new ArrayList<>();

        int row = 0;
        for (String line : lines) {
            int col = 0;
            for (Character colChar : line.toCharArray()) {

                cubes.add(new HyperCube(row, col, 0, 0, colChar));
                col++;
            }
            row++;
        }
        return cubes;
    }


}
