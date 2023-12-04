package com.bacontech.helpers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Answer {

    protected static final String FULL_PROMPT = "input.txt";
    protected static final String SAMPLE = "sample.txt";


    public List<String> parseLines(String filename) {
        URL url = getClass().getResource(filename);
        try {
            return BaconFileReader.getFileLines(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Blew up in parse");
        }
    }

    public static void printSolution(String label, Object answer) {
        System.out.println("====");
        System.out.println(label);
        System.out.println(answer);
        System.out.println("====");
    }

    public List<String> parse(String filename) {
        List<String> lines = parseLines(filename);
        return lines;
    }

    public Object solvePart1Custom(String filename) {
        return doPart1(parseLines(filename));
    }

    public Object solvePart1Sample() {
        return doPart1(parseLines(SAMPLE));
    }

    public Object solvePart1Real() {
        return doPart1(parseLines(FULL_PROMPT));
    }

    public Object solvePart2Sample() {
        return doPart2(parseLines(SAMPLE));
    }

    public Object solvePart2Real() {
        return doPart2(parseLines(FULL_PROMPT));
    }

    public Object solvePart2Custom(String filename) {
        return doPart2(parseLines(filename));
    }

    public Object doPart1(List<String> lines) {
        return null;
    }

    public Object doPart2(List<String> lines) {
        return null;
    }
}
