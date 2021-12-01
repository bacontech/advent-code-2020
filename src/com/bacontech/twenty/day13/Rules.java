package com.bacontech.twenty.day13;

import lombok.Data;

@Data
public class Rules {
    Integer waitTime;
    Integer busId;

    public Rules(Integer busId, Integer waitTime) {
        this.busId = busId;
        this.waitTime = waitTime;
    }
}
