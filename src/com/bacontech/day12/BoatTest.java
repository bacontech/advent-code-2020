package com.bacontech.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoatTest {

    @Test
    void directions() {
        Boat boat = new Boat();
        boat.setWayPointX(1);
        boat.setWayPointY(0);
        boat.wayPointLeft(180);

        assertEquals(-1, boat.getWayPointX(), "way point x");
        assertEquals(0, boat.getWayPointY(), "way point y");
    }

    @Test
    void directions2() {
        Boat boat = new Boat();
        boat.setWayPointX(10);
        boat.setWayPointY(4);
        boat.wayPointLeft(180);

        assertEquals(-10, boat.getWayPointX(), "way point x");
        assertEquals(-4, boat.getWayPointY(), "way point y");
    }

}
