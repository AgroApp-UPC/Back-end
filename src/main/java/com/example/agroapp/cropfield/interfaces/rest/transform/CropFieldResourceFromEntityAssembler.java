package com.example.agroapp.cropfield.interfaces.rest.transform;

import com.example.agroapp.cropfield.domain.model.aggregates.CropField;
import com.example.agroapp.cropfield.interfaces.rest.resources.CropFieldResource;

/**
 * Assembler to transform CropField entities to CropFieldResource
 * This follows the Assembler pattern to separate domain models from API resources
 */
public class CropFieldResourceFromEntityAssembler {

    /**
     * Transform a CropField entity to a CropFieldResource
     *
     * @param entity The CropField entity
     * @return The corresponding CropFieldResource
     */
    public static CropFieldResource toResourceFromEntity(CropField entity) {
        return new CropFieldResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDays(),
                entity.getPlantingDate(),
                entity.getHarvestDate(),
                entity.getField(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
