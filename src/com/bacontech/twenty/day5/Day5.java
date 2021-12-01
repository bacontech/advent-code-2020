package com.bacontech.twenty.day5;

import com.bacontech.helpers.BaconFileReader;

import java.util.HashSet;
import java.util.List;

public class Day5 {
    public static void main (String[] args) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day5", "day5-input.txt");

        Long answer = day5(lines);
        System.out.println(answer);

    }

    //Pt-1: What is the highest seat id on a boarding pass?
    // Pt-1: 996
    //Pt-2 What is your seat?
    // (Its a "full flight" - but not all rows are used.)
    // There will be someone before and after you
    private static Long day5(List<String> lines) {
        long highSeatId = 0L;
        HashSet<Long> seats = new HashSet<>();

        for (String line : lines) {
            PlaneTicket ticket = new PlaneTicket(line);
            if (ticket.rowCode.isBlank() || ticket.colCode.isBlank()) {
                System.out.println("Illegal ticket - bad pattern");
            } else {
                long thisSeatId = ticket.getSeatId();
                seats.add(thisSeatId);
                if (thisSeatId > highSeatId) {
                    highSeatId = thisSeatId;
                }
            }
        }
        long lastSeatId = -100L;
        for (Long seat : seats) {
//            System.out.println(seat);
            long diff = seat - lastSeatId;
            if (diff == 2L) {

                System.out.println("Your seat: " + (seat -1));
                break;
            }
            lastSeatId = seat;
        }
        System.out.println("Highest Seat: " + highSeatId);
        return highSeatId;
    }



    //R or L
    static long findCol(String colCode) {
        return findNumberFromCode(colCode, 'L', 'R');
    }


    static long findNumberFromCode(String code, char lowLetter, char highLetter) {
        int max = (int) Math.pow(2, code.length());
        int min = 1;
        for (char thisLetter : code.toCharArray()) {
//            System.out.println("Starting: " + max + " " + min);
            int diff = (max - min) + 1;
            int adjust = diff / 2;
            if (thisLetter == lowLetter) {
                max = max - adjust;
            } else if (thisLetter == highLetter) {
                min = min + adjust;
            }
        }

//        System.out.println((max - 1));
        return (max - 1);
    }

    static long findRow(String rowCode) {
        return findNumberFromCode(rowCode, 'F', 'B');
    }
}
