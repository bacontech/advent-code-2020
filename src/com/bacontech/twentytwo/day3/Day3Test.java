package com.bacontech.twentytwo.day3;

import kotlin.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void samplePart1() {
        Integer expected = 157;
        assertEquals(expected, Day3.solvePart1("sample.txt"));
    }

    @Test
    void actualPart1() {
        Integer expected = 7997;
        assertEquals(expected, Day3.solvePart1("input.txt"));
    }

    @Test
    void samplePart2() {
        Integer expected = 70;
        assertEquals(expected, Day3.solvePart2("sample.txt"));
    }

    @Test
    void actualPart2() {
        Integer expected = 2545;
        assertEquals(expected, Day3.solvePart2("input.txt"));
    }

    // Lowercase item types a through z have priorities 1 through 26.
    //Uppercase item types A through Z have priorities 27 through 52.

    @ParameterizedTest
    @CsvSource({
        "a,1",
        "b,2",
        "z,26",
        "A,27",
        "C,29",
        "Y,51",
        "Z,52",
    })
    void itemValue(char input, Integer expectedValue) {
        assertEquals(expectedValue, Day3.itemValue(input));
    }

    @ParameterizedTest
    @CsvSource({
        "abcdef,abc,def",
        "aaaaabbbbb,aaaaa,bbbbb",
    })
    void splitBag(String input, String bag1, String bag2) {
        Pair<String, String> result = Day3.splitBag(input);
        assertEquals(bag1, result.getFirst());
        assertEquals(bag2, result.getSecond());
    }

    @ParameterizedTest
    @CsvSource({
        "aaaabc,abc",
        "bbbcdeeef,bcdef",
        "xyzxyzxyz,xyz",
    })
    void itemValue(String input, String expectedValue) {
        assertEquals(expectedValue, Day3.uniqueLetters(input));
    }
}
