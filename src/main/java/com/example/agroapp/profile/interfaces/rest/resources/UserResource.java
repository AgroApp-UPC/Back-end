package com.example.agroapp.profile.interfaces.rest.resources;

import java.util.Date;

public record UserResource(
        Long id,
        String nombre,
        String apellido,
        String email,
        String dni,
        String rol,
        Date createdAt,
        Date updatedAt
) {}