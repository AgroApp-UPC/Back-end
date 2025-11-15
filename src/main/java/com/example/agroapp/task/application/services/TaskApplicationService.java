package com.example.agroapp.task.application.services;

import com.example.agroapp.task.domain.model.aggregates.Task;
import com.example.agroapp.task.domain.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Application service for Task use cases.
 * Contains the business logic for managing tasks.
 * Coordinates interactions between domain model and repository.
 */
@Service
public class TaskApplicationService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskApplicationService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Create a new task
     *
     * @param fieldId     ID of the field this task belongs to
     * @param date        Task date
     * @param name        Task name
     * @param description Task description
     * @return The created Task
     */
    @Transactional
    public Task createTask(Long fieldId, Date date, String name, String description) {
        Task task = new Task(fieldId, date, name, description);
        return taskRepository.save(task);
    }

    /**
     * Get all tasks
     *
     * @return List of all tasks
     */
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Get a specific task by ID
     *
     * @param id Task ID
     * @return Optional containing the task if found
     */
    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Get all tasks associated with a specific field
     *
     * @param fieldId The field ID
     * @return List of tasks linked to that field
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksByFieldId(Long fieldId) {
        return taskRepository.findByFieldId(fieldId);
    }

    /**
     * Get upcoming tasks (not completed and date greater or equal than today)
     *
     * @return List of upcoming tasks
     */
    @Transactional(readOnly = true)
    public List<Task> getUpcomingTasks() {
        Date today = new Date();
        return taskRepository.findByCompletedFalseAndDateAfter(today);
    }

    /**
     * Update an existing task
     *
     * @param id          Task ID
     * @param date        Task date
     * @param name        Task name
     * @param description Task description
     * @return Optional containing the updated task if found
     */
    @Transactional
    public Optional<Task> updateTask(Long id, Date date, String name, String description) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.update(date, name, description);
                    return taskRepository.save(task);
                });
    }

    /**
     * Mark a task as completed
     *
     * @param id Task ID
     * @return Optional containing the updated task if found
     */
    @Transactional
    public Optional<Task> markTaskCompleted(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.markCompleted();
                    return taskRepository.save(task);
                });
    }

    /**
     * Delete a task by ID
     *
     * @param id Task ID
     * @return true if deleted, false if task not found
     */
    @Transactional
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
