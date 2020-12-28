package com.bacontech.day17;

import lombok.Data;

@Data
public class HyperCube {
    private static final Character ACTIVE = '#';
    private static final Character INACTIVE = '.';

    private Boolean isActive;
    private Integer row;
    private Integer col;
    private Integer zIndex;
    private Integer wIndex;

    public HyperCube(int row, int col, int z, int w, Character character) {
        this(row, col, z, w, character == ACTIVE);
    }
    public HyperCube(int row, int col, int z, int w, boolean isActive) {
        this.row = row;
        this.col = col;
        this.zIndex = z;
        this.wIndex = w;
        this.isActive = isActive;
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
