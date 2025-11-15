package com.example.agroapp.profile.interfaces.rest.resources;

public record SignupRequest(
        String nombre,
        String apellido,
        String email,
        String password,
        String dni,
        String rol // "ADMIN" o "FARMER"
) {}
