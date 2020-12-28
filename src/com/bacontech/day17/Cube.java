package com.bacontech.day17;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Cube {
    private static final Character ACTIVE = '#';
    private static final Character INACTIVE = '.';

    private Boolean isActive;
    private Integer row;
    private Integer col;
    private Integer zIndex;

    public Cube(int row, int col, int z, Character character) {
        this(row, col, z, character == ACTIVE);
    }
    public Cube(int row, int col, int z, boolean isActive) {
        this.row = row;
        this.col = col;
        this.zIndex = z;
        this.isActive = isActive;
    }

    // Each cube only ever considers its neighbors:
    // any of the 26 other cubes where any of their
    // coordinates differ by at most 1.
    // For example, given the cube at x=1,y=2,z=3,
    // its neighbors include the cube at x=2,y=2,z=2,
    // the cube at x=0,y=2,z=3, and so on.
    public boolean isNeighbor(Cube otherCube) {
        if (Math.abs(this.row - otherCube.row) > 1) {
            return false;
        }
        if (Math.abs(this.col - otherCube.col) > 1) {
            return false;
        }
        if (Math.abs(this.zIndex - otherCube.zIndex) > 1) {
            return false;
        }
        return true;
    }

    public boolean determineIfActiveNext(int numActiveNeighbors) {
        if (this.isActive) {
            if (numActiveNeighbors == 2 || numActiveNeighbors == 3) {
                return true; // remain active
            } else {
                return false; // Otherwise, the cube becomes inactive.
            }
        } else {
            if (numActiveNeighbors == 3) {
                return true;
            } else {
                return false;
            }
        }
    }
}
