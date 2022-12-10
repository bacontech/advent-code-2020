package com.bacontech.twentytwo.day5;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void samplePart1() {
        assertEquals("CMZ", Day5.solvePart1("sample.txt"));
    }
    @Test
    void inputPart1() {
        assertEquals("QGTHFZBHV", Day5.solvePart1("input.txt"));
    }

    @Test
    void determineNumberOfStacks() {
        assertEquals(3, Day5.determineNumberOfStacks("    [D]    ", 3));
        assertEquals(9, Day5.determineNumberOfStacks("    [V] [G]             [H]        ",
            3));
    }


    @Test
    void samplePart2() {
        assertEquals("MCD", Day5.solvePart2("sample.txt"));
    }
    @Test
    void inputPart2() {
        assertEquals("MGDMPSZTM", Day5.solvePart2("input.txt"));
    }
}
