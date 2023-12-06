package com.bacontech.twentythree.day6;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {
    Day6 day = new Day6();

    @Test
    void samplePart1() {
        Long expected = 288L;
        Object result = day.solvePart1Sample();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void realPart1() {
        Long expected = 114400L;
        Object result = day.solvePart1Real();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void samplePart2() {
        Long expected = 71503L;
        Object result = day.solvePart2Sample();

        assertEquals(expected, result);
        System.out.println(result);
    }

    // Ran in about 5 minutes... :Joy:
    @Test
    void realPart2() {
        Long expected = 21039729L;
        Object result = day.solvePart2Real();

        assertEquals(expected, result);
        System.out.println(result);
    }


}