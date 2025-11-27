package com.example.agroapp.iam.domain.model.commands;

public record UpdateUserPasswordCommand(
        Long userId,
        String currentPassword,
        String newPassword
) {
}

