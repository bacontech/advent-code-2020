package com.bacontech.twentythree.day5;

import com.bacontech.helpers.Answer;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;

public class Day5 extends Answer {

    @Override
    public Object doPart1(List<String> lines) {
        Long lowestLocationNumberForSeed = 0L;


        // Seed num to location
        Map<Long, Long> seeds = new HashMap<>();

        // Parse all maps
        GardenMapping seedToSoil = new GardenMapping(new ArrayList<>());
        GardenMapping soilToFertilizer = new GardenMapping(new ArrayList<>());
        GardenMapping fertToWater = new GardenMapping(new ArrayList<>());
        GardenMapping waterToLight = new GardenMapping(new ArrayList<>());
        GardenMapping lightToTemperature = new GardenMapping(new ArrayList<>());
        GardenMapping tempToHumidity = new GardenMapping(new ArrayList<>());
        GardenMapping humToLocation = new GardenMapping(new ArrayList<>());

        GardenMapping activeMapping = new GardenMapping(new ArrayList<>());

        Map<String, GardenMapping> modeMap = new HashMap<>();
        modeMap.put("seed-to-soil", seedToSoil);
        modeMap.put("soil-to-fertilizer", soilToFertilizer);
        modeMap.put("fertilizer-to-water", fertToWater);
        modeMap.put("water-to-light", waterToLight);
        modeMap.put("light-to-temperature", lightToTemperature);
        modeMap.put("temperature-to-humidity", tempToHumidity);
        modeMap.put("humidity-to-location", humToLocation);

        String mode = null;
        System.out.println("Reading Maps");
        for (String line : lines) {

            if (line.trim().isEmpty()) {
                continue;
            }
            if (line.startsWith("seeds:")) {
                parseSeeds(seeds, line);
                continue;
            }
            if (line.trim().endsWith("map:")) {
                mode = determineMode(line);
                System.out.println("Setting mode: " + mode);
                activeMapping = modeMap.get(mode);
                continue;
            }

            // The rest are map coordinates
            GardenMappingUnit mappingUnit = parseMapping(line.trim());
            activeMapping.applyMappingUnit(mappingUnit);
        }
        System.out.println("Maps Read");

        for (Long seed : seeds.keySet()) {
            Long location = Optional.of(seed)
                .map(seedToSoil::getMapped)
                .map(soilToFertilizer::getMapped)
                .map(fertToWater::getMapped)
                .map(waterToLight::getMapped)
                .map(lightToTemperature::getMapped)
                .map(tempToHumidity::getMapped)
                .map(humToLocation::getMapped)
                .orElseThrow();
            System.out.println(location);
            seeds.put(seed, location);
        }

        lowestLocationNumberForSeed = seeds.values().stream().reduce(Math::min).orElseThrow();

        return lowestLocationNumberForSeed;
    }

    private String determineMode(String line) {
        if (line.startsWith("seed-to-soil")) {
            return "seed-to-soil";
        }
        if (line.startsWith("soil-to-fertilizer")) {
            return "soil-to-fertilizer";
        }
        if (line.startsWith("fertilizer-to-water")) {
            return "fertilizer-to-water";
        }
        if (line.startsWith("water-to-light")) {
            return "water-to-light";
        }
        if (line.startsWith("light-to-temperature")) {
            return "light-to-temperature";
        }
        if (line.startsWith("temperature-to-humidity")) {
            return "temperature-to-humidity";
        }
        if (line.startsWith("humidity-to-location")) {
            return "humidity-to-location";
        }
        throw new RuntimeException("Failed to set mode");
    }

    static void parseSeeds(Map<Long, Long> seeds, String line) {
        String seedStr = line.substring(6).strip();
        Arrays.stream(seedStr.split(" "))
            .filter(StringUtils::isNotBlank)
            .map(String::trim)
            .map(Long::parseLong)
            .forEach(seed -> seeds.put(seed, null));
    }

    static void parseSeedsPartTwo(Map<Long, Long> seeds, String line) {
        String seedStr = line.substring(6).strip();
        String[] seedMap = seedStr.split(" ");

        for (int i = 0; i < seedMap.length; i=i+2) {
            Long start = Long.parseLong(seedMap[i]);
            Long range = Long.parseLong(seedMap[i+1]);
            seeds.put(start, range);
        }
    }

    // Default: same to same - 10 to 10
    // Destination Start - source range start - range length
    static GardenMappingUnit parseMapping(String mapping) {
        if (mapping == null) {
            return null;
        }
        String[] split = mapping.trim().split(" ");
        Long destStart = Long.parseLong(split[0]);
        Long sourceStart = Long.parseLong(split[1]);
        Long range = Long.parseLong(split[2]);
        return new GardenMappingUnit(destStart, sourceStart, range);
    }


    @Override
    public Object doPart2(List<String> lines) {
        Long lowestLocationNumberForSeed = Long.MAX_VALUE;


        // Now a mapping of seeds
        Map<Long, Long> seeds = new HashMap<>();

        // Parse all maps
        GardenMapping seedToSoil = new GardenMapping(new ArrayList<>());
        GardenMapping soilToFertilizer = new GardenMapping(new ArrayList<>());
        GardenMapping fertToWater = new GardenMapping(new ArrayList<>());
        GardenMapping waterToLight = new GardenMapping(new ArrayList<>());
        GardenMapping lightToTemperature = new GardenMapping(new ArrayList<>());
        GardenMapping tempToHumidity = new GardenMapping(new ArrayList<>());
        GardenMapping humToLocation = new GardenMapping(new ArrayList<>());

        GardenMapping activeMapping = new GardenMapping(new ArrayList<>());

        Map<String, GardenMapping> modeMap = new HashMap<>();
        modeMap.put("seed-to-soil", seedToSoil);
        modeMap.put("soil-to-fertilizer", soilToFertilizer);
        modeMap.put("fertilizer-to-water", fertToWater);
        modeMap.put("water-to-light", waterToLight);
        modeMap.put("light-to-temperature", lightToTemperature);
        modeMap.put("temperature-to-humidity", tempToHumidity);
        modeMap.put("humidity-to-location", humToLocation);

        String mode = null;
        System.out.println("Reading Maps");
        for (String line : lines) {

            if (line.trim().isEmpty()) {
                continue;
            }
            if (line.startsWith("seeds:")) {
                parseSeedsPartTwo(seeds, line);
                continue;
            }
            if (line.trim().endsWith("map:")) {
                mode = determineMode(line);
                System.out.println("Setting mode: " + mode);
                activeMapping = modeMap.get(mode);
                continue;
            }

            // The rest are map coordinates
            GardenMappingUnit mappingUnit = parseMapping(line.trim());
            activeMapping.applyMappingUnit(mappingUnit);
        }
        System.out.println("Maps Read");

        for (Map.Entry<Long, Long> seedGroup : seeds.entrySet()) {
            Long seedStart = seedGroup.getKey();
            Long seedRange = seedGroup.getValue();

            for (int i = 0; i < seedRange; i++) {
                Long location = Optional.of(seedStart + i)
                    .map(seedToSoil::getMapped)
                    .map(soilToFertilizer::getMapped)
                    .map(fertToWater::getMapped)
                    .map(waterToLight::getMapped)
                    .map(lightToTemperature::getMapped)
                    .map(tempToHumidity::getMapped)
                    .map(humToLocation::getMapped)
                    .orElseThrow();
                if (location < lowestLocationNumberForSeed) {
                    lowestLocationNumberForSeed = location;
                }
            }


//            System.out.println(location);
//            seeds.put(seed, location);
        }

//        lowestLocationNumberForSeed = seeds.values().stream().reduce(Math::min).orElseThrow();

        return lowestLocationNumberForSeed;
    }

    record GardenMappingUnit(Long destStart, Long sourceStart, Long rangeLength) {
    }

    record GardenMapping(List<GardenMappingUnit> rules) {
        Long getMapped(Long source) {
            for (GardenMappingUnit r : rules) {
                Long start = r.sourceStart;
                Long end = r.sourceStart + r.rangeLength;
                if (source >= start && source < end) {
                    Long diff = source - start;
                    return r.destStart + diff;
                }
            }
            return source;
        }

        public void applyMappingUnit(GardenMappingUnit mappingUnit) {
            rules.add(mappingUnit);
        }
    }
}
