package com.bacontech.twentythree.day5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    Day5 day = new Day5();

    @Test
    void samplePart1() {
        Long expected = 35L;
        Object result = day.solvePart1Sample();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void realPart1() {
        Long expected = 650599855L;
        Object result = day.solvePart1Real();

        assertEquals(expected, result);
        System.out.println(result);
    }

    @Test
    void samplePart2() {
        Long expected = 46L;
        Object result = day.solvePart2Sample();

        assertEquals(expected, result);
        System.out.println(result);
    }

    // Ran in about 5 minutes... :Joy:
    @Test
    void realPart2() {
        Long expected = 1240035L;
        Object result = day.solvePart2Real();

        assertEquals(expected, result);
        System.out.println(result);
    }


    @Nested
    class ParseMapping {
        @Test
        void test() {
            Day5.GardenMappingUnit result = Day5.parseMapping("50 98 2");
            assertNotNull(result);
            assertEquals(50L, result.destStart());
            assertEquals(98L, result.sourceStart());
            assertEquals(2L, result.rangeLength());
        }
    }


    @Nested
    class ParseSeeds {
        @Test
        void test() {
            Map<Long, Long> seeds = new HashMap<>();
            Day5.parseSeeds(seeds, "seeds: 79 14 55 13");
            assertNotNull(seeds);
            assertEquals(4, seeds.size());
            assertTrue(seeds.containsKey(79L));
            assertTrue(seeds.containsKey(14L));
            assertTrue(seeds.containsKey(55L));
            assertTrue(seeds.containsKey(13L));
        }
    }


    @Nested
    class ParseSeedsPartTwo {
        @Test
        void test() {
            Map<Long, Long> seeds = new HashMap<>();
            Day5.parseSeedsPartTwo(seeds, "seeds: 79 14 55 13");
            assertNotNull(seeds);
            assertEquals(2, seeds.size());
            assertEquals(14L, seeds.get(79L));
            assertEquals(13L, seeds.get(55L));
        }
    }


    @Nested
    class GardenMappingObj {
        @Test
        void test() {
            Day5.GardenMappingUnit unit = new Day5.GardenMappingUnit(50L, 98L, 2L);
            Day5.GardenMappingUnit unit2 = new Day5.GardenMappingUnit(52L, 50L, 48L);

            Day5.GardenMapping gardenMapping = new Day5.GardenMapping(new ArrayList<>());
            gardenMapping.applyMappingUnit(unit);
            gardenMapping.applyMappingUnit(unit2);

            assertAll(
                () -> assertEquals(81L, gardenMapping.getMapped(79L)),
                () -> assertEquals(48L, gardenMapping.getMapped(48L)),
                () -> assertEquals(49L, gardenMapping.getMapped(49L)),
                () -> assertEquals(52L, gardenMapping.getMapped(50L)),
                () -> assertEquals(99L, gardenMapping.getMapped(97L)),
                () -> assertEquals(50L, gardenMapping.getMapped(98L)),
                () -> assertEquals(51L, gardenMapping.getMapped(99L)),
                () -> assertEquals(100L, gardenMapping.getMapped(100L))
            );

        }
    }
}