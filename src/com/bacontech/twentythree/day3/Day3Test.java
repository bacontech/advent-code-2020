package com.bacontech.twentythree.day3;

import kotlin.Pair;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void samplePart1() {
        Long expected = 4361L;
        Object result = Day3.solvePart1("sample.txt");
        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void actualPart1() {
        Long expected = 525911L;
        Object result = Day3.solvePart1("input.txt");
        assertEquals(expected, result);
        System.out.println(result);
    }
    @Nested
    class NumberOfNumbers {
        @Test
        void test() {
            Integer result = Day3.numberOfNumbers("1.1", 0, 2);
            assertEquals(2, result);
        }
        @Test
        void test2() {
            Integer result = Day3.numberOfNumbers("121", 0, 2);
            assertEquals(1, result);
        }
        @Test
        void test3() {
            Integer result = Day3.numberOfNumbers("...", 0, 2);
            assertEquals(0, result);
        }
        @Test
        void test4() {
            Integer result = Day3.numberOfNumbers("...123", 0, 2);
            assertEquals(0, result);
        }
        @Test
        void test5() {
            Integer result = Day3.numberOfNumbers("...123", 3, 5);
            assertEquals(1, result);
        }
    }

    @Nested
    class FindNumberGroups {
        @Test
        void numberMatcher() {
            List<PartNumber> result = Day3.findNumberGroups("467..114..", 0);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertAll(
                () -> assertEquals(467L, result.get(0).number),
                () -> assertEquals(0, result.get(0).x),
                () -> assertEquals(3, result.get(0).y)
            );
            assertAll(
                () -> assertEquals(114L, result.get(1).number),
                () -> assertEquals(5, result.get(1).x),
                () -> assertEquals(8, result.get(1).y)
            );
        }

        @Test
        void numberMatcher2() {
            List<PartNumber> result = Day3.findNumberGroups("467..467..", 0);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertAll(
                () -> assertEquals(467L, result.get(0).number),
                () -> assertEquals(0, result.get(0).x),
                () -> assertEquals(3, result.get(0).y)
            );
            assertAll(
                () -> assertEquals(467L, result.get(1).number),
                () -> assertEquals(5, result.get(1).x),
                () -> assertEquals(8, result.get(1).y)
            );
        }
    }


    @Test
    void samplePart2() {
        Long expected = 467835L;
        assertEquals(expected, Day3.solvePart2("sample.txt"));
    }

    @Test
    void actualPart2() {
        Long expected = 467835L;
        assertEquals(expected, Day3.solvePart2("input.txt"));
    }
    @Nested
    class GetNumbers {
        @Test
        void test() {
            List<Long> result = Day3.getNumbers("1009...", 3);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1009L, result.get(0));
        }
        @Test
        void test2() {
            List<Long> result = Day3.getNumbers("..1009...", 5);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1009L, result.get(0));
        }
        @Test
        void test3() {
            List<Long> result = Day3.getNumbers("..1009.1233..", 5);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1009L, result.get(0));
        }
        @Test
        void test4() {
            List<Long> result = Day3.getNumbers("..1009.1233..", 6);
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(1009L, result.get(0));
            assertEquals(1233L, result.get(1));
        }
        @Test
        void test5() {
            List<Long> result = Day3.getNumbers("..1009.1233", 7);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1233L, result.get(0));
        }
        @Test
        void test6() {
            List<Long> result = Day3.getNumbers("..1009.1233...", 7);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1233L, result.get(0));
        }
        @Test
        void test7() {
            List<Long> result = Day3.getNumbers("..1009.1233", 10);
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1233L, result.get(0));
        }
    }


    @Nested
    class GetNumber {
        @Test
        void test() {
            Long result = Day3.getNumber("1009...", 3);
            assertEquals(1009L, result);
        }
        @Test
        void test2() {
            Long result = Day3.getNumber("..1009...", 5);
            assertEquals(1009L, result);
        }
        @Test
        void test3() {
            Long result = Day3.getNumber("..1009.1233..", 5);
            assertEquals(1009L, result);
        }
        @Test
        void test4() {
            Long result = Day3.getNumber("..1009.1233..", 6);
            assertNull(result);
        }
        @Test
        void test5() {
            Long result = Day3.getNumber("..1009.1233", 7);
            assertEquals(1233L, result);
        }
        @Test
        void test6() {
            Long result = Day3.getNumber("..1009.1233...", 7);
            assertEquals(1233L, result);
        }
    }
}
