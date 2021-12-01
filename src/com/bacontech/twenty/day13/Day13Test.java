package com.bacontech.twenty.day13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    @ParameterizedTest
    @CsvSource({
        "5, 12, 3",
        "10, 12, 8",
        "20, 12, 8",
        "30, 12, 18",
        "7, 126, 0",
        "7, 127, 6",
        "7, 128, 5",
        "7, 129, 4",
        "7, 130, 3",
        "7, 131, 2",
        "7, 132, 1",
        "7, 133, 0",
    })
    void testMinutesAfter(int busId, long departureTime, long expectedMinutesAfter) {
        long willWait = Day13.getWaitingMinutes(busId, departureTime);
        assertEquals(expectedMinutesAfter, willWait);
    }

    @ParameterizedTest
    @CsvSource({
        "3, 19, 3417, true",
        "61, 17, 75, true",
        "61, 17, 76, false",
        "61, 17, 74, false",
    })
    void testWaitTimeIsOkay(Integer reqWaitTime, Integer busId, long startAt, boolean expected) {
        boolean isOkay = Day13.waitTimeIsOkay(reqWaitTime, busId, startAt);
        assertEquals(expected, isOkay);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "17,x,13,19 ~ 3417",
        "67,7,59,61 ~ 754018",
        "67,x,7,59,61 ~ 779210",
        "67,7,x,59,61 ~ 1261476",
        "1789,37,47,1889 ~ 1202161486",
        "7,13,x,x,59,x,31,19 ~ 1068781",
    }, delimiter = '~')
    void testDay13Part2_samples(String busses, Long expectedAnswer) {
        fullTest(busses, expectedAnswer);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17 ~ 526090562196173"
    }, delimiter = '~')
    void testDay13Part2(String busses, Long expectedAnswer) {
        fullTest(busses, expectedAnswer);
    }



    void fullTest(String busses, Long expectedAnswer) {
        BusSchedule schedule = new BusSchedule();
        schedule.busIds = Arrays.stream(busses.split(","))
            .filter(busId -> !"x".equalsIgnoreCase(busId))
            .map(busId -> Integer.parseInt(busId))
            .collect(Collectors.toList());

        schedule.busIdsWithXs = Arrays.stream(busses.split(","))
            .collect(Collectors.toList());

        long answer = Day13.day13Part2(schedule);
        assertEquals(expectedAnswer, answer);
    }
}
