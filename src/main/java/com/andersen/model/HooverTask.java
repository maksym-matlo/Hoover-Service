package com.andersen.model;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;


/**
 * JPA entity that will be stored by persistence mechanism.
 * In this program persistence provided by Hibernate and Postgres.
 * <p>HooverTask is a entity present the task for the hoover.
 * Each HooverTask has a roomSize, currentCoordinates, patches, instructions and
 * id that Hibernate generates.
 * <p>
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Entity
@Data
public class HooverTask {

    /**
     * Identifier of HooverTask
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hoover_task_generator")
    @SequenceGenerator(allocationSize = 1, name = "hoover_task_generator")
    private Long id;

    /**
     * Current coordinates of room needs
     */
    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "room_xAxis", nullable = false)),
            @AttributeOverride(name = "yAxis", column = @Column(name = "room_yAxis", nullable = false))})
    private Coordinates roomSize;

    /**
     * Current coordinates of the hoover
     */
    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "current_xAxis", nullable = false)),
            @AttributeOverride(name = "yAxis", column = @Column(name = "current_yAxis", nullable = false))})
    private Coordinates currentCoordinates;

    /**
     * Coordinates of the patches
     */
    @ElementCollection
    @CollectionTable(name="patches_coordinates")
    @AttributeOverrides({
            @AttributeOverride(name = "xAxis", column = @Column(name = "patch_xAxis", nullable = false)),
            @AttributeOverride(name = "yAxis", column = @Column(name = "patch_yAxis", nullable = false))})
    @Column(nullable = false)
    private List<Coordinates> patches = new ArrayList<>();

    /**
     * Instructions for the hoover
     */
    @Column(nullable = false)
    private String instructions;
}
