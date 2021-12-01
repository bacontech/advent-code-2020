package com.bacontech.twenty.day10;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Adapter {
    private int value;
    private List<Adapter> hops = new ArrayList<>();
    Adapter(int value) {
        this.value = value;
    }
    public int getPossibleHops() {
        return this.hops.size();
    }
}
