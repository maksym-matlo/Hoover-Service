package com.andersen.web.dto;

import com.andersen.TestData;
import com.andersen.model.HooverTask;
import com.andersen.web.converter.HooverTaskDtoConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HooverTaskDtoConverterTest {

    @Test
    public void fromDto(){
        HooverTask expected = TestData.getHooverTask();
        expected.setId(null);

        HooverTaskDto hooverTaskDto = TestData.getHooverTaskDto();

        HooverTask actual = HooverTaskDtoConverter.fromDto(hooverTaskDto);

        assertEquals(expected, actual);
    }
}
