package com.example.agroapp.profile.application.internal;

import com.example.agroapp.profile.domain.model.aggregates.Profile;
import com.example.agroapp.profile.domain.model.aggregates.User;
import com.example.agroapp.profile.domain.repositories.ProfileRepository;
import com.example.agroapp.profile.interfaces.rest.resources.*;
import com.example.agroapp.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.example.agroapp.profile.domain.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Transactional
@RequiredArgsConstructor

public class ProfileApplicationService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileResourceFromEntityAssembler profileAssembler;

    public Profile getOrCreateProfileForUser(User user) {
        return profileRepository.findByUser(user)
                .orElseGet(() -> {
                    Profile profile = Profile.builder()
                            .user(user)
                            .avatarUrl(null)
                            .language("es")
                            .notificationsEnabled(true)
                            .alertsEnabled(true)
                            .build();
                    return profileRepository.save(profile);
                });
    }

    public ProfileResource getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Profile profile = getOrCreateProfileForUser(user);

        return profileAssembler.toResource(user, profile);
    }

    public ProfileResource updateProfile(String email, UpdateProfileResource resource) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Profile profile = getOrCreateProfileForUser(user);

        if (resource.avatarUrl() != null) profile.setAvatarUrl(resource.avatarUrl());
        if (resource.language() != null) profile.setLanguage(resource.language());
        if (resource.notificationsEnabled() != null) profile.setNotificationsEnabled(resource.notificationsEnabled());
        if (resource.alertsEnabled() != null) profile.setAlertsEnabled(resource.alertsEnabled());

        Profile updated = profileRepository.save(profile);
        return profileAssembler.toResource(user, updated);
    }

    public void resetProfileSettings(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Profile profile = getOrCreateProfileForUser(user);

        profile.setLanguage("es");
        profile.setNotificationsEnabled(true);
        profile.setAlertsEnabled(true);
        profileRepository.save(profile);
    }

    public ProfileSettingsResource getSettings(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        Profile profile = getOrCreateProfileForUser(user);

        return new ProfileSettingsResource(
                profile.getLanguage(),
                profile.isNotificationsEnabled(),
                profile.isAlertsEnabled()
        );
    }

    public ProfileResource getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return profileAssembler.toResource(user, profile);
    }

}