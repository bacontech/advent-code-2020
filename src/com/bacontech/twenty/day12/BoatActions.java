package com.bacontech.twenty.day12;

import lombok.Data;

@Data
public class BoatActions {
    private Character action;
    private int value;
//    Action N means to move north by the given value.
//    Action S means to move south by the given value.
//    Action E means to move east by the given value.
//    Action W means to move west by the given value.
//    Action L means to turn left the given number of degrees.
//    Action R means to turn right the given number of degrees.
//    Action F means to move forward by the given value in the direction the ship is currently facing.

    BoatActions(String line) {
        action = line.charAt(0);
        value = Integer.parseInt(line.substring(1));
    }
}
