package com.bacontech.day17;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {

    @ParameterizedTest
    @CsvSource({
        "0,0,1",
        "0,1,0",
        "1,0,0",
        "1,1,0",
        "0,1,1",
        "1,0,1",
        "1,1,1",
        "0,0,-1",
        "0,-1,0",
        "-1,0,0",
        "-1,-1,0",
        "0,-1,-1",
        "-1,0,-1",
        "-1,-1,-1",
    })
    void neighborCheck(int row, int col, int z) {
        Cube one = new Cube(0,0,0, true);
        Cube two = new Cube(row, col, z, true);
        assertTrue(one.isNeighbor(two));
    }

    @ParameterizedTest
    @CsvSource({
        "0,0,2",
        "0,2,0",
        "2,0,0",
        "2,2,0",
        "0,2,2",
        "2,0,2",
        "2,2,2",
        "0,0,-2",
        "0,-2,0",
        "-2,0,0",
        "-2,-2,0",
        "0,-2,-2",
        "-2,0,-2",
        "-2,-2,-2",
    })
    void neighborCheck_False(int row, int col, int z) {
        Cube one = new Cube(0,0,0, true);
        Cube two = new Cube(row, col, z, true);
        assertFalse(one.isNeighbor(two));
    }
}
