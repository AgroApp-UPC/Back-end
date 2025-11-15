package com.example.agroapp.task.interfaces.rest;

import com.example.agroapp.task.application.services.TaskApplicationService;
import com.example.agroapp.task.domain.model.aggregates.Task;
import com.example.agroapp.task.interfaces.rest.resources.CreateTaskResource;
import com.example.agroapp.task.interfaces.rest.resources.TaskResource;
import com.example.agroapp.task.interfaces.rest.resources.UpdateTaskResource;
import com.example.agroapp.task.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Task operations
 * Exposes HTTP endpoints for managing tasks
 * Base path: /api/v1/tasks
 */
@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Tasks", description = "Operations related to task management")
public class TaskController {

    private final TaskApplicationService taskApplicationService;

    @Autowired
    public TaskController(TaskApplicationService taskApplicationService) {
        this.taskApplicationService = taskApplicationService;
    }

    /**
     * Create a new task
     * POST /api/v1/tasks
     */
    @Operation(summary = "Create a new task", description = "Creates a new task associated with a field")
    @PostMapping
    public ResponseEntity<TaskResource> createTask(@Valid @RequestBody CreateTaskResource resource) {
        Task task = taskApplicationService.createTask(
                resource.fieldId(),
                resource.date(),
                resource.name(),
                resource.description()
        );
        TaskResource taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(task);
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }

    /**
     * Get all tasks
     * GET /api/v1/tasks
     */
    @Operation(summary = "Get all tasks", description = "Retrieves all tasks")
    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        List<Task> tasks = taskApplicationService.getAllTasks();
        List<TaskResource> resources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get a specific task by ID
     * GET /api/v1/tasks/{id}
     */
    @Operation(summary = "Get task by ID", description = "Retrieves a specific task by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long id) {
        return taskApplicationService.getTaskById(id)
                .map(task -> ResponseEntity.ok(
                        TaskResourceFromEntityAssembler.toResourceFromEntity(task)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get tasks by field ID
     * GET /api/v1/tasks?fieldId={fieldId}
     */
    @Operation(summary = "Get tasks by field", description = "Retrieves all tasks associated with a specific field")
    @GetMapping(params = "fieldId")
    public ResponseEntity<List<TaskResource>> getTasksByFieldId(@RequestParam Long fieldId) {
        List<Task> tasks = taskApplicationService.getTasksByFieldId(fieldId);
        List<TaskResource> resources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get upcoming tasks (not completed and date after today)
     * GET /api/v1/tasks/upcoming
     */
    @Operation(summary = "Get upcoming tasks", description = "Retrieves tasks that are not completed and scheduled after today")
    @GetMapping("/upcoming")
    public ResponseEntity<List<TaskResource>> getUpcomingTasks() {
        List<Task> tasks = taskApplicationService.getUpcomingTasks();
        List<TaskResource> resources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update a task
     * PUT /api/v1/tasks/{id}
     */
    @Operation(summary = "Update a task", description = "Updates all information of an existing task")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResource> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskResource resource) {

        return taskApplicationService.updateTask(
                        id,
                        resource.date(),
                        resource.name(),
                        resource.description()
                )
                .map(task -> ResponseEntity.ok(
                        TaskResourceFromEntityAssembler.toResourceFromEntity(task)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Mark task as completed
     * PATCH /api/v1/tasks/{id}/complete
     */
    @Operation(summary = "Mark task as completed", description = "Marks the task as completed (used by dashboard checkbox)")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResource> markTaskCompleted(@PathVariable Long id) {
        return taskApplicationService.markTaskCompleted(id)
                .map(task -> ResponseEntity.ok(
                        TaskResourceFromEntityAssembler.toResourceFromEntity(task)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a task
     * DELETE /api/v1/tasks/{id}
     */
    @Operation(summary = "Delete a task", description = "Deletes a task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskApplicationService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
