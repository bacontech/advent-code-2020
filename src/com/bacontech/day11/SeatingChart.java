package com.bacontech.day11;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeatingChart {
    public static final char SEATED = '#';
    public static final char EMPTY = 'L';
    public static final char AISLE = '.';

    final int numRows;
    final int numCols;
    final char[][] seats;

    SeatingChart(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        seats = new char[numRows][numCols];
    }

    public boolean isAisle(int row, int col) {
        return this.seats[row][col] == SeatingChart.AISLE;
    }
    public boolean isOccupied(int row, int col) {
        return this.seats[row][col] == SeatingChart.SEATED;
    }
    public boolean isFreeSeat(int row, int col) {
        return this.seats[row][col] == SeatingChart.EMPTY;
    }

    public void addSeatingRow(int row, String line) {
        if (line.length() > numCols) {
            throw new RuntimeException("Too many seats in this row");
        }
        char[] rowOfSeats = line.toCharArray();
        seats[row] = rowOfSeats;
    }

    public long numberOfSeatsCanSee(int row, int col) {
        List<Character> seatsSeen = new ArrayList<>();
        seatsSeen.add(findFirstSeatTowardsTopLeft(row, col));
        seatsSeen.add(findFirstSeatTowardsTop(row, col));
        seatsSeen.add(findFirstSeatTowardsTopRight(row, col));
        seatsSeen.add(findFirstSeatTowardsLeft(row, col));
        seatsSeen.add(findFirstSeatTowardsRight(row, col));
        seatsSeen.add(findFirstSeatTowardsBottomLeft(row, col));
        seatsSeen.add(findFirstSeatTowardsBottom(row, col));
        seatsSeen.add(findFirstSeatTowardsBottomRight(row, col));

        return seatsSeen.stream().filter(seat -> seat.equals(SeatingChart.SEATED)).count();
    }

    private Character findFirstSeatTowardsBottom(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            searchRow++;
            if (searchRow >= numRows || searchCol < 0) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }

    private char findFirstSeatTowardsTopLeft(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            // minus 1 to both
            searchRow--; searchCol--;
            if (searchRow < 0 || searchCol < 0) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }
    private char findFirstSeatTowardsBottomLeft(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            searchRow++; searchCol--;
            if (searchRow >= numRows || searchCol < 0) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }
    private char findFirstSeatTowardsBottomRight(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            searchRow++; searchCol++;
            if (searchRow >= numRows || searchCol >= numCols) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }

    private char findFirstSeatTowardsTop(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            // minus 1 to row only
            searchRow--;
            if (searchRow < 0 || searchCol < 0) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }

    private char findFirstSeatTowardsTopRight(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            // minus 1 to row; plus 1 to col
            searchRow--; searchCol++;
            if (searchRow < 0 || searchCol >= numCols) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }
    private char findFirstSeatTowardsRight(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            //plus 1 to col
            searchCol++;
            if (searchRow < 0 || searchCol >= numCols) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }

    private char findFirstSeatTowardsLeft(int row, int col) {
        char noSeatSeen = ' ';
        int searchRow = row;
        int searchCol = col;

        do {
            // minus 1 to both
            searchCol--;
            if (searchRow < 0 || searchCol < 0) {
                return noSeatSeen;
            }
            char thisTile = this.seats[searchRow][searchCol];
            if (thisTile != AISLE) {
                return thisTile;
            }
        } while (true);
    }

    public int numberOfPeopleSeatedAroundMe(int row, int col) {
        //    1 2 3
        //    4 X 5
        //    6 7 8
        int rowMinus1 = row - 1;
        int rowPlus1 = row + 1;

        int numberOfPeopleSeated = 0;

        // ROW -1
        if (rowMinus1 >= 0) {
            numberOfPeopleSeated += countSeatedInRow(rowMinus1, col, true);
        }
        // ROW
        numberOfPeopleSeated += countSeatedInRow(row, col, false);
        // ROW +1
        if (rowPlus1 < numRows) {
            numberOfPeopleSeated += countSeatedInRow(rowPlus1, col, true);
        }

        return numberOfPeopleSeated;
    }

    private int countSeatedInRow(int row, int col, boolean includeSelf) {
        int colMinus1 = col - 1;
        int colPlus1 = col + 1;
        int numberOfPeopleSeated = 0;

        if (colMinus1 >= 0) {
            if (isSeatedPerson(seats[row][colMinus1])) {
                numberOfPeopleSeated++;
            }
        }
        if (includeSelf) {
            if (isSeatedPerson(seats[row][col])) {
                numberOfPeopleSeated++;
            }
        }

        if (colPlus1 < numCols) {
            if (isSeatedPerson(seats[row][colPlus1])) {
                numberOfPeopleSeated++;
            }
        }
        return numberOfPeopleSeated;
    }

    static boolean isSeatedPerson(char possibleSeated) {
        if (possibleSeated == SEATED) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                toStringBuilder.append(this.seats[row][col]);
            }
            toStringBuilder.append("\n");
        }
        return toStringBuilder.toString();
    }
}
