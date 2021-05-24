package com.andersen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <p> Coordinates is an entity presents the coordinates for some objects
 * in this application such as hoover, room size and coordinates of patches.
 * Each Coordinates has a xAxis and yAxis
 * <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coordinates {

    /**
     * Coordinates on the X axis
     */
    @Column(insertable = false, updatable = false, nullable = false)
    private int xAxis;

    /**
     * Coordinates on the Y axis
     */
    @Column(insertable = false, updatable = false, nullable = false)
    private int yAxis;
}
