package com.example.agroapp.profile.interfaces.rest.resources;

public record ProfileResource(
        String nombre,
        String apellido,
        String email,
        String dni,
        String rol,
        String avatarUrl,
        boolean notificationsEnabled,
        boolean alertsEnabled,
        String language
) {}