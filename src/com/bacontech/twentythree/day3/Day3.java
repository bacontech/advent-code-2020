package com.bacontech.twentythree.day3;

import com.bacontech.helpers.Answer;
import kotlin.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 extends Answer {

    private static String REGEX_NUMBER_GROUP = "(\\d+)";
    private static Pattern PAT_NUMBER_GROUP = Pattern.compile(REGEX_NUMBER_GROUP);

    private static String REGEX_CONTAINS_SYMBOL = "[^\\d\\.\\s]";

    private static Pattern PAT_CONTAINS_SYMBOL = Pattern.compile(REGEX_CONTAINS_SYMBOL);

    public static void main(String[] args) {

        String input = Day3.SAMPLE;

        Object part1 = solvePart1(input);
        printSolution("part1", part1);


        Object part2 = solvePart2(input);
        printSolution("part2", part2);
    }

    public static Object solvePart1(String filename) {
        Day3 answer = new Day3();
        List<String> lines = answer.parse(filename);


        return answer.processAnswerPart1(lines);
    }


    public Long processAnswerPart1(List<String> lines) {
        Long sum = 0L;


        // Find a number
        // Is it next to a symbol (even diagonally) (non-number, non-period, ??)
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            List<PartNumber> partNumbers = findNumberGroups(line, i);
            for (PartNumber partNumber : partNumbers) {
                Pair<Integer, Integer> indexes = determineIndexToCheck(partNumber);
                int beginningIndex = indexes.getFirst();
                int endingIndex = indexes.getSecond();
                // Check if adjacent to symbol;

                boolean symbolFound = false;

                //check above
                if (partNumber.lineNumber > 0 ) {
                    String lineToCheck = lines.get(partNumber.lineNumber -1);
                    boolean foundHere = hasSymbol(lineToCheck, beginningIndex, endingIndex);
                    if (foundHere) {
                        symbolFound = true;
                    }
                }
                //Check middle
                if (!symbolFound) {
                    String lineToCheck = lines.get(partNumber.lineNumber);
                    boolean foundHere = hasSymbol(lineToCheck, beginningIndex, endingIndex);
                    if (foundHere) {
                        symbolFound = true;
                    }
                }
                //Check bottom
                if (!symbolFound && partNumber.lineNumber + 1 < lines.size()) {
                    String lineToCheck = lines.get(partNumber.lineNumber +1);
                    boolean foundHere = hasSymbol(lineToCheck, beginningIndex, endingIndex);
                    if (foundHere) {
                        symbolFound = true;
                    }
                }
                if (symbolFound) {
                    sum += partNumber.number;
                }

            }
        }

        return sum;
    }

    private static boolean hasSymbol(String lineToCheck, int beginningIndex, int endingIndex) {
        String charsToCheck = lineToCheck.substring(beginningIndex, endingIndex);
        Matcher matcher = PAT_CONTAINS_SYMBOL.matcher(charsToCheck);
        boolean found = matcher.find();
        return found;
    }

    public static Pair<Integer, Integer> determineIndexToCheck(PartNumber partNumber) {
        return determineIndexToCheck(partNumber.x, partNumber.y, partNumber.line.length());
    }

    public static Pair<Integer, Integer> determineIndexToCheck(Integer x, Integer y, Integer lineLength) {
        Integer beginningIndex = x > 0 ? x -1 : x;
        Integer endingIndex = y < lineLength ? y + 1 : y;
        return new Pair<>(beginningIndex, endingIndex);
    }

    public static List<PartNumber> findGears(String line, String previousLine, String nextLine, Integer lineNumber) {
        System.out.println("Checking Line");
        System.out.println(line);
        List<PartNumber> gears = new ArrayList<>();
        boolean keepSearching = true;
        int skippedChars = 0;
        do {
            Integer numberOfAdjacentNumbers = 0;
            String stringToCheck = line.substring(skippedChars);
            System.out.println(stringToCheck);
            if (stringToCheck.contains("*")) {
                int index = line.indexOf("*", skippedChars);
                System.out.println("Index found: " + index);
                Pair<Integer, Integer> indexesToCheck = determineIndexToCheck(index, index, line.length());
                Integer numbersAbove = numberOfNumbers(previousLine, indexesToCheck.getFirst(), indexesToCheck.getSecond());
                numberOfAdjacentNumbers += numbersAbove;
                boolean numbersLeft = false ;
                if (index > 0) {
                    //check left
                    if (Character.isDigit(line.charAt(index -1))) {
                        numberOfAdjacentNumbers++;
                        numbersLeft = true;
                    }
                }
                boolean numbersRight = false;
                if (index +1 < line.length()) {
                    if (Character.isDigit(line.charAt(index+1))) {
                        numberOfAdjacentNumbers++;
                        numbersRight = true;
                    }
                }
                Integer numbersBelow = numberOfNumbers(nextLine, indexesToCheck.getFirst(), indexesToCheck.getSecond());
                numberOfAdjacentNumbers +=numbersBelow;

                if (numberOfAdjacentNumbers == 2) {
                    // This is a gear
                    PartNumber gear = new PartNumber();
                    gear.line = line;
                    gear.x = index;
                    gear.y = index;
                    gear.lineNumber = lineNumber;
                    gear.previousLine = previousLine;
                    gear.nextLine = nextLine;
                    gear.hasNumbersAbove = numbersAbove;
                    gear.hasNumbersBelow = numbersBelow;
                    gear.hasNumbersLeft = numbersLeft ? 1 : 0;
                    gear.hasNumbersRight = numbersRight ? 1 : 0;
                    gears.add(gear);
                }
                skippedChars = index + 1;
            } else {
                keepSearching = false;
            }
        } while (keepSearching);
        return gears;
    }

    static Integer numberOfNumbers(String line, Integer startIndex, Integer endIndex) {
        int num = 0;
        boolean inNumber = false;
        if (line == null) {
            return 0;
        }
        for (int i = startIndex; i <= endIndex; i++) {
            if (Character.isDigit(line.charAt(i))) {
                if (!inNumber) {
                    inNumber = true;
                    num++;
                }
            } else {
                inNumber = false;
            }
        }

        return num;
    }

    public static List<PartNumber> findNumberGroups(String line, int lineNum) {
        List<PartNumber> parts = new ArrayList<>();
        boolean keepSearching = true;
        String stringToSearch = line;
        int skippedChars = 0;
        int loopProtector = 0;
        do {

            Matcher matcher = PAT_NUMBER_GROUP.matcher(stringToSearch);
            boolean found = matcher.find();
            if (found) {
                PartNumber foundPart = new PartNumber();
                String numberFound = matcher.group(1);
                foundPart.line = line;
                foundPart.lineNumber = lineNum;
                foundPart.number = Long.parseLong(numberFound);
                foundPart.x = line.indexOf(numberFound, skippedChars); // Edge Case
                foundPart.y = foundPart.x + numberFound.length();

                int end = matcher.end(1);
                skippedChars += end;
                String newString = stringToSearch.substring(end);
                stringToSearch = newString;
                parts.add(foundPart);
            } else {
                keepSearching = false;
            }
            loopProtector++;
        } while(keepSearching && loopProtector < 20);


        return parts;
    }

    public static Object solvePart2(String filename) {
        Day3 answer = new Day3();
        List<String> lines = answer.parse(filename);
        return answer.processAnswerPart2(lines);
    }

    public Long processAnswerPart2(List<String> lines) {
        Long sum = 0L;


        // Find Gear Ratio - find all *s adjacent to exactly 2 numbers, multiply, then sum.
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String previous = i == 0 ? null : lines.get(i-1);
            String next = i+1 == lines.size() ? null : lines.get(i+1);
            System.out.println("Finding Gears");
            List<PartNumber> gears = findGears(line, previous, next, i);
            System.out.println("Gears Found");
            for (PartNumber gear : gears) {
                List<Long> numbers = new ArrayList<>();

                if (gear.hasNumbersAbove > 0) {
                    List<Long> numbersAbove = getNumbers(previous, gear.x);
                    numbers.addAll(numbersAbove);
                }
                if (gear.hasNumbersLeft > 0) {
                    Long numberLeft = getNumber(line, gear.x -1);
                    numbers.add(numberLeft);
                }
                if (gear.hasNumbersRight > 0) {
                    Long numberRight = getNumber(line, gear.x +1);
                    numbers.add(numberRight);
                }
                if (gear.hasNumbersBelow > 0) {
                    List<Long> numbersAbove = getNumbers(next, gear.x);
                    numbers.addAll(numbersAbove);
                }

                // Multiply together
                Long gearRatio = numbers.stream().reduce(1L, (a, b) -> a*b);
                sum += gearRatio;
            }
        }



        return sum;
    }

    public static List<Long> getNumbers(String line, Integer middleIndex) {
        List<Long> numbers = new ArrayList<>();
        Long middleNumber = getNumber(line, middleIndex);
        if (middleNumber != null) {
            numbers.add(middleNumber);
        } else {
            Long leftNumber = getNumber(line, middleIndex -1);
            if (leftNumber != null) {
                numbers.add(leftNumber);
            }
            Long rightNumber = getNumber(line, middleIndex +1);
            if (rightNumber != null) {
                numbers.add(rightNumber);
            }
        }

        return numbers;
    }
    public static Long getNumber(String line, Integer startingIndex) {
        Character startingChar = line.charAt(startingIndex);
        if (!Character.isDigit(startingChar)) {
            return null;
        }

        int index = startingIndex;
        boolean foundStart = false;
        do {
            if (index == 0) {
                foundStart = true;
            } else {
                index = index -1;
                Character toCheck = line.charAt(index);
                if (!Character.isDigit(toCheck)) {
                    foundStart = true;
                    index = index + 1;
                }
            }
        } while (!foundStart);

        StringBuilder sb = new StringBuilder();
        boolean foundEnd = false;
        do {
            if (index + 1 > line.length()) {
                foundEnd = true;
            } else {
                Character toCheck = line.charAt(index);
                if (Character.isDigit(toCheck)) {
                    sb.append(toCheck);
                } else {
                    foundEnd = true;
                }
                index = index +1;
            }
        } while (!foundEnd);
        return Long.parseLong(sb.toString());
    }
}

