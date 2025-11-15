package com.example.agroapp.community.interfaces.rest.transform;

import com.example.agroapp.community.domain.model.aggregates.CommunityPost;
import com.example.agroapp.community.interfaces.rest.resources.CommunityPostResource;

/**
 * Assembler to transform CommunityPost entities to CommunityPostResource
 * This follows the Assembler pattern to separate domain models from API resources
 */
public class CommunityPostResourceFromEntityAssembler {

    /**
     * Transform a CommunityPost entity to a CommunityPostResource
     *
     * @param entity The CommunityPost entity
     * @return The corresponding CommunityPostResource
     */
    public static CommunityPostResource toResourceFromEntity(CommunityPost entity) {
        return new CommunityPostResource(
                entity.getId(),
                entity.getUser(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
