package com.andersen.service.impl;

import com.andersen.model.HooverTask;
import com.andersen.repository.HooverTaskRepository;
import com.andersen.service.HooverTaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * HooverTaskServiceImpl provides you CRUD methods for HooverTask model.
 *
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Service
@AllArgsConstructor
public class HooverTaskServiceImpl implements HooverTaskService {
    private final HooverTaskRepository taskRepository;

    /**
     * Uses HooverTaskRepository interface to save a by.andersen.HooverTask
     *
     * @param hooverTask a HooverTask that should be saved
     * @return created HooverTask entity
     */
    @Override
    public HooverTask save(HooverTask hooverTask) {
        return taskRepository.save(hooverTask);
    }
}
