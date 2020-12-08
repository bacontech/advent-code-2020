package com.bacontech.day7;

import com.bacontech.helpers.BaconFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    private static String REGEX_BAG_RULES2 = "(\\d+) ([\\w ]+,?)+?.";
    private static String REGEX_BAG_RULES = "(\\d+) ([\\w ]+) bag,?.?";
    private static Pattern PATTERN_BAGS_PREFIX = Pattern.compile(REGEX_BAG_RULES);

//    public static void main (String[] args) {
////        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day7", "sample.txt");
//        // Sample - Part 1 - expected 4
//        // Sample - Part 2 - expected 126
//
//        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day7", "day7.txt");
//
//        Long answer = day7(lines);
//
//
//    }

//    private static Long day7(List<String> lines) {
//        Map<String, BagRule> allRules = parseBagRules(lines);
//
//        return Long.valueOf(allRules.size());
//    }

//    public static Map<String, BagRule> parseBagRules(List<String> lines) {
//        Map<String, BagRule> allRules = new HashMap<>();
//        List<String> bagsWithCountFiguredOut = new ArrayList<>();
//        List<String> undecided = new ArrayList<>();
//
//
//        for (String line : lines) {
//            String[] split = line.split(" bags contain", 2);
//            String bagName = split[0];
//            String rules = split[1];
//
//            BagRule bagRule = new BagRule();
//            bagRule.setName(bagName);
////            System.out.println(bagName);
//            Matcher matcher = PATTERN_BAGS_PREFIX.matcher(rules);
//            while (matcher.find()) {
//                int numberOfBags = Integer.parseInt(matcher.group(1));
//                String name = matcher.group(2);
//                bagRule.addRule(name, numberOfBags);
//            }
//            if (bagRule.getRules().containsKey("shiny gold") || bagRule.getRules().keySet().stream().anyMatch(canContainGold::contains)) {
//                bagRule.setCanContainGoldBag(true);
//                canContainGold.add(bagName);
//            } else if (bagRule.getRules().size() == 0 || definitelyNoGold.containsAll(bagRule.getRules().keySet())) {
//                bagRule.setCanContainGoldBag(false);
//                bagRule.setContainsExactlyNBags(0);
//                definitelyNoGold.add(bagName);
//            } else {
//                notSure.add(bagName);
//            }
//            allRules.put(bagName, bagRule);
//        }
//
//        while (allRules.values().stream().anyMatch(bagRule -> bagRule.getCanContainGoldBag() == null)) {
//            List<String> removeFromNotSure = new ArrayList<>();
//            for (String ruleName : notSure) {
//                BagRule bagRule = allRules.get(ruleName);
//                if (bagRule.getRules().keySet().stream().anyMatch(canContainGold::contains)) {
//                    bagRule.setCanContainGoldBag(true);
//                    canContainGold.add(ruleName);
//                    removeFromNotSure.add(ruleName);
//                } else if (definitelyNoGold.containsAll(bagRule.getRules().keySet())) {
//                    bagRule.setCanContainGoldBag(false);
//                    definitelyNoGold.add(ruleName);
//                    removeFromNotSure.add(ruleName);
//                } else {
//                    // Do nothing
//                }
//
//            }
//            notSure.removeAll(removeFromNotSure);
////            System.out.println("Finished Loop");
//        }
//
//        Long numBagsThatCanGetGold = allRules.values().stream().filter(bagRule -> bagRule.getCanContainGoldBag()).count();
//
//        System.out.println("numBagsThatCanGetGold: " + numBagsThatCanGetGold);
//
//        return allRules;
//    }

    public static Map<String, BagRule> parseBagRulesPart1(List<String> lines) {
        Map<String, BagRule> allRules = new HashMap<>();
        List<String> canContainGold = new ArrayList<>();
        List<String> definitelyNoGold = new ArrayList<>();
        List<String> notSure = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split(" bags contain", 2);
            String bagName = split[0];
            String rules = split[1];

            BagRule bagRule = new BagRule();
            bagRule.setName(bagName);
//            System.out.println(bagName);
            Matcher matcher = PATTERN_BAGS_PREFIX.matcher(rules);
            while (matcher.find()) {
                int numberOfBags = Integer.parseInt(matcher.group(1));
                String name = matcher.group(2);
                bagRule.addRule(name, numberOfBags);
            }
            if (bagRule.getRules().containsKey("shiny gold") || bagRule.getRules().keySet().stream().anyMatch(canContainGold::contains)) {
                bagRule.setCanContainGoldBag(true);
                canContainGold.add(bagName);
            } else if (bagRule.getRules().size() == 0 || definitelyNoGold.containsAll(bagRule.getRules().keySet())) {
                bagRule.setCanContainGoldBag(false);
                definitelyNoGold.add(bagName);
            } else {
                notSure.add(bagName);
            }
            allRules.put(bagName, bagRule);
        }

        while (allRules.values().stream().anyMatch(bagRule -> bagRule.getCanContainGoldBag() == null)) {
            List<String> removeFromNotSure = new ArrayList<>();
            for (String ruleName : notSure) {
                BagRule bagRule = allRules.get(ruleName);
                if (bagRule.getRules().keySet().stream().anyMatch(canContainGold::contains)) {
                    bagRule.setCanContainGoldBag(true);
                    canContainGold.add(ruleName);
                    removeFromNotSure.add(ruleName);
                } else if (definitelyNoGold.containsAll(bagRule.getRules().keySet())) {
                    bagRule.setCanContainGoldBag(false);
                    definitelyNoGold.add(ruleName);
                    removeFromNotSure.add(ruleName);
                } else {
                    // Do nothing
                }

            }
            notSure.removeAll(removeFromNotSure);
//            System.out.println("Finished Loop");
        }

        Long numBagsThatCanGetGold = allRules.values().stream().filter(bagRule -> bagRule.getCanContainGoldBag()).count();

        System.out.println("numBagsThatCanGetGold: " + numBagsThatCanGetGold);

        return allRules;
    }


}
