package com.bacontech.twentythree.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {
    Day4 day = new Day4();

    @Test
    void samplePart1() {
        Long expected = 13L;
        Object result = day.solvePart1Sample();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void realPart1() {
        Long expected = 23750L;
        Object result = day.solvePart1Real();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void samplePart2() {
        Long expected = 30L;
        Object result = day.solvePart2Sample();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void realPart2() {
        Long expected = 13261850L;
        Object result = day.solvePart2Real();

        assertEquals(expected, result);
        System.out.println(result);
    }
}