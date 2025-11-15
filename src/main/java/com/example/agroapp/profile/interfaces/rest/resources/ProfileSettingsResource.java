package com.example.agroapp.profile.interfaces.rest.resources;

public record ProfileSettingsResource(
        String language,
        boolean notificationsEnabled,
        boolean alertsEnabled
) {}