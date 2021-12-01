package com.bacontech.twenty.day18;

import com.bacontech.helpers.BaconFileReader;

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18 {
    private static String REGEX_ADDITION_EXPRESSION = "(\\d+ \\+ \\d+)";
    private static Pattern PAT_ADDITION_EXPRESSION = Pattern.compile(REGEX_ADDITION_EXPRESSION);

    private static String REGEX_INNER_EXPRESSION = "\\(([+* \\d]+?)\\)";
    private static Pattern PAT_INNER_EXPRESSION = Pattern.compile(REGEX_INNER_EXPRESSION);

    private static String REGEX_IS_NUM = "^\\d+$";
    private static Pattern PAT_IS_NUM = Pattern.compile(REGEX_IS_NUM);

    public static void main(String[] args) {
        List<String> problems = parse("day18.txt");
//        Long sumOfAllProblems = day18pt1(problems);
//        System.out.println("Day 18 Pt1: " + sumOfAllProblems);]

        Long sumOfAllProblems2 = day18pt2(problems);
        System.out.println("Day 18 Pt2: " + sumOfAllProblems2);
        // 8952864356993
    }

    static Long day18pt2(List<String> problems) {
        return problems.stream()
            .map(Day18::solveLinePt2)
            .reduce(0L, Long::sum);
    }

    static Long day18pt1(List<String> problems) {
        return problems.stream()
            .map(Day18::solveLinePt1)
            .reduce(0L, Long::sum);
    }

    static Long solveLinePt2(String expression) {
        // inner parens first
        // then left to right
        int counter = 0;
        while (true) {
            // Find inner match
            Matcher matcher = PAT_INNER_EXPRESSION.matcher(expression);
            if (matcher.find()) {
                // A paren group exists
                String parenGroup = matcher.group(0);
                String innerExpression = matcher.group(1);
//                System.out.println(innerExpression);
                Long solvedInnerExpression = Day18.solveSimpleExpressionPt2(innerExpression);
                expression = expression.replace(parenGroup, solvedInnerExpression.toString());
//                System.out.println(solvedInnerExpression);
            } else {
                return Day18.solveSimpleExpressionPt2(expression); // there are no paren groups.
            }
            counter++;
            if (counter == 150) {
                throw new RuntimeException("Something bad");
            }
        }
    }

    // Addition comes before Multiplication
    static Long solveSimpleExpressionPt2(String simpleExpression) {
        int counter = 0;
        while (true) {
            // Find inner match
            Matcher matcher = PAT_ADDITION_EXPRESSION.matcher(simpleExpression);
            if (matcher.find()) {
                // A paren group exists
                String fullMatch = matcher.group(0);
                String additionExpression = matcher.group(1);
//                System.out.println(additionExpression);
                // This should work, cause its just addition
                Long solved = Day18.solveSimpleExpressionPt1(additionExpression);
                simpleExpression = simpleExpression.replace(fullMatch, solved.toString());
//                System.out.println(solved);
            } else {
                return Day18.solveSimpleExpressionPt1(simpleExpression); // there are adding Groups left.
            }
            counter++;
            if (counter == 150) {
                throw new RuntimeException("Something bad");
            }
        }
    }

    static Long solveLinePt1(String expression) {
        // inner parens first
        // then left to right
        int counter = 0;
        while (true) {
            // Find inner match
            Matcher matcher = PAT_INNER_EXPRESSION.matcher(expression);
            if (matcher.find()) {
                // A paren group exists
                String parenGroup = matcher.group(0);
                String innerExpression = matcher.group(1);
//                System.out.println(innerExpression);
                Long solvedInnerExpression = Day18.solveSimpleExpressionPt1(innerExpression);
                expression = expression.replace(parenGroup, solvedInnerExpression.toString());
//                System.out.println(solvedInnerExpression);
            } else {
                return Day18.solveSimpleExpressionPt1(expression); // there are no paren groups.
            }
            counter++;
            if (counter == 150) {
                throw new RuntimeException("Something bad");
            }
        }
    }

    static Long solveSimpleExpressionPt1(String simpleExpression) {
        String[] expressionParts = simpleExpression.split(" ");
        Stack<String> parts = new Stack<String>();

        for (int i = expressionParts.length -1; i >= 0; i--) {
            parts.add(expressionParts[i]);
//            parts.addElement();
        }

        Long runningTotal = 0L;
        String sign = "+";
        while (true) {
            String part = parts.pop().strip();
            if ("+".equals(part) || "*".equals(part)) {
                sign = part;
            } else {
                Matcher matcher = PAT_IS_NUM.matcher(part);
                if (matcher.matches()) {
                    // this is a number only.
                    Long thisNumber = Long.parseLong(part);
                    if ("+".equals(sign)) {
                        runningTotal += thisNumber;
                    } else {
                        runningTotal *= thisNumber;
                    }
                } else {
                    System.out.println("THIS IS NOT EXPECTED");
                }
            }
            if (parts.size() == 0) {
                break;
            }
        }

        return runningTotal;
    }


    static List<String> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day18", filename);
        return lines;
    }
}
