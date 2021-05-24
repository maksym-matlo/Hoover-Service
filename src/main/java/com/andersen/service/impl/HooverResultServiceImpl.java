package com.andersen.service.impl;

import com.andersen.model.Coordinates;
import com.andersen.model.Direction;
import com.andersen.model.HooverResult;
import com.andersen.model.HooverTask;
import com.andersen.repository.HooverResultRepository;
import com.andersen.service.HooverResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HooverResultServiceImpl provides you CRUD methods for HooverResult model.
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Service
@AllArgsConstructor
public class HooverResultServiceImpl implements HooverResultService {
    private final HooverResultRepository resultRepository;

    /**
     * Counter of the cleaned patches
     */
    private static Integer patchCounter;

    /**
     * Counter of the performed instructions
     */
    private static Integer marker;

    /**
     * Uses HooverResultRepository interface to save a by.andersen.HooverResult
     *
     * @param hooverResult a HooverResult that should be saved
     * @return created HooverResult entity
     */
    @Override
    public HooverResult save(HooverResult hooverResult) {
        return resultRepository.save(hooverResult);
    }

    /**
     * Method for performing a hoover job.
     *
     * @param hooverTask a HooverTask to be performed
     * @return result of hoover job
     */
    public HooverResult doCleaningJob (HooverTask hooverTask){

        marker = 0;
        patchCounter = 0;
        Coordinates finalCoordinates =
                getNextCoordinates(hooverTask.getRoomSize(), hooverTask.getCurrentCoordinates(),
                hooverTask.getPatches(), hooverTask.getInstructions());

        return new HooverResult(finalCoordinates, patchCounter);
    }

    /**
     * Method for performing one step of hoover job.
     *
     * @param roomSize coordinates of the room
     * @param currentCoordinates current hoover coordinates
     * @param patches list of the patches
     * @param instruction instructions for hoover movement
     * @return one step result of hoover job
     */
    private Coordinates getNextCoordinates(Coordinates roomSize,
                                             Coordinates currentCoordinates,
                                             List<Coordinates> patches,
                                             String instruction) {

        Direction direction = getDirectionByInstruction(instruction);
        Coordinates plannedCoordinates = getPlannedCoordinates(currentCoordinates, direction);

        if(!canTouchTheWall(plannedCoordinates, roomSize)){
            currentCoordinates = plannedCoordinates;
            if(foundPatches(patches, currentCoordinates)){
                patchCounter++;
                patches.remove(currentCoordinates);
            }
        }

        if(instruction.length()-1>marker){
            marker++;
            getNextCoordinates(roomSize, currentCoordinates, patches, instruction);
        }

        return currentCoordinates;
    }

    /**
     * Method for getting hoover shifting depends received movement instructions
     *
     * @param instruction set of hoover movement instructions
     * @return instruction of hoover shifting
     */
    private Direction getDirectionByInstruction(String instruction){
        String point = String.valueOf(instruction.charAt(marker));
        return Direction.valueOf(point);
    }

    /**
     * Method for getting planned coordinates of next hoover movement
     *
     * @param currentCoordinates current hoover coordinates
     * @param direction received movement instruction
     * @return instruction of hoover shifting
     */
    private Coordinates getPlannedCoordinates(Coordinates currentCoordinates, Direction direction){
        Coordinates plannedCoordinates = new Coordinates();
        plannedCoordinates.setXAxis(currentCoordinates.getXAxis() + direction.getXShift());
        plannedCoordinates.setYAxis(currentCoordinates.getYAxis() + direction.getYShift());
        return plannedCoordinates;
    }

    /**
     * Checking the planned coordinates for the location in the coordinates of the room
     *
     * @param plannedCoordinates plannes hoover coordinates
     * @param roomSize room size
     * @return boolean value
     */
    private boolean canTouchTheWall (Coordinates plannedCoordinates, Coordinates roomSize){
        return plannedCoordinates.getXAxis() == roomSize.getXAxis() ||
                plannedCoordinates.getYAxis() == roomSize.getYAxis() ||
                plannedCoordinates.getXAxis() < 0 ||
                plannedCoordinates.getYAxis() < 0;
    }

    /**
     * Check the current coordinates for a stay in one of those coordinates
     * mention in the list of the patches
     *
     * @param patches list of patches
     * @param currentPosition current hoover position
     * @return boolean value
     */
    private boolean foundPatches(List<Coordinates> patches, Coordinates currentPosition){
        return patches.contains(currentPosition);
    }
}
