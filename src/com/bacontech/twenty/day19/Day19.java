package com.bacontech.twenty.day19;

import com.bacontech.helpers.BaconFileReader;
import com.bacontech.helpers.Tuple2;

import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    public static void main(String[] args) {
        List<String> lines = parse("day19.txt");
        Tuple2<Map<Integer, MonsterRule>, List<MonsterMessage>> parsed = parseForDay(lines);
        Long day19Part1 = day19(parsed.getFirst(), parsed.getSecond(), false);
        System.out.println(day19Part1);
        // 269

        Long day19Part2 = day19(parsed.getFirst(), parsed.getSecond(), true);
        System.out.println(day19Part2);
//         344 - too low
    }

    private static Long day19(Map<Integer, MonsterRule> rules, List<MonsterMessage> messages, boolean isPart2) {
        List<MonsterRule> unfinishedRules = new ArrayList<>(rules.values());
        Map<Integer, MonsterRule> finishedRules = new HashMap<>();

        // Find base letters
        for (MonsterRule rule : rules.values()) {
            if (rule.getRawRule().contains("\"")) {
                rule.setFinishedRule(rule.getRawRule().replaceAll("\"", ""));
                finishedRules.put(rule.getNumber(), rule);
                unfinishedRules.remove(rule);
            }
        }
        int loopNumber = 1;
        do {
            List<MonsterRule> nextUnfinishedRules = new ArrayList<>(unfinishedRules);
            ruleLoop:
            for (MonsterRule rule : nextUnfinishedRules) {
                if (rule.isConditional()) {
                    String[] subRules = rule.getRule1().strip().split(" ");
                    String finishedRule1 = "";
                    for (String subRule : subRules) {
                        int number = Integer.parseInt(subRule);

                        if (!finishedRules.containsKey(number)) {
                            continue ruleLoop;
                        }
                        finishedRule1 += finishedRules.get(number).getFinishedRule();
                    }

                    String[] subRules2 = rule.getRule2().strip().split(" ");
                    String finishedRule2 = "";
                    for (String subRule : subRules2) {
                        int number = Integer.parseInt(subRule);

                        if (!finishedRules.containsKey(number)) {
                            continue ruleLoop;
                        }
                        finishedRule2 += finishedRules.get(number).getFinishedRule();
                    }
                    // All subrules are finished
                    rule.setFinishedRule(MessageFormat.format("({0}|{1})", finishedRule1, finishedRule2));
                    unfinishedRules.remove(rule);
                    finishedRules.put(rule.getNumber(), rule);

                } else {
                    String[] subRules = rule.getRawRule().strip().split(" ");
                    String finishedRule = "";
                    for (String subRule : subRules) {
                        int number = Integer.parseInt(subRule);

                        if (!finishedRules.containsKey(number)) {
                            continue ruleLoop;
                        }
                        finishedRule += finishedRules.get(number).getFinishedRule();
                    }
                    // All subrules are finished

                    // Special rules for 8 and 11
                    if (isPart2) {
                        if (rule.getNumber() == 8) {
                            rule.setFinishedRule(MessageFormat.format("({0})+", finishedRule));
                        } else if (rule.getNumber() == 11) {
                            String fourtwo = finishedRules.get(42).getFinishedRule();
                            String threeone = finishedRules.get(31).getFinishedRule();
                            finishedRule = buildRule11(fourtwo, threeone);
                            rule.setFinishedRule(finishedRule);
                        } else {
                            rule.setFinishedRule(MessageFormat.format("({0})", finishedRule));
                        }
                    } else {
                        rule.setFinishedRule(MessageFormat.format("({0})", finishedRule));
                    }

                    unfinishedRules.remove(rule);
                    finishedRules.put(rule.getNumber(), rule);
                }
            }
//            System.out.println("finished loop number " + loopNumber++);
        } while (unfinishedRules.size() > 0);


        // How many messages completely match rule 0?
        System.out.println("Rule 8");
        System.out.println(finishedRules.get(8).getFinishedRule());
        System.out.println("Rule 11");
        System.out.println(finishedRules.get(11).getFinishedRule());



        String regexForRule0 = finishedRules.get(0).getFinishedRule();
        Pattern patternForRule0 = Pattern.compile(regexForRule0);

        Long matches = 0L;
        for (MonsterMessage message : messages) {
            Matcher matcher = patternForRule0.matcher(message.getMessage());
            if (matcher.matches()) {
//                System.out.println(MessageFormat.format("Message matches rule 0: {0}", message.getMessage()));
                matches++;
            } else {
                // No match

            }
        }

        return matches;
    }

    // Super hacky.. but.. oh well :)
    private static String buildRule11(String fourtwo, String threeone) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(fourtwo);
        stringBuilder.append(threeone);
        stringBuilder.append("|");
        stringBuilder.append(fourtwo).append("{2}");
        stringBuilder.append(threeone).append("{2}");
        stringBuilder.append("|");
        stringBuilder.append(fourtwo).append("{3}");
        stringBuilder.append(threeone).append("{3}");
        stringBuilder.append("|");
        stringBuilder.append(fourtwo).append("{4}");
        stringBuilder.append(threeone).append("{4}");
        stringBuilder.append("|");
        stringBuilder.append(fourtwo).append("{5}");
        stringBuilder.append(threeone).append("{5}");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    // Part 2 Regex
    // Apparently WRONG
    // ^(((a(a(b(b(b(a(a|b)|ba)|a(bb))|a(a(ba|(a|b)b)|b(bb|aa)))|a(a((bb|aa)b|(ba|(a|b)b)a)|b(b(aa|ab)|a(ab|bb))))|b(a(b((ba|(a|b)b)a|(aa|b(a|b))b)|a((ba|aa)b|(bb|aa)a))|b(a(a(ba)|b(ba|aa))|b(a(ba|(a|b)b)|b(a(a|b)|ba)))))|b(b((((ab|bb)a|(ba)b)a|(a(ab)|b(ba|(a|b)b))b)b|((a(ba|aa)|b(aa|ab))b|((bb)a)a)a)|a(b(((aa|ab)a|((a|b)(a|b))b)a|(b(aa|ab)|a(ab|bb))b)|a(((a(a|b)|ba)b|(aa|b(a|b))a)b|(b(aa)|a(aa))a))))b|(b(a(a(b((bb)b|(ba|aa)a)|a((bb|a(a|b))a|(ab)b))|b(((aa)b|(bb|a(a|b))a)b|((ba|(a|b)b)a|(bb|ba)b)a))|b(a(b(a(aa)|b(bb|a(a|b)))|a((bb|a(a|b))(a|b)))|b(b(b(bb|a(a|b))|a(ab|bb))|a((bb|ba)(a|b)))))|a(b((a((bb)b|(ba|aa)a)|b(b(bb|a(a|b))|a(ab)))a|(a(b(bb)|a(aa|b(a|b)))|b((ab)a))b)|a((b((bb|a(a|b))b|(ba|(a|b)b)a)|a((ab)a|(aa|ab)b))a|(a(b(ba|aa)|a(aa))|b((ab)b|(ab)a))b)))a))+(((a(a(b(b(b(a(a|b)|ba)|a(bb))|a(a(ba|(a|b)b)|b(bb|aa)))|a(a((bb|aa)b|(ba|(a|b)b)a)|b(b(aa|ab)|a(ab|bb))))|b(a(b((ba|(a|b)b)a|(aa|b(a|b))b)|a((ba|aa)b|(bb|aa)a))|b(a(a(ba)|b(ba|aa))|b(a(ba|(a|b)b)|b(a(a|b)|ba)))))|b(b((((ab|bb)a|(ba)b)a|(a(ab)|b(ba|(a|b)b))b)b|((a(ba|aa)|b(aa|ab))b|((bb)a)a)a)|a(b(((aa|ab)a|((a|b)(a|b))b)a|(b(aa|ab)|a(ab|bb))b)|a(((a(a|b)|ba)b|(aa|b(a|b))a)b|(b(aa)|a(aa))a))))b|(b(a(a(b((bb)b|(ba|aa)a)|a((bb|a(a|b))a|(ab)b))|b(((aa)b|(bb|a(a|b))a)b|((ba|(a|b)b)a|(bb|ba)b)a))|b(a(b(a(aa)|b(bb|a(a|b)))|a((bb|a(a|b))(a|b)))|b(b(b(bb|a(a|b))|a(ab|bb))|a((bb|ba)(a|b)))))|a(b((a((bb)b|(ba|aa)a)|b(b(bb|a(a|b))|a(ab)))a|(a(b(bb)|a(aa|b(a|b)))|b((ab)a))b)|a((b((bb|a(a|b))b|(ba|(a|b)b)a)|a((ab)a|(aa|ab)b))a|(a(b(ba|aa)|a(aa))|b((ab)b|(ab)a))b)))a)((b(((b(b(bb|ba)|a(ab|bb))|a((aa|ab)(a|b)))b|((b(a(a|b)|ba)|a(bb))a|(a(bb|a(a|b))|b((a|b)(a|b)))b)a)a|(a((b(bb|a(a|b))|a(ab))a|(a(bb)|b(bb))b)|b(((ba|aa)a|(bb|a(a|b))b)a|((ab)b|(a(a|b)|ba)a)b))b)|a(b((b(b(aa)|a(aa))|a(a(ba|ab)|b(aa)))a|(a(b(ab)|a(ab|bb))|b(b(bb)|a((a|b)(a|b))))b)|a((b(b(bb|aa)|a(ba))|a((ab)a|(bb|aa)b))b|(a(a(aa)|b(bb|a(a|b)))|b(a(bb|aa)|b(aa|ab)))a)))b|((b((((aa|b(a|b))b|(ab)a)b|((aa|b(a|b))b|(ab|bb)a)a)b|((b(aa)|a(aa))b|(b(bb|ba)|a(ba))a)a)|a(a(((ab)b|(ab|bb)a)b|((ab)b|(a(a|b)|ba)a)a)|b(b((bb|a(a|b))a|(ab)b)|a((a(a|b)|ba)b|(ab|bb)a))))a|(b((a((aa|b(a|b))b|(ab|bb)a)|b(a(ba)|b(aa)))b|(((bb|aa)a|(a(a|b)|ba)b)a|((ba|aa)a|(aa)b)b)a)|a(((b(ba|(a|b)b)|a(bb|ba))a|(b(ab|bb)|a(bb|aa))b)a|(b((bb|ba)b|((a|b)(a|b))a)|a((bb|a(a|b))b|((a|b)(a|b))a))b))b)a))+$


    static List<String> parse(String filename) {
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day19", filename);
        return lines;
    }

    static Tuple2<Map<Integer, MonsterRule>, List<MonsterMessage>> parseForDay(List<String> lines) {
        Map<Integer, MonsterRule> rules = new HashMap<>();
        List<MonsterMessage> messages = new ArrayList<>();

        boolean readingRules = true;
        for (String line : lines) {
            if (line.isBlank()) {
                readingRules = false;
                continue;
            }
            if (readingRules) {
                String[] ruleParts = line.split(":");
                int ruleNum = Integer.parseInt(ruleParts[0]);
                String ruleString = ruleParts[1].trim();
                MonsterRule monsterRule = new MonsterRule(ruleNum, ruleString);
                rules.put(ruleNum, monsterRule);
            } else {
                messages.add(new MonsterMessage(line.trim()));
            }
        }
        return new Tuple2<>(rules, messages);
    }
}
