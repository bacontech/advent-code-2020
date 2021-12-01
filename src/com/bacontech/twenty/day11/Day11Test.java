package com.bacontech.twenty.day11;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Day11Test {


    @Test
    void testSample() {
        List<String> lines = sampleData();
        SeatingChart chart = Day11.parseToSeatingChart(lines);
        Day11.sitAccordingToRulesPt1(chart);
    }

    private List<String> sampleData() {
        List<String> sample = new ArrayList<>();
        sample.add("L.LL.LL.LL");
        sample.add("LLLLLLL.LL");
        sample.add("L.L.L..L..");
        sample.add("LLLL.LL.LL");
        sample.add("L.LL.LL.LL");
        sample.add("L.LLLLL.LL");
        sample.add("..L.L.....");
        sample.add("LLLLLLLLLL");
        sample.add("L.LLLLLL.L");
        sample.add("L.LLLLL.LL");
        return sample;
    }

}
