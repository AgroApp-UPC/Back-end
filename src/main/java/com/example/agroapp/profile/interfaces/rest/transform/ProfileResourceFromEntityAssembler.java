package com.example.agroapp.profile.interfaces.rest.transform;

import com.example.agroapp.profile.domain.model.aggregates.Profile;
import com.example.agroapp.profile.interfaces.rest.resources.ProfileResource;
import com.example.agroapp.profile.domain.model.aggregates.User;
import org.springframework.stereotype.Component;

@Component

public class ProfileResourceFromEntityAssembler {


    public ProfileResource toResource(User user, Profile profile) {
        return new ProfileResource(
                user.getNombre(),
                user.getApellido(),
                user.getEmail(),
                user.getDni(),
                user.getRol().name(),
                profile.getAvatarUrl(),
                profile.isNotificationsEnabled(),
                profile.isAlertsEnabled(),
                profile.getLanguage()
        );
    }
}