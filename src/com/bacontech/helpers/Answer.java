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
}
