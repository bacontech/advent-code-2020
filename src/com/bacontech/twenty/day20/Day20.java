package com.bacontech.twenty.day20;

import com.bacontech.helpers.BaconFileReader;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 {

    public static void main(String[] args) {
        Map<Long, PuzzlePiece> pieces = parse("day20.txt");
        System.out.println(pieces.size());
        System.out.println();
        day20(pieces);
    }

    static Long day20(Map<Long, PuzzlePiece> pieces) {
        // FLIPS are allowed...


        // Initial set of edge matches
        for (PuzzlePiece piece : pieces.values()) {
            for (Map.Entry<Integer, String> edge : piece.getEdges().entrySet()) {
                List<Long> possibleMatches = findEdgeMatches(piece.getId(), edge.getValue(), pieces);
                piece.getPossibleMatches().put(edge.getKey(), possibleMatches);
            }
            if (piece.getNumberOfEdges() > 1) {
                System.out.println(MessageFormat.format("Possible corner piece - id: {0}", piece.getId()));
            }
        }

        // Find 4 corner pieces - Will have 2 adjacent edges that may not match?
        // "but the outermost edges won't line up with any other tiles." is that guaranteed?


        // After assembling the puzzle, multiply the ids of the 4 corner tiles
        return 0L;
    }

    private static List<Long> findEdgeMatches(Long idOfCurrent, String edge, Map<Long, PuzzlePiece> pieces) {
        List<Long> possibleMatches = new ArrayList<>();
        for (PuzzlePiece piece : pieces.values()) {
            if (piece.getId().equals(idOfCurrent)) continue;
            boolean foundMatch = false;
            for (Map.Entry<Integer, String> otherEdge : piece.getEdges().entrySet()) {
                if (edge.equals(otherEdge.getValue())) {
                    if (foundMatch) {
                        System.out.println("Found second match");
                    }
                    possibleMatches.add(piece.getId());
                    foundMatch = true;
                }
            }
        }
        return possibleMatches;
    }

    static Map<Long, PuzzlePiece> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day20", filename);
        Map<Long, PuzzlePiece> pieces = new HashMap<>();

        Long id = null;
        char[][] puzzleData = null;
        int puzzleDataRow = 0;
        // Must be a blank line at the end of the file
        for(String line: lines) {
            if (line.contains("Tile")) {
                String number = line.split(" ")[1].replace(":", "").trim();
                id = Long.parseLong(number);
            } else if (line.isBlank()) {
                PuzzlePiece piece = new PuzzlePiece(id, puzzleData);
                pieces.put(piece.getId(), piece);

                id = null;
                puzzleData = null;
                puzzleDataRow = 0;
            } else {
                // Puzzle Piece data
                if (puzzleData == null) {
                    puzzleData = new char[10][10];
                }
                puzzleData[puzzleDataRow] = line.toCharArray();
                puzzleDataRow++;
            }
        }
        return pieces;
    }
}
