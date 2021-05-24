package com.andersen.service;

import com.andersen.TestData;
import com.andersen.model.HooverTask;
import com.andersen.repository.HooverTaskRepository;
import com.andersen.service.impl.HooverTaskServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@AllArgsConstructor
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HooverTaskServiceTest {
    private final HooverTaskRepository hooverTaskRepository = mock(HooverTaskRepository.class);
    private final HooverTaskService hooverTaskService = new HooverTaskServiceImpl(hooverTaskRepository);

    @Test
    public void saveHooverTask(){
        HooverTask expected = TestData.getHooverTask();
        Mockito.when(hooverTaskRepository.save(expected)).thenReturn(expected);

        HooverTask actual = hooverTaskService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void saveHooverTaskWithNullRoomSize(){
        HooverTask hooverTask = TestData.getHooverTask();
        hooverTask.setRoomSize(null);
        Mockito.when(hooverTaskRepository.save(hooverTask)).thenThrow(mock(DataIntegrityViolationException.class));

        assertThrows(DataIntegrityViolationException.class, () -> hooverTaskService.save(hooverTask));
    }

    @Test
    public void saveHooverTaskWithNullCurrentCoordinates(){
        HooverTask hooverTask = TestData.getHooverTask();
        hooverTask.setCurrentCoordinates(null);
        Mockito.when(hooverTaskRepository.save(hooverTask)).thenThrow(mock(DataIntegrityViolationException.class));

        assertThrows(DataIntegrityViolationException.class, () -> hooverTaskService.save(hooverTask));
    }

    @Test
    public void saveHooverTaskWithNullPatches(){
        HooverTask hooverTask = TestData.getHooverTask();
        hooverTask.setPatches(null);
        Mockito.when(hooverTaskRepository.save(hooverTask)).thenThrow(mock(DataIntegrityViolationException.class));

        assertThrows(DataIntegrityViolationException.class, () -> hooverTaskService.save(hooverTask));
    }

    @Test
    public void saveHooverTaskWithNullInstructions(){
        HooverTask hooverTask = TestData.getHooverTask();
        hooverTask.setInstructions(null);
        Mockito.when(hooverTaskRepository.save(hooverTask)).thenThrow(mock(DataIntegrityViolationException.class));

        assertThrows(DataIntegrityViolationException.class, () -> hooverTaskService.save(hooverTask));
    }
}
