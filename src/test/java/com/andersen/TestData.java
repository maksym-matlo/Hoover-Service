package com.andersen;

import com.andersen.model.Coordinates;
import com.andersen.model.HooverResult;
import com.andersen.model.HooverTask;
import com.andersen.web.dto.HooverResultDto;
import com.andersen.web.dto.HooverTaskDto;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static HooverTask getHooverTask(){
        HooverTask hooverTask = new HooverTask();
        hooverTask.setId(1L);
        hooverTask.setRoomSize(new Coordinates(10, 10));
        hooverTask.setCurrentCoordinates(new Coordinates(1,1));

        List<Coordinates> patches = new ArrayList<>();
        patches.add(new Coordinates(4, 5));
        patches.add(new Coordinates(6, 3));
        patches.add(new Coordinates(2, 2));

        hooverTask.setPatches(patches);
        hooverTask.setInstructions("WSENEWSNNEWSEWSNES");
        return hooverTask;
    }

    public static HooverResult getHooverResult(){
        HooverResult hooverResult = new HooverResult();
        hooverResult.setId(1L);
        hooverResult.setCurrentCoordinates(new Coordinates(0,1));
        hooverResult.setCleanedPatches(1);
        return hooverResult;
    }

    public static HooverTaskDto getHooverTaskDto(){
        HooverTaskDto hooverTaskDto = new HooverTaskDto();
        hooverTaskDto.setRoomSize(new int[]{10,10});
        hooverTaskDto.setCoords(new int[]{1,1});
        hooverTaskDto.setPatches(new int[][]{{4,5},{6,3},{2,2}});
        hooverTaskDto.setInstructions("WSENEWSNNEWSEWSNES");
        return hooverTaskDto;
    }

    public static HooverResultDto getHooverResultDto(){
        HooverResultDto hooverResultDto = new HooverResultDto();
        hooverResultDto.setCoords(new int[]{0,1});
        hooverResultDto.setPatches(1);
        return hooverResultDto;
    }
}
