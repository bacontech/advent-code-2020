package com.bacontech.twenty.day12;

import lombok.Data;

@Data
public class Boat {

    //       0
    // 270        90
    //      180

    private int direction = 90;
    private int wayPointX = 10;
    private int wayPointY = 1;

    private int xCoord = 0;
    private int yCoord = 0;

    public void north(int amt) {
        yCoord += amt;
    }
    public void south(int amt) {
        yCoord -= amt;
    }
    public void east(int amt) {
        xCoord += amt;
    }
    public void west(int amt) {
        xCoord -= amt;
    }
    public void wayPointNorth(int amt) {
        wayPointY += amt;
    }
    public void wayPointSouth(int amt) {
        wayPointY -= amt;
    }
    public void wayPointEast(int amt) {
        wayPointX += amt;
    }
    public void wayPointWest(int amt) {
        wayPointX -= amt;
    }

    public void left(int amt) {
        direction -= amt;
        if (direction < 0) {
            direction += 360;
        }
    }

    public void right(int amt) {
        direction += amt;
        if (direction > 359) {
            direction -= 360;
        }
    }
    // PRIOR: 10 units east and 4 units north of the ship
    // R90 rotates the waypoint around the ship clockwise 90 degrees, moving it to 4 units east and 10 units south of the ship. The ship remains at east 170, north 38.
    public void wayPointRight(int amt) {
        int oldWaypointX = wayPointX;
        int oldWaypointY = wayPointY;
        if (amt == 180) {
            wayPointX = -oldWaypointX;
            wayPointY = -oldWaypointY;
        }
        if (amt == 90) {
            wayPointX = oldWaypointY;
            wayPointY = -oldWaypointX;
            // old    10 / 4
            // R90    4 / -10
            // R180   -10 / -4
            // R270   -4 / 10
        }
        if (amt == 270) {
            wayPointLeft(90);
        }
    }

    public void wayPointLeft(int amt) {
        int oldWaypointX = wayPointX;
        int oldWaypointY = wayPointY;
        if (amt == 90) {
            wayPointX = -oldWaypointY;
            wayPointY = oldWaypointX;
        } if (amt == 180) {
            wayPointX = -oldWaypointX;
            wayPointY = -oldWaypointY;
        }
        if (amt == 270) {
            wayPointRight(90);
        }

    }

    public void moveToWaypoint(int amt) {
        int moveX = wayPointX * amt;
        int moveY = wayPointY * amt;
        xCoord += moveX;
        yCoord += moveY;
    }

    public void forward(int amt) {
        if (direction == 0) {
            north(amt);
        }
        if (direction == 90) {
            east(amt);
        }
        if (direction == 180) {
            south(amt);
        }
        if (direction == 270) {
            west(amt);
        }
    }

    public String getDirectionString() {
        if (direction == 0) return "NORTH";
        if (direction == 90) return "EAST";
        if (direction == 180) return "SOUTH";
        if (direction == 270) return "WEST";
        return "BAD";
    }

}
