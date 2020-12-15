package com.bacontech.day14;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Decoder {
    private String mask;

    // location - decimal value
    private final Map<Integer, Long> memory = new HashMap<>();
    private final List<Integer> memoryLocations = new ArrayList<>();
}
