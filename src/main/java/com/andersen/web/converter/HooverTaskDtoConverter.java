package com.andersen.web.converter;

import com.andersen.model.Coordinates;
import com.andersen.model.HooverTask;
import com.andersen.web.dto.HooverTaskDto;

import java.util.ArrayList;
import java.util.List;

/**
 * HooverTaskDtoConverter provides you methods for converting
 * between HooverTaskDto and HooverTask
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

public class HooverTaskDtoConverter {

    /**
     * Method for converting HooverTask into HooverRaskDto
     *
     * @param hooverTaskDto a HooverTask needs to be converted
     * @return a HooverTaskDto instance
     */
    public static HooverTask fromDto(HooverTaskDto hooverTaskDto){
        HooverTask hooverTask = new HooverTask();
        Coordinates roomSize = getSingleCoordinate(hooverTaskDto.getRoomSize());
        Coordinates currentCoordinates = getSingleCoordinate(hooverTaskDto.getCoords());
        List<Coordinates> patches = getListCoordinates(hooverTaskDto.getPatches());
        hooverTask.setRoomSize(roomSize);
        hooverTask.setCurrentCoordinates(currentCoordinates);
        hooverTask.setPatches(patches);
        hooverTask.setInstructions(hooverTaskDto.getInstructions());
        return hooverTask;
    }

    /**
     * Method for setting coordinates from array
     *
     * @param value an array consists 2 int values
     * @return a Coordinates instance
     */
    private static Coordinates getSingleCoordinate(int [] value){
        Coordinates coordinates = new Coordinates();
        coordinates.setXAxis(value[0]);
        coordinates.setYAxis(value[1]);
        return coordinates;
    }

    /**
     * Method for setting list of coordinates from array
     *
     * @param value an array consists set of int values
     * @return a List of coordinates
     */
    private static List<Coordinates> getListCoordinates(int [][] value){
        List<Coordinates> coordinates = new ArrayList<>();

        for(int i=0;i<value.length;i++){
            for (int b=0;b<value[i].length-1;b++){
                coordinates.add(new Coordinates(value[i][b], value[i][b+1]));
            }
        }
        return coordinates;
    }
}
