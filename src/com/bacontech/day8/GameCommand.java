package com.bacontech.day8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCommand {
    public static final String JMP = "jmp";
    public static final String NOP = "nop";
    public static final String ACC = "acc";

    private static final List<String> ONE_DOWNS = Arrays.asList(ACC, NOP);
    private static final List<String> FLIPPED_ONE_DOWNS = Arrays.asList(ACC, JMP);
    private static final List<String> ZERO_VALUE = Arrays.asList(NOP, JMP);


    private Integer lineNumber;
    private String operation;
    private Integer argument;

    public Integer resultingLineNumber() {
        if (ONE_DOWNS.contains(operation)) {
            return lineNumber + 1;
        }
        return lineNumber + argument;
    }
    public Integer resultingLineNumberBackwards() {
        if (ONE_DOWNS.contains(operation)) {
            return lineNumber - 1;
        }
        return lineNumber - argument;
    }

    public Integer resultingLineNumberIfFlipped() {
        if (FLIPPED_ONE_DOWNS.contains(operation)) {
            return lineNumber + 1;
        }
        return lineNumber + argument;
    }

    public Integer getAccumulatorValue() {
        if (ZERO_VALUE.contains(argument)) {
            return 0;
        } else {
            return argument;
        }
    }

    public String getFlippedOperation() {
        if (NOP.equals(operation)) {
            return JMP;
        } else if (JMP.equals(operation)) {
            return NOP;
        }
        return ACC;
    }
}
