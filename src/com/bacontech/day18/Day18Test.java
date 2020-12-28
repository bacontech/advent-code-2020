package com.bacontech.day18;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    @ParameterizedTest
    @CsvSource({
        "1 + 2 * 3 + 4 * 5 + 6, 71",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 26",
        "5 + (8 * 3 + 9 + 3 * 4 * 3), 437",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 12240",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 13632",
    })
    void solveLine(String expression, Long expectedAnswer) {
        assertEquals(expectedAnswer, Day18.solveLinePt1(expression));
    }

    @ParameterizedTest
    @CsvSource({
        "1 + 2 * 3 + 4 * 5 + 6, 71",
        "2 * 3, 6",
        "5 + 6, 11",
        "8 + 6 * 4, 56"
    })
    void solveSimple(String expression, Long expectedAnswer) {
        assertEquals(expectedAnswer, Day18.solveSimpleExpressionPt1(expression));
    }

    @ParameterizedTest
    @CsvSource({
        "1 + 2 * 3 + 4 * 5 + 6, 231",
        "2 * 3, 6",
        "5 + 6, 11",
        "8 + 6 * 4, 56",
        "4 * 8 + 6, 56",
        "4 * 8, 32",
    })
    void solveSimplePt2(String expression, Long expectedAnswer) {
        assertEquals(expectedAnswer, Day18.solveSimpleExpressionPt2(expression));
    }


    @ParameterizedTest
    @CsvSource({
        "1 + 2 * 3 + 4 * 5 + 6, 231",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 46",
        "5 + (8 * 3 + 9 + 3 * 4 * 3), 1445",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 669060",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 23340",
    })
    void solveLinePt2(String expression, Long expectedAnswer) {
        assertEquals(expectedAnswer, Day18.solveLinePt2(expression));
    }

}
