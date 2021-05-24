package com.andersen.repository;

import com.andersen.model.HooverTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maksym Matlo
 * @version 1.0
 * @since 2021-05-23
 */

@Repository
public interface HooverTaskRepository extends JpaRepository<HooverTask, Long> {
}
