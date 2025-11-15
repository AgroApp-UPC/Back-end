package com.example.agroapp.field.interfaces.rest.transform;

import com.example.agroapp.field.domain.model.aggregates.Field;
import com.example.agroapp.field.interfaces.rest.resources.FieldResource;

/**
 * Assembler to transform Field entities into FieldResource.
 * This follows the Assembler pattern to keep domain models separate from API resources.
 */
public class FieldResourceFromEntityAssembler {

    /**
     * Transform a Field entity into a FieldResource.
     *
     * @param entity The Field entity
     * @return The corresponding FieldResource
     */
    public static FieldResource toResourceFromEntity(Field entity) {
        return new FieldResource(
                entity.getId(),
                entity.getName(),
                entity.getImageUrl(),
                entity.getProduct(),
                entity.getLocation(),
                entity.getFieldSize(),
                entity.getCrop(),
                entity.getDaysSincePlanting(),
                entity.getPlantingDate(),
                entity.getExpectedHarvestDate(),
                entity.getSoilType(),
                entity.getWatering(),
                entity.getSunlight(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
