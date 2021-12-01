package com.bacontech.twenty.day16;

import com.bacontech.helpers.BaconFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) {
        TrainDay16 trainDay16 = parse("day16.txt");

//        Long answerPt1 = day16pt1(trainDay16);
//        System.out.println(answerPt1);
        // Pt1: 20091

        Long answerPt2 = day16pt2(trainDay16);
        System.out.println(answerPt2);

    }

    // It doesn't matter which position corresponds to which field;
    // you can identify invalid nearby tickets by considering only whether
    // tickets contain values that are not valid for any field.
    // In this example, the values on the first nearby ticket are all valid
    // for at least one field. This is not true of the other three nearby tickets:
    // the values 4, 55, and 12 are are not valid for any field.
    // Adding together all of the invalid values produces your ticket scanning error rate: 4 + 55 + 12 = 71.

    // Consider the validity of the nearby tickets you scanned. What is your ticket scanning error rate?
    static Long day16pt1(TrainDay16 trainDay16) {
        Map<Integer, Boolean> isValid = new HashMap<>();
        List<Integer> invalidFields = new ArrayList<>();

        for (Rule rule : trainDay16.rules) {
            for(Range range : rule.ranges) {
                for (int i = range.low; i <= range.high; i++) {
                    isValid.put(i, true);
                }
            }
        }

        for (Ticket nearbyTicket : trainDay16.otherTickets) {
            for (Integer ticketValue: nearbyTicket.values.values()) {
                if (!isValid.containsKey(ticketValue)) {
                    invalidFields.add(ticketValue);
                }
            }
        }

        return invalidFields.stream().mapToLong(Integer::longValue).sum();
    }

    static Long day16pt2(TrainDay16 trainDay16) {

        Map<Integer, List<Integer>> valueToAllowedField = new HashMap<>();
        List<Ticket> validTickets = new ArrayList<>();

        for (Rule rule : trainDay16.rules) {
            for(Range range : rule.ranges) {
                for (int i = range.low; i <= range.high; i++) {
                    if (!valueToAllowedField.containsKey(i)) {
                        valueToAllowedField.put(i, new ArrayList<>());
                    }
                    valueToAllowedField.get(i).add(rule.ruleCode);
                }
            }
        }

        for (Ticket nearbyTicket : trainDay16.otherTickets) {
            boolean ticketIsValid = true;
            for (Integer ticketValue: nearbyTicket.values.values()) {
                if (!valueToAllowedField.containsKey(ticketValue)) {
                    ticketIsValid = false;
                    break;
                }
            }
            if (ticketIsValid) {
                validTickets.add(nearbyTicket); //discard invalid, only use valid tickets
            }
        }
        validTickets.add(trainDay16.myTicket); // include my ticket in rules discovery

        //Discover the rules
        Map<Integer, List<Integer>> fieldToPossibleRules = instantiateRules(trainDay16);

        for (Ticket validTicket : validTickets) {
            for (Map.Entry<Integer, Integer> ticketValue: validTicket.values.entrySet()) {
                int fieldNumber = ticketValue.getKey();
                int fieldValue = ticketValue.getValue();

                List<Integer> rulesOkayWithThisValue = valueToAllowedField.get(fieldValue);


                List<Integer> newPossibleRules = fieldToPossibleRules.get(fieldNumber)
                    .stream()
                    .filter(ruleCode -> rulesOkayWithThisValue.contains(ruleCode))
                    .collect(Collectors.toList());

                fieldToPossibleRules.put(fieldNumber, newPossibleRules);

            }
        }

        Map<Integer, Integer> finalizedFieldToRules = new HashMap<>();

        while (finalizedFieldToRules.size() < 20) {
            List<Map.Entry<Integer, List<Integer>>> readyToAssign = fieldToPossibleRules.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 1)
                .collect(Collectors.toList());

            for(Map.Entry<Integer, List<Integer>> entry : readyToAssign) {
                Integer fieldNumber = entry.getKey();
                if (finalizedFieldToRules.containsKey(fieldNumber)) {
                    System.out.println("Something went wrong - finalizing rules");
                }
                Integer ruleCode = entry.getValue().get(0);
                finalizedFieldToRules.put(fieldNumber, ruleCode);

                fieldToPossibleRules.entrySet()
                    .forEach(ftprEntry -> {
                        ftprEntry.getValue().remove(ruleCode);
                    });
            }
        }


        List<Integer> ruleCodesInAnswer = trainDay16.rules
            .stream()
            .filter(rule -> rule.name.startsWith("departure"))
            .map(rule -> rule.ruleCode)
            .collect(Collectors.toList());

        System.out.println("Debug");
        // Once you work out which field is which,
        // look for the six fields on your ticket that start with the word departure.
        // What do you get if you multiply those six values together?

//        return invalidFields.stream().mapToLong(Integer::longValue).sum();
        List<TicketField> myTicketFields = trainDay16.myTicket.values.entrySet()
            .stream()
            .map(entry -> {
                TicketField field = new TicketField();
                field.value = entry.getValue().longValue();
                Integer ruleCode = finalizedFieldToRules.get(entry.getKey());

                field.name = trainDay16.rules.stream()
                    .filter(rule -> rule.ruleCode.equals(ruleCode))
                    .map(rule -> rule.name)
                    .findFirst().orElseThrow(() -> new RuntimeException("Something broke setting my ticket field name"));
                return field;
            })
            .collect(Collectors.toList());


        return myTicketFields
            .stream()
            .filter(ticketField -> ticketField.name.startsWith("departure"))
            .map(ticketField -> ticketField.value)
            .reduce(1L, (a, b) -> a * b);
    }

    private static Map<Integer, List<Integer>> instantiateRules(TrainDay16 trainDay16) {
        Map<Integer, List<Integer>> fieldToPossibleRules = new HashMap<>();
        List<Integer> allRules = new ArrayList<>();
        // Instantiate fieldToPossibleRules with everything
        for (Rule rule : trainDay16.rules) {
            allRules.add(rule.ruleCode);
        }

        for (Integer fieldNumber : trainDay16.myTicket.values.keySet()) {
            fieldToPossibleRules.put(fieldNumber, allRules);
        }
        return fieldToPossibleRules;
    }

    static TrainDay16 parse(String filename) {
        TrainDay16 trainDay16 = new TrainDay16();
        List<String> lines = BaconFileReader.getFileLines("src", "com", "bacontech", "day16", filename);

        int ruleCode = 0;
        String mode = "rules";
        for(String line : lines) {
            if (line.isBlank()) { continue; }

            if (line.equalsIgnoreCase("your ticket:")) {
                mode = "your ticket";
                continue;
            } else if (line.equalsIgnoreCase("nearby tickets:")) {
                mode = "other tickets";
                continue;
            }

            if (mode.equalsIgnoreCase("rules")) {
                String[] split = line.split(": ");
                String name = split[0];
                String[] ranges = split[1].split(" or ");
                Rule rule = new Rule();
                rule.ruleCode = ruleCode;
                rule.name = name;
                for (String range : ranges) {
                    int lowRange = Integer.parseInt(range.split("-")[0]);
                    int highRange = Integer.parseInt(range.split("-")[1]);
                    rule.ranges.add(new Range(lowRange, highRange));
                }
                trainDay16.rules.add(rule);
                ruleCode++;
            } else if (mode.equalsIgnoreCase("your ticket")) {
                Ticket myTicket = parseTicket(line);
                trainDay16.myTicket = myTicket;
            } else if (mode.equalsIgnoreCase("other tickets")) {
                Ticket otherTicket = parseTicket(line);
                trainDay16.otherTickets.add(otherTicket);
            } else {
                System.out.println("Whoops");
            }
        }

        return trainDay16;
    }

    private static Ticket parseTicket(String line) {
        Ticket otherTicket = new Ticket();
        String[] values = line.split(",");
        for (int i = 0; i < values.length; i++) {
            int value = Integer.parseInt(values[i]);
            otherTicket.values.put(i, value);
        }
        return otherTicket;
    }
}
