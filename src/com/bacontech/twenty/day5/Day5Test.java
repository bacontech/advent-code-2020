package com.bacontech.twenty.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @ParameterizedTest
    @CsvSource({
        "FBFBBFFRLR, 357",
        "FFFFFFFLLL, 0",
        "BBBBBBBRRR, 1023",
    })
    void testGetId(String ticketCode, long expectedId) {
        PlaneTicket ticket = new PlaneTicket(ticketCode);

        assertEquals(expectedId, ticket.getSeatId());
    }

    @ParameterizedTest
    @CsvSource({
        "FBFBBFF, 44",
        "BFFFBBF, 70",
        "FFFBBBF, 14",
        "BBFFBBF, 102",
        "FFFFFFF, 0",
        "BBBBBBB, 127",
    })
    void testFindRow(String rowCode, long expectedRow) {
        assertEquals(expectedRow, Day5.findRow(rowCode));
    }

    @ParameterizedTest
    @CsvSource({
        "RLR, 5",
        "RRR, 7",
        "RLL, 4",
        "LLL, 0",
        "LLR, 1",
        "LRL, 2",
    })
    void testFindCol(String colCode, long expectedCol) {
        assertEquals(expectedCol, Day5.findCol(colCode));
    }
}
