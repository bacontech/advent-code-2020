package com.bacontech.twenty.day7;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BagRule {
    private String name;
    private Boolean canContainGoldBag;
    private Integer containsExactlyNBags;
    private Map<String, Integer> rules = new HashMap<>();

    public void addRule(String name, Integer count) {
        rules.put(name, count);
    }

}
