package com.example.agroapp.task.domain.model.aggregates;

import com.example.agroapp.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

/**
 * Task aggregate root.
 * Represents an operational task linked to a specific Field.
 * Uses a boolean "completed" to support dashboard checkbox interaction.
 */
@Entity
@Getter
public class Task extends AuditableAbstractAggregateRoot<Task> {

    /**
     * ID of the Field this task is associated with.
     */
    @NotNull(message = "Field ID is required")
    @Column(nullable = false)
    private Long fieldId;

    /**
     * Scheduled or due date for the task.
     */
    @NotNull(message = "Task date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    /**
     * Task title or label (e.g., field name or short name).
     */
    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    @Column(nullable = false, length = 200)
    private String name;

    /**
     * Detailed description of what must be done.
     */
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(nullable = false, length = 500)
    private String description;

    /**
     * Whether the task has been completed (checkbox in dashboard UI).
     */
    @Column(nullable = false)
    private boolean completed = false;

    /**
     * Required by JPA.
     */
    protected Task() {}

    /**
     * Constructor for creating a new Task.
     */
    public Task(Long fieldId,
                Date date,
                String name,
                String description) {
        this.fieldId = fieldId;
        this.date = date;
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    /**
     * Update task details (does not affect completion state).
     */
    public Task update(Date date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
        return this;
    }

    /**
     * Mark this task as completed (for dashboard checkbox).
     */
    public Task markCompleted() {
        this.completed = true;
        return this;
    }
}
