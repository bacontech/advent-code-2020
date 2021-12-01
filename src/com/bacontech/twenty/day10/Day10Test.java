package com.bacontech.twenty.day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void part2SmallSample() {
        TreeSet<Integer> adapters = Day10.parseFileAndSort("small-sample.txt");
        Long numberOfArrangements = Day10.day10Part2(adapters);

        assertEquals(8L, numberOfArrangements);
        // 8
    }
    @Test
    void part2BiggerSample() {
        TreeSet<Integer> adapters = Day10.parseFileAndSort("sample.txt");
        Long numberOfArrangements = Day10.day10Part2(adapters);

        assertEquals(19208L, numberOfArrangements);
        // 19208
    }

    @Test
    void pathsFromSet() {
        TreeSet<Integer> input = new TreeSet<>();
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);

        Map<Integer, Adapter> map = Day10.buildAdapterMap(input);
        List<List<Adapter>> set = Day10.buildComplexSets(map);
        long paths = Day10.discoverNumberOfPaths(set.get(0));
        assertEquals(4, paths);
    }

    @ParameterizedTest
    @CsvSource({
        "4, 4,5,6,7",
        "2, 10,11,12",
        "7, 1,2,3,4,5",
        "2, 4,5,7",
        "3, 3,4,5,7",
        "6 , 2,3,4,5,7",
        "13, 1,2,3,4,5,6",
        "11, 1,2,3,4,5,7",
        "3, 10,11,12,14"
    })
    void pathsFromSet(int expected,  @AggregateWith(VarargsAggregator.class) Integer... integers) {
        TreeSet<Integer> input = new TreeSet<>();
        Arrays.stream(integers).forEach(i -> input.add(i));

        Map<Integer, Adapter> map = Day10.buildAdapterMap(input);
        List<List<Adapter>> set = Day10.buildComplexSets(map);
        long paths = Day10.discoverNumberOfPaths(set.get(0));
        assertEquals(expected, paths);
    }

    static class VarargsAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return accessor.toList().stream()
                .skip(context.getIndex())
                .map(String::valueOf)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        }
    }
}
