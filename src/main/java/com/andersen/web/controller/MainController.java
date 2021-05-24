package com.andersen.web.controller;

import com.andersen.model.HooverResult;
import com.andersen.model.HooverTask;
import com.andersen.service.HooverResultService;
import com.andersen.service.HooverTaskService;
import com.andersen.web.converter.HooverResultDtoConverter;
import com.andersen.web.converter.HooverTaskDtoConverter;
import com.andersen.web.dto.HooverResultDto;
import com.andersen.web.dto.HooverTaskDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * MainController handles hoover related http requests in RESTful way.<p>
 * This controller calls CRUD methods and sends response for http requests
 * on /doCleaningJob uri.
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/doCleaningJob")
@AllArgsConstructor
public class MainController {

    private final HooverTaskService taskService;
    private final HooverResultService resultService;

    /**
     * Handles post requests on /doCleaningJob uri
     * Methods converts HooverTaskDto into HooverTask and saves HooverTask instance,
     * perform cleaning hoover job, received result of hoover job, converting it into
     * HooverResultDto and returns it back
     *
     * @param hooverTaskDto HooverTaskDto instance
     * @return created HooverResultDto
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HooverResultDto doCleaningJob(@Valid @RequestBody HooverTaskDto hooverTaskDto){
        HooverTask hooverTask = HooverTaskDtoConverter.fromDto(hooverTaskDto);
        taskService.save(hooverTask);
        HooverResult hooverResult = resultService.doCleaningJob(hooverTask);
        resultService.save(hooverResult);
        return HooverResultDtoConverter.toDto(hooverResult);
    }
}
