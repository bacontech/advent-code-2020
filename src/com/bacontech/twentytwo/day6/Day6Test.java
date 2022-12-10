package com.bacontech.twentytwo.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @ParameterizedTest
    @CsvSource({
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11",
    })
    void part1(String input, Integer expected) {
        Day6 day6 = new Day6();
        assertEquals(expected, day6.findMessage(input, 4));
    }
    @ParameterizedTest
    @CsvSource({
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26",
    })
    void part2(String input, Integer expected) {
        Day6 day6 = new Day6();
        assertEquals(expected, day6.findMessage(input, 14));
    }

}