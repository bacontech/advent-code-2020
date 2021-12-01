package com.bacontech.twenty.day9;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class XmasNumber {
    private long value;
    private int lineNumber;
    // small: big
    private Map<Long, Long> matches = new HashMap<>();

    XmasNumber(int lineNumber, long value) {
        this.lineNumber = lineNumber;
        this.value = value;
    }

    public void addMatch(long num1, long num2) {
        if (num1 < num2) {
            matches.put(num1, num2);
        } else {
            matches.put(num2, num1);
        }
    }

    public boolean hasAMatch() {
        if (matches.size() > 0) {
            return true;
        }
        return false;
    }
}
