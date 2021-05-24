package com.andersen.web.dto;

import com.andersen.TestData;
import com.andersen.model.HooverResult;
import com.andersen.web.converter.HooverResultDtoConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HooverResultDtoConverterTest {

    @Test
    public void fromDto(){
        HooverResult hooverResult = TestData.getHooverResult();
        HooverResultDto actual = TestData.getHooverResultDto();

        HooverResultDto expected = HooverResultDtoConverter.toDto(hooverResult);

        assertEquals(expected, actual);
    }
}
