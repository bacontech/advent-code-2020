package com.bacontech.twenty.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @ParameterizedTest
    @CsvSource({
        "alex, a, 1",
        "aaa, a, 3",
        "ppppppppppplppppp, p, 16",
    })
    public void testCountLetters(String inputString, String letter, int expectedAnswer) {
        int matchingLetters = Day2.countLetters(inputString, letter);
        assertEquals(expectedAnswer, matchingLetters);
    }
}
