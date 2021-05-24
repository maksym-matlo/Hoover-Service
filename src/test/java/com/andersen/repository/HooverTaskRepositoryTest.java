package com.andersen.repository;

import com.andersen.TestData;
import com.andersen.model.HooverTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HooverTaskRepositoryTest {
    @Autowired
    private HooverTaskRepository taskRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveHooverTask(){
        HooverTask expected = TestData.getHooverTask();

        HooverTask actual = taskRepository.save(expected);

        assertEquals(expected, actual);
    }
}
