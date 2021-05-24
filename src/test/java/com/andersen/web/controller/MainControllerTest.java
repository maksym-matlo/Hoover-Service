package com.andersen.web.controller;

import com.andersen.TestData;
import com.andersen.service.HooverResultService;
import com.andersen.service.HooverTaskService;
import com.andersen.web.dto.HooverResultDto;
import com.andersen.web.dto.HooverTaskDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MainControllerTest {
    @Autowired
    private MainController mainController;

    @Autowired
    private HooverTaskService hooverTaskService;

    @Autowired
    private HooverResultService hooverResultService;

    @Test
    @Order(1)
    public void doCleaningJob(){
        HooverTaskDto taskDto = TestData.getHooverTaskDto();
        HooverResultDto expected = TestData.getHooverResultDto();

        HooverResultDto actual = mainController.doCleaningJob(taskDto);

        assertEquals(expected, actual);
    }

    @Test
    public void doCleaningJobWithNullRoomSize(){
        HooverTaskDto taskDto = TestData.getHooverTaskDto();
        taskDto.setRoomSize(null);

        assertThrows(NullPointerException.class,
                () -> mainController.doCleaningJob(taskDto));
    }

    @Test
    public void doCleaningJobWithNullCurrentCoordinates(){
        HooverTaskDto taskDto = TestData.getHooverTaskDto();
        taskDto.setCoords(null);

        assertThrows(NullPointerException.class,
                () -> mainController.doCleaningJob(taskDto));
    }

    @Test
    public void doCleaningJobTaskWithNullPatches(){
        HooverTaskDto taskDto = TestData.getHooverTaskDto();
        taskDto.setPatches(null);

        assertThrows(NullPointerException.class,
                () -> mainController.doCleaningJob(taskDto));
    }

    @Test
    public void doCleaningJobWithNullInstructions(){
        HooverTaskDto taskDto = TestData.getHooverTaskDto();
        taskDto.setInstructions(null);

        assertThrows(DataAccessException.class,
                () -> mainController.doCleaningJob(taskDto));
    }
}
