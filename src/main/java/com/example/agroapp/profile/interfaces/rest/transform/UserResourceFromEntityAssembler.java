package com.example.agroapp.profile.interfaces.rest.transform;

import com.example.agroapp.profile.domain.model.aggregates.User;
import com.example.agroapp.profile.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEmail(),
                entity.getDni(),
                entity.getRol().name(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}