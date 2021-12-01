package com.bacontech.twenty.day15;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void test() {
        List<String> lines = Day15.parse("day15.txt");
//        List<String> sample = new ArrayList();
//            sample.add("0");
//            sample.add("3");
//            sample.add("6");

        Long answerPt1 = Day15.day15pt1(lines);
        assertEquals(1437692, answerPt1);
    }
}
