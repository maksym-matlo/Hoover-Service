package com.andersen.web.controller;

import com.andersen.TestData;
import com.andersen.repository.HooverResultRepository;
import com.andersen.web.dto.HooverTaskDto;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MainControllerRestApiTest {
    @LocalServerPort
    private int port;

    @Autowired
    private HooverResultRepository resultRepository;

    @Test
    public void doCleaningJob(){
        HooverTaskDto task = TestData.getHooverTaskDto();

        RestAssured.
                given()
                .header("Content-type", "application/json")
                .body(task)
                .port(port)
                .when().post("/api/v1/doCleaningJob")
                .then()
                .statusCode(201)
                .body("coords", Matchers.containsInRelativeOrder(0,1))
                .body("patches", Matchers.equalTo(1));
    }

    @Test
    public void doCleaningJobWithNullRoomSize(){
        HooverTaskDto task = TestData.getHooverTaskDto();
        task.setRoomSize(null);

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(task)
                .port(port)
                .when().post("/api/v1/doCleaningJob")
                .then()
                .statusCode(500);
    }

    @Test
    public void doCleaningJobWithNullCurrentCoordinates(){
        HooverTaskDto task = TestData.getHooverTaskDto();
        task.setCoords(null);

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(task)
                .port(port)
                .when().post("/api/v1/doCleaningJob")
                .then()
                .statusCode(500);
    }

    @Test
    public void doCleaningJobWithNullPatches(){
        HooverTaskDto task = TestData.getHooverTaskDto();
        task.setPatches(null);

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(task)
                .port(port)
                .when().post("/api/v1/doCleaningJob")
                .then()
                .statusCode(500);
    }

    @Test
    public void doCleaningJobWithNullInstructions(){
        HooverTaskDto task = TestData.getHooverTaskDto();
        task.setInstructions(null);

        RestAssured
                .given()
                .header("Content-type", "application/json")
                .body(task)
                .port(port)
                .when().post("/api/v1/doCleaningJob")
                .then()
                .statusCode(500);
    }
}
