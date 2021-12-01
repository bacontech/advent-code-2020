package com.bacontech.twenty.day20;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PuzzlePiece {

    // Puzzle Input
    private Long id;
    // Puzzle Input
    private char[][] rawPiece;


    private Map<Integer, String> edges = new HashMap<>();


    // For each edge, the ids of pieces that might match
    private Map<Integer, List<Long>> possibleMatches = new HashMap<>();

//    private char[][] turnedRight;
//    private char[][] turnedLeft;
//    private char[][] upsideDown;

    public PuzzlePiece(Long id, char[][] rawPiece) {
        if (id == null || rawPiece == null) {
            throw new RuntimeException("Something Wrong");
        }
        this.id = id;
        this.rawPiece = rawPiece;

        // Calculate Edges
        String edge = String.valueOf(this.rawPiece[0]);
        edges.put(0, edge); // Top

        edge = "";
        for(int i = 0; i < 10; i++) {
            edge += this.rawPiece[i][9];
        }
        edges.put(1, edge); // Right

        edge = String.valueOf(this.rawPiece[9]);
        edges.put(2, edge); // Bottom

        edge = "";
        for(int i = 0; i < 10; i++) {
            edge += this.rawPiece[i][0];
        }
        edges.put(3, edge); // Left

        possibleMatches.put(0, new ArrayList<>());
        possibleMatches.put(1, new ArrayList<>());
        possibleMatches.put(2, new ArrayList<>());
        possibleMatches.put(3, new ArrayList<>());
    }

    public int getNumberOfEdges() {
        int edges = 0;
        for (int i = 0; i < 4; i++) {
            if (possibleMatches.get(i).size() == 0) {
                edges++;
            }
        }
        return edges;
    }

}
