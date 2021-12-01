package com.bacontech.twenty.day17;

import java.util.*;
import java.util.stream.Collectors;

public class CubeMap {
    // z,   x,   y == Active
    // z, row, col == Active
    private Map<Integer, Map<Integer, Map<Integer, Cube>>> map = new HashMap<>();

//    private Cube[][][] arrayMap;
    private int numberOfCycles;

    public CubeMap() {

    }
    public CubeMap(List<Cube> cubesAt0, int numberOfCycles) {
        this.numberOfCycles = numberOfCycles;

        for (Cube cube: cubesAt0) {
            setCube(cube);
        }
    }

    public void setCube(Cube cube) {
        setCube(cube.getRow(), cube.getCol(), cube.getZIndex(), cube);
    }
    private void setCube(int row, int col, int logicalZ, Cube cube) {
        Map<Integer, Map<Integer, Cube>> xyMapAtZ = map.computeIfAbsent(logicalZ, k -> new HashMap<>());

        Map<Integer, Cube> cubeMapAtZRow = xyMapAtZ.computeIfAbsent(row, k -> new HashMap<>());

        cubeMapAtZRow.put(col, cube);
    }

    public Cube getCube(int row, int col, int logicalZ) {
        return map.getOrDefault(logicalZ, new HashMap<>())
            .getOrDefault(row, new HashMap<>())
            .get(col);
    }

    public List<Cube> getAllCubes() {
        return map.values()
            .stream()
            .flatMap(slice -> slice.values().stream())
            .flatMap(row -> row.values().stream())
            .collect(Collectors.toList());
    }

    public int numberOfActiveNeighbors(Cube cube) {
        int activeCount = 0;
        for (int rowAdj = -1; rowAdj < 2; rowAdj++) {
            for (int colAdj = -1; colAdj < 2; colAdj++) {
                for (int zAdj = -1; zAdj < 2; zAdj++) {
                    if (rowAdj == 0 && colAdj == 0 && zAdj == 0) continue; // skip self
                    int row = cube.getRow() + rowAdj;
                    int col = cube.getCol() + colAdj;
                    int z = cube.getZIndex() + zAdj;

                    Cube neighbor = Optional.ofNullable(this.getCube(row, col, z))
                        .orElse(new Cube(row, col, z, false));
                    if (neighbor.getIsActive()) {
                        activeCount++;
                    }
                }
            }
        }
        return activeCount;
    }

    // logically, -6 to 6
    // in memory, actually 0-12.  Where 0 == -6
    // -6, -5, -4, -3, -2, -1, 0, 1, 2, 3,  4,  5,  6
    //  0,  1,  2,  3,  4,  5, 6, 7, 8, 9, 10, 11, 12
    private int getZIndex(int logical) {
        if (logical < -numberOfCycles || logical > numberOfCycles) {
            throw new IllegalArgumentException("out of z-index bounds");
        }
        return logical + numberOfCycles;
    }
}
