package com.example.agroapp.task.interfaces.rest.transform;

import com.example.agroapp.task.domain.model.aggregates.Task;
import com.example.agroapp.task.interfaces.rest.resources.TaskResource;

/**
 * Assembler to transform Task entities into TaskResource.
 * This follows the Assembler pattern to separate domain models from API resources.
 */
public class TaskResourceFromEntityAssembler {

    /**
     * Transform a Task entity into a TaskResource.
     *
     * @param entity The Task entity
     * @return The corresponding TaskResource
     */
    public static TaskResource toResourceFromEntity(Task entity) {
        return new TaskResource(
                entity.getId(),
                entity.getFieldId(),
                entity.getDate(),
                entity.getName(),
                entity.getDescription(),
                entity.isCompleted(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
