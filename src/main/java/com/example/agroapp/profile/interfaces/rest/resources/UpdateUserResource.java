package com.example.agroapp.profile.interfaces.rest.resources;

public record UpdateUserResource(
        String nombre,
        String apellido,
        String email,
        String dni,
        String newPassword // puede ser null si no quiere cambiarla
) {}