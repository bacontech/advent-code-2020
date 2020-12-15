package com.bacontech.day14;

import com.bacontech.helpers.BaconFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    private static String REGEX_MEM_VALUE = "^mem\\[(\\d+)] = (\\d+)$";
    private static Pattern PAT_MEM_VALUE = Pattern.compile(REGEX_MEM_VALUE);

    public static void main (String[] args) {
        List<Decoder> decoders = parse("day14.txt");
//        List<Decoder> decoders = parse("sample.txt");
//        List<Decoder> decoders = parse("sample2.txt");

//        Long answer = day14ModifyValue(decoders);
//        System.out.println(answer);
        // Part 1 - 15403588588538 (answer)

        Long answerPt2 = day14ModifyPosition(decoders);
        System.out.println(answerPt2);
        // Part 2 - 3219624143593 too low
    }


    static Long day14ModifyValue(List<Decoder> decoders) {
        HashMap<Integer, Long> finalState = new HashMap<>();
        for(Decoder decoder : decoders) {
            for(Integer memory : decoder.getMemory().keySet()) {
                Long decValue = decoder.getMemory().get(memory);
                String binaryValue = Long.toBinaryString(decValue);

                String binaryAfterMask = binaryAfterMask(decoder.getMask(), binaryValue);
                Long valueAfterMask = Long.parseLong(binaryAfterMask, 2);
                finalState.put(memory, valueAfterMask);
            }
        }

        System.out.println("Memory initialized");
        Long sum = finalState.values().stream()
            .mapToLong(Long::longValue).sum();
        return sum;
    }

    static Long day14ModifyPosition(List<Decoder> decoders) {
        HashMap<Long, Long> finalState = new HashMap<>();
        for(Decoder decoder : decoders) {
            for(Integer memory : decoder.getMemoryLocations()) {
                Long decValue = decoder.getMemory().get(memory);
                String binaryMemoryLocation = Integer.toBinaryString(memory);

                List<String> locationsAfterMask = binaryPositionsAfterMask(decoder.getMask(), binaryMemoryLocation);

                for (String posInBinary : locationsAfterMask) {
                    Long position = Long.parseLong(posInBinary, 2);
                    finalState.put(position, (long) decValue);
                }
            }
        }

        System.out.println("Memory initialized");
        Long sum = finalState.values().stream()
            .mapToLong(Long::longValue).sum();
        return sum;
    }

    static String binaryAfterMask(String mask, String binaryValue) {
        int padZeros = mask.length() - binaryValue.length();
        StringBuilder binaryWithPadBuilder = new StringBuilder();
        for (int i = 0; i < padZeros; i++) {
            binaryWithPadBuilder.append("0");
        }
        binaryWithPadBuilder.append(binaryValue);

        String valueWithPad = binaryWithPadBuilder.toString();
        if (valueWithPad.length() != mask.length()) {
            throw new RuntimeException("YOU BROKE PADDING");
        }
        StringBuilder valueWithMask = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '1') {
                valueWithMask.append('1');
            } else if (mask.charAt(i) == '0') {
                valueWithMask.append('0');
            } else {
                valueWithMask.append(valueWithPad.charAt(i));
            }
        }
        return valueWithMask.toString();
    }

    static List<String> binaryPositionsAfterMask(String mask, Integer value) {
        String binaryMemoryLocation = Integer.toBinaryString(value);

        return binaryPositionsAfterMask(mask, binaryMemoryLocation);
    }

    static List<String> binaryPositionsAfterMask(String mask, String binaryValue) {

        int padZeros = mask.length() - binaryValue.length();
        StringBuilder binaryWithPadBuilder = new StringBuilder();
        for (int i = 0; i < padZeros; i++) {
            binaryWithPadBuilder.append("0");
        }
        binaryWithPadBuilder.append(binaryValue);

        String valueWithPad = binaryWithPadBuilder.toString();
        if (valueWithPad.length() != mask.length()) {
            throw new RuntimeException("YOU BROKE PADDING");
        }


        StringBuilder valueWithMask = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '1') {
                valueWithMask.append('1');
            } else if (mask.charAt(i) == '0') {
                valueWithMask.append(valueWithPad.charAt(i));
            } else {
                valueWithMask.append('X');
            }
        }

        List<String> binaryPositions = generateBinaryPositionsFromMask(mask, valueWithMask.toString());
        return binaryPositions;
    }

    static List<String> generateBinaryPositionsFromMask(String mask, String valueWithMask) {
        List<String> positions = new ArrayList<>();

        if (!valueWithMask.contains("X")) {
            positions.add(valueWithMask);
            return positions;
        } else {
            String value0 = valueWithMask.replaceFirst("X", "0");
            positions.addAll(generateBinaryPositionsFromMask(mask, value0));
            String value1 = valueWithMask.replaceFirst("X", "1");
            positions.addAll(generateBinaryPositionsFromMask(mask, value1));
        }

        return positions;
    }

    static List<Decoder> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day14", filename);

        List<Decoder> allDecoders = new ArrayList<>();
        Decoder currentDecoder = null;

        for (String line : lines) {
            if (line.startsWith("mask")) {
                if (currentDecoder != null) {
                    allDecoders.add(currentDecoder);
                }
                currentDecoder = new Decoder();
                String mask = line.split("mask = ")[1];
//                System.out.println(mask);
                currentDecoder.setMask(mask);
            } else if (line.startsWith("mem")) {
                Matcher matcher = PAT_MEM_VALUE.matcher(line);
                if (matcher.find()) {
                    int memPosition = Integer.parseInt(matcher.group(1));
                    long memValue = Long.parseLong(matcher.group(2));
                    currentDecoder.getMemoryLocations().add(memPosition);
                    currentDecoder.getMemory().put(memPosition, memValue);
                }
            } else {
                System.out.println("You BROKE");
            }
        }
        if (currentDecoder != null) {
            allDecoders.add(currentDecoder);
        }

        return allDecoders;
    }
}
