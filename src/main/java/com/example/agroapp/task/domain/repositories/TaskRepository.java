package com.example.agroapp.task.domain.repositories;

import com.example.agroapp.task.domain.model.aggregates.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for Task aggregate.
 * Spring Data JPA automatically provides the implementation.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Find all tasks associated with a specific field.
     *
     * @param fieldId The field ID
     * @return List of tasks linked to that field
     */
    List<Task> findByFieldId(Long fieldId);

    /**
     * Find tasks that are not completed and have a date after a specific day.
     * Used for "upcoming tasks" in the dashboard.
     *
     * @param date The minimum date to compare
     * @return List of upcoming (not completed) tasks
     */
    List<Task> findByCompletedFalseAndDateAfter(Date date);
}
