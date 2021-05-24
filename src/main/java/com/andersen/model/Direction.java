package com.andersen.model;

/**
 *
 * <p> Direction is an enum has cardinal points used in this application
 * as a commands for hoover and instructions for hoover shift
 * in case of getting any of presented commands.
 * <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

public enum Direction {

    /**
     * Set of commands
     */
    N(0,1),
    S(0,-1),
    W(-1,0),
    E(1,0);

    /**
     * Shift along the X axis
     */
    private final int xShift;

    /**
     * Shift along the Y axis
     */
    private final int yShift;

    Direction(int xShift, int yShift) {
        this.xShift = xShift;
        this.yShift = yShift;
    }

    public int getXShift() {
        return xShift;
    }

    public int getYShift() {
        return yShift;
    }
}
