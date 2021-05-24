package com.andersen.web.dto;

import lombok.Data;

/**
 * <p> HooverResultDto is an entity for holding HooverResult in requests
 * Each HooverResultDto has a xAxis and yAxis
 * <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Data
public class HooverResultDto {

    /**
     * Coordinates on the X axis
     */
    private int [] coords;

    /**
     * Coordinates of the patches
     */
    private int patches;
}
