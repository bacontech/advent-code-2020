package com.bacontech.twentythree.day4;

import com.bacontech.helpers.Answer;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4 extends Answer {

    @Override
    public Object doPart1(List<String> lines) {
        Long sumOfAllCards = 0L;


        for (String line : lines) {
            String cardIdentifier = line.split(":")[0].trim();
            String numberDetail = line.split(":")[1].trim();
            String winningNumbersStr = numberDetail.split("\\|")[0].trim();
            String cardNumbersStr = numberDetail.split("\\|")[1].trim();
            System.out.println(cardIdentifier);
            System.out.println("---");
//            System.out.println(winningNumbersStr);
//            System.out.println(cardNumbersStr);
            List<Long> winningNumbers = Arrays.stream(winningNumbersStr.split(" "))
                .map(String::trim)
                .filter(s -> !StringUtils.isBlank(s))
                .map(num -> {
//                    System.out.println(num);
                    return Long.parseLong(num);
                })
                .collect(Collectors.toList());
            List<Long> cardNumbers = Arrays.stream(cardNumbersStr.split(" "))
                .map(String::trim)
                .filter(s -> !StringUtils.isBlank(s))
                .map(num -> {
//                    System.out.println(num);
                    return Long.parseLong(num);
                })
                .collect(Collectors.toList());

//            System.out.println(winningNumbers);
//            System.out.println(cardNumbers);
            Long cardScore = determineCardScore(winningNumbers, cardNumbers);
            System.out.println("cardScore: " + cardScore);
            sumOfAllCards += cardScore;
        }

        return sumOfAllCards;
    }

    private Long determineCardScore(List<Long> winningNumbers, List<Long> cardNumbers) {
        Long points = 0L;
        for (Long cardNumber : cardNumbers) {
            if (winningNumbers.contains(cardNumber)) {
                // Winner
                if (points == 0L) {
                    points = 1L;
                } else {
                    points = points * 2;
                }
            }
        }
        return points;
    }

    private Long determineNumberOfMatches(List<Long> winningNumbers, List<Long> cardNumbers) {
        Long matches = 0L;
        for (Long cardNumber : cardNumbers) {
            if (winningNumbers.contains(cardNumber)) {
                matches += 1L;
            }
        }
        return matches;
    }

    @Override
    public Object doPart2(List<String> lines) {
        Long numberOfScratchCards = 0L;

        Map<Long, Long> extraCopies = new HashMap<>();

        for (String line : lines) {
            String cardIdentifier = line.split(":")[0].trim();
            Long cardNumber = Long.parseLong(cardIdentifier.substring(5).trim());
            String numberDetail = line.split(":")[1].trim();
            String winningNumbersStr = numberDetail.split("\\|")[0].trim();
            String cardNumbersStr = numberDetail.split("\\|")[1].trim();
            List<Long> winningNumbers = Arrays.stream(winningNumbersStr.split(" "))
                .map(String::trim)
                .filter(s -> !StringUtils.isBlank(s))
                .map(num -> {
                    return Long.parseLong(num);
                })
                .collect(Collectors.toList());
            List<Long> cardNumbers = Arrays.stream(cardNumbersStr.split(" "))
                .map(String::trim)
                .filter(s -> !StringUtils.isBlank(s))
                .map(num -> {
                    return Long.parseLong(num);
                })
                .collect(Collectors.toList());

            Long matches = determineNumberOfMatches(winningNumbers, cardNumbers);
            Long copiesOfThis = extraCopies.getOrDefault(cardNumber, 1L);

            if (matches > 0) {
                for (int i = 1; i <= matches; i++) {
                    Long currentCopies = extraCopies.getOrDefault(cardNumber + i, 1L);
                    extraCopies.put(cardNumber + i, currentCopies + copiesOfThis);
                }
            }
            System.out.println("copiesOfThis: " + copiesOfThis);
            System.out.println("matches: " + matches);
            numberOfScratchCards += copiesOfThis;
        }




        return numberOfScratchCards;
    }
}
