package com.bacontech.day14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @ParameterizedTest
    @CsvSource({
        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X, 11, 000000000000000000000000000001001001, 73",
        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X, 101, 000000000000000000000000000001100101, 101",
        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X, 0, 000000000000000000000000000001000000, 64",
    })
    void testMaskMath (String mask, Integer value, String expectedString, Integer expectedValue) {
        String binaryValue = Integer.toBinaryString(value);
        String result = Day14.binaryAfterMask(mask, binaryValue);
        assertEquals(expectedString, result);
        assertEquals(expectedValue, Integer.parseInt(result, 2));
    }

    @Test
    void testBinaryPositionsAfterMask2 () {
        String mask = "000000000000000000000000000000X1001X";
        int position = 42;
        List<String> result = Day14.binaryPositionsAfterMask(mask, position);
        assertEquals(4, result.size());

        String expectedAnswer1 = "000000000000000000000000000000011010";
        String expectedAnswer2 = "000000000000000000000000000000011011";
        String expectedAnswer3 = "000000000000000000000000000000111010";
        String expectedAnswer4 = "000000000000000000000000000000111011";
        assertTrue(result.contains(expectedAnswer1), expectedAnswer1);
        assertTrue(result.contains(expectedAnswer2), expectedAnswer2);
        assertTrue(result.contains(expectedAnswer3), expectedAnswer3);
        assertTrue(result.contains(expectedAnswer4), expectedAnswer4);
    }

    @Test
    void testBinaryPositionsAfterMask3 () {
        String mask = "00000000000000000000000000000000X0XX";
        int position = 26;
        List<String> result = Day14.binaryPositionsAfterMask(mask, position);
        assertEquals(8, result.size());

        String expectedAnswer1 = "000000000000000000000000000000010000";
        String expectedAnswer2 = "000000000000000000000000000000010001";
        String expectedAnswer3 = "000000000000000000000000000000010010";
        String expectedAnswer4 = "000000000000000000000000000000010011";
        assertTrue(result.contains(expectedAnswer1), expectedAnswer1);
        assertTrue(result.contains(expectedAnswer2), expectedAnswer2);
        assertTrue(result.contains(expectedAnswer3), expectedAnswer3);
        assertTrue(result.contains(expectedAnswer4), expectedAnswer4);

        String expectedAnswer = "000000000000000000000000000000011000";
        assertTrue(result.contains(expectedAnswer), expectedAnswer);

        expectedAnswer = "000000000000000000000000000000011001";
        assertTrue(result.contains(expectedAnswer), expectedAnswer);

        expectedAnswer = "000000000000000000000000000000011010";
        assertTrue(result.contains(expectedAnswer), expectedAnswer);

        expectedAnswer = "000000000000000000000000000000011011";
        assertTrue(result.contains(expectedAnswer), expectedAnswer);

    }
}
