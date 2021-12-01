package com.bacontech.helpers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class BaconFileReader {
    public static List<String> getFileLines(URL url) throws URISyntaxException {
        URI uri = url.toURI();
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(uri), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("file failed to load. usukbro");
            e.printStackTrace();
        }
        return lines;
    }


    public static List<String> getFileLines(String path1, String... path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path1, path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("file failed to load. usukbro");
            e.printStackTrace();
        }
        return lines;
    }
}
