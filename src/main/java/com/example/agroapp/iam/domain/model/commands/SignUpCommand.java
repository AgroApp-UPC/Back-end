package com.example.agroapp.iam.domain.model.commands;

public record SignUpCommand(
        String userName,
        String email,
        String password,
        String phoneNumber,
        String identificator
) {
}

