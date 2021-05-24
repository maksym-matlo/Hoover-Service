package com.andersen.service;

import com.andersen.TestData;
import com.andersen.model.HooverResult;
import com.andersen.model.HooverTask;
import com.andersen.repository.HooverResultRepository;
import com.andersen.service.impl.HooverResultServiceImpl;
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
public class HooverResultServiceTest {
    private final HooverResultRepository resultRepository = mock(HooverResultRepository.class);;
    private final HooverResultService resultService = new HooverResultServiceImpl(resultRepository);

    @Test
    public void saveHooverResult(){
        HooverResult expected = TestData.getHooverResult();
        Mockito.when(resultRepository.save(expected)).thenReturn(expected);

        HooverResult actual = resultService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void doCleaningJob(){
        HooverTask hooverTask = TestData.getHooverTask();
        HooverResult expected = TestData.getHooverResult();
        expected.setId(null);

        HooverResult actual = resultService.doCleaningJob(hooverTask);

        assertEquals(expected, actual);
    }

    @Test
    public void saveHooverResultWithNullCurrentCoordinates(){
        HooverResult hooverResult = TestData.getHooverResult();
        hooverResult.setCurrentCoordinates(null);
        Mockito.when(resultRepository.save(hooverResult)).thenThrow(mock(DataIntegrityViolationException.class));

        assertThrows(DataIntegrityViolationException.class,
                () -> resultService.save(hooverResult));
    }
}
