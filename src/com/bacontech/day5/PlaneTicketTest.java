package com.bacontech.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTicketTest {

    @ParameterizedTest
    @CsvSource({
        "FBFBBFFRLR, FBFBBFF, RLR",
        "FFFFFFFRRR, FFFFFFF, RRR",
        "BBBBBBBLLL, BBBBBBB, LLL",
    })
    void testParse(String code, String expectedRow, String expectedCol) {
        PlaneTicket ticket = new PlaneTicket(code);
        assertAll(
            () ->  assertEquals(expectedRow, ticket.rowCode),
            () ->  assertEquals(expectedCol, ticket.colCode)
        );

    }

}
