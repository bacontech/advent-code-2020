package com.bacontech.twenty.day6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupAnswers {
    private Map<Character, Integer> answers = new HashMap<>();
    private int size = 0;

    public void addAnswer(Character answer) {
        if (answers.containsKey(answer)) {
            int currentCount = answers.get(answer);
            answers.put(answer, currentCount + 1);
        } else {
            answers.put(answer, 1);
        }
    }

    public void incrementGroupSize() {
        this.size++;
    }
}
