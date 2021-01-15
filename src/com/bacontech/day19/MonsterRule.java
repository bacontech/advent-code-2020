package com.bacontech.day19;

import lombok.Data;

@Data
public class MonsterRule {
    private int number;
    private String rawRule;
    private boolean conditional;
    private String rule1;
    private String rule2;

    private String finishedRule;

    MonsterRule(int number, String rawRule) {
        this.number = number;
        this.rawRule = rawRule;
        this.conditional = hasConditional(rawRule);
        if (this.isConditional()) {
            String ruleConditions[] = this.rawRule.split("\\| ");
            // Dataset only has 1 | per rule
            this.rule1 = ruleConditions[0];
            this.rule2 = ruleConditions[1];
        }
    }

    private static boolean hasConditional(String rawRule) {
        return rawRule.contains("|");
    }

    @Override
    public String toString() {
        return "[" + rawRule + "]";
    }
}
