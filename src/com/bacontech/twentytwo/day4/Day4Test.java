package com.bacontech.twentytwo.day4;

import com.bacontech.twentytwo.day3.Day3;
import kotlin.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day4Test {

    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
//    @MethodSource
     @MethodSource("getAssignment") // default to same name.
    void getAssignment(String input, List<Integer> expected) {
        assertEquals(expected, Day4.getAssignment(input));
    }

    private static Stream<Arguments> getAssignment() {
        return Stream.of(
            arguments("2-4", List.of(2,3,4)),
            arguments("6-8", List.of(6,7,8)),
            arguments("2-8", List.of(2,3,4,5,6,7,8)),
            arguments("6-6", List.of(6))
        );
    }

    @Test
    void samplePart1() {
        Integer expected = 2;
        assertEquals(expected, Day4.solvePart1("sample.txt"));
    }

    @Test
    void actualPart1() {
        Integer expected = 528;
        assertEquals(expected, Day4.solvePart1("input.txt"));
    }

    @Test
    void samplePart2() {
        Integer expected = 4;
        assertEquals(expected, Day4.solvePart2("sample.txt"));
    }

    @Test
    void actualPart2() {
        Integer expected = 881;
        assertEquals(expected, Day4.solvePart2("input.txt"));
    }
}
