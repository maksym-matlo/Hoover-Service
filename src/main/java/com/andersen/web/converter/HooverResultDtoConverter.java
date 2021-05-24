package com.andersen.web.converter;

import com.andersen.model.HooverResult;
import com.andersen.web.dto.HooverResultDto;

/**
 * HooverResultDtoConverter provides you methods for converting
 * between HooverResultDto and HooverResult
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

public class HooverResultDtoConverter {

    /**
     * Method for converting HooverResult into HooverResultDto
     *
     * @param hooverResult a HooverResult needs to be converted
     * @return a HooverResultDto instance
     */
    public static HooverResultDto toDto(HooverResult hooverResult){
        HooverResultDto hooverResultDto = new HooverResultDto();
        int xAxis = hooverResult.getCurrentCoordinates().getXAxis();
        int yAxis = hooverResult.getCurrentCoordinates().getYAxis();
        int numberOfCleanedPatches = hooverResult.getCleanedPatches();
        hooverResultDto.setCoords(new int[]{xAxis, yAxis});
        hooverResultDto.setPatches(numberOfCleanedPatches);
        return hooverResultDto;
    }
}
