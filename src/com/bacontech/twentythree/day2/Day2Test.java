package com.bacontech.twentythree.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    @Test
    void test() {
        Object result = Day2.solvePart1("sample.txt");
        assertEquals(8L, result);
    }
    @Test
    void actual() {
        Object result = Day2.solvePart1("input.txt");
        assertEquals(9759L, result);
    }

    @Test
    void testpt2() {
        Object result = Day2.solvePart2("sample.txt");
        assertEquals(2286L, result);
    }
    @Test
    void actualpt2() {
        Object result = Day2.solvePart2("input.txt");
        assertEquals(79315L, result);
    }
}
