package com.bacontech.twenty.day17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HyperCubeMap {
    // w, z,   x,   y == Active
    // w, z, row, col == Active
    private Map<Integer, Map<Integer, Map<Integer, Map<Integer, HyperCube>>>> map = new HashMap<>();

    public HyperCubeMap() {

    }
    public HyperCubeMap(List<HyperCube> cubesAt0) {
        for (HyperCube cube: cubesAt0) {
            setCube(cube);
        }
    }

    public void setCube(HyperCube cube) {
        setCube(cube.getRow(), cube.getCol(), cube.getZIndex(), cube.getWIndex(), cube);
    }
    private void setCube(int row, int col, int logicalZ, int w, HyperCube cube) {
        Map<Integer, Map<Integer, Map<Integer, HyperCube>>> zxyMapAtW = map.computeIfAbsent(w, k -> new HashMap<>());
        Map<Integer, Map<Integer, HyperCube>> xyMapAtZ = zxyMapAtW.computeIfAbsent(logicalZ, k -> new HashMap<>());

        Map<Integer, HyperCube> cubeMapAtZRow = xyMapAtZ.computeIfAbsent(row, k -> new HashMap<>());

        cubeMapAtZRow.put(col, cube);
    }

    public HyperCube getCube(int row, int col, int logicalZ, int w) {
        return map.getOrDefault(w, new HashMap<>())
            .getOrDefault(logicalZ, new HashMap<>())
            .getOrDefault(row, new HashMap<>())
            .get(col);
    }

    public List<HyperCube> getAllCubes() {
        return map.values()
            .stream()
            .flatMap(cube -> cube.values().stream())
            .flatMap(slice -> slice.values().stream())
            .flatMap(row -> row.values().stream())
            .collect(Collectors.toList());
    }

    public int numberOfActiveNeighbors(HyperCube cube) {
        int activeCount = 0;
        for (int rowAdj = -1; rowAdj < 2; rowAdj++) {
            for (int colAdj = -1; colAdj < 2; colAdj++) {
                for (int zAdj = -1; zAdj < 2; zAdj++) {
                    for (int wAdj = -1; wAdj < 2; wAdj++) {
                        if (rowAdj == 0 && colAdj == 0 && zAdj == 0 && wAdj == 0) continue; // skip self
                        int row = cube.getRow() + rowAdj;
                        int col = cube.getCol() + colAdj;
                        int z = cube.getZIndex() + zAdj;
                        int w = cube.getWIndex() + wAdj;

                        HyperCube neighbor = Optional.ofNullable(this.getCube(row, col, z, w))
                            .orElse(new HyperCube(row, col, z, w, false));
                        if (neighbor.getIsActive()) {
                            activeCount++;
                        }
                    }

                }
            }
        }
        return activeCount;
    }
}
