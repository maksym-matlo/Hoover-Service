package com.andersen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * JPA entity that will be stored by persistence mechanism.
 * In this program persistence provided by Hibernate and Postgres.
 * <p> HooverResult is an entity presents the result of hoover job.
 * Each HooverResult has a currentCoordinates, cleanedPatches and
 * id that Hibernate generates.
 * <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Data
@NoArgsConstructor
@Entity
public class HooverResult {

    /**
     * Identifier of HooverResult
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hoover_result_generator")
    @SequenceGenerator(allocationSize = 1, name = "hoover_result_generator")
    private Long id;

    /**
     * Current coordinates of the hoover
     */
    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "current_xAxis", nullable = false)),
            @AttributeOverride(name = "yAxis", column = @Column(name = "current_yAxis", nullable = false))})
    @Column(nullable = false)
    private Coordinates currentCoordinates;

    /**
     * Number of patches cleaned by hoover
     */
    @Column(nullable = false)
    private int cleanedPatches;

    public HooverResult(Coordinates currentCoordinates, int cleanedPatches) {
        this.currentCoordinates = currentCoordinates;
        this.cleanedPatches = cleanedPatches;
    }
}
