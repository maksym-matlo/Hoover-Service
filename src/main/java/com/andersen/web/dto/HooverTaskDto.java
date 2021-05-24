package com.andersen.web.dto;

import lombok.Data;

/**
 * <p> HooverTaskDto is an entity for holding HooverTask in requests
 * Each HooverTaskDto has coordinates of the room, current hoover coordinates
 * coordinates pf patches and hoover movement instruction
 * <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */


@Data
public class HooverTaskDto {

    /**
     * Current coordinates of room needs
     */
    private int [] roomSize;

    /**
     * Current coordinates of the hoover
     */
    private int [] coords;

    /**
     * Coordinates of the patches
     */
    private int [] [] patches;

    /**
     * Instructions for the hoover
     */
    private String instructions;
}
