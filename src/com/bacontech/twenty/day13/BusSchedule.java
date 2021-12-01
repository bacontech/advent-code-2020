package com.bacontech.twenty.day13;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BusSchedule {
    int departureTime;
    List<Integer> busIds = new ArrayList<>();
    List<String> busIdsWithXs = new ArrayList<>();
}
