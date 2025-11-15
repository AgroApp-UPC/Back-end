package com.example.agroapp.profile.interfaces.rest.resources;

import java.util.Date;

public record ProfileActivityResource(
        String description,
        Date occurredAt
) {}