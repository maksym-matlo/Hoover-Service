package com.andersen.service;

import com.andersen.model.HooverResult;
import com.andersen.model.HooverTask;

/**
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

public interface HooverResultService {
    HooverResult save(HooverResult hooverResult);
    HooverResult doCleaningJob(HooverTask hooverTask);
}
