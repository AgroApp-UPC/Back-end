package com.example.agroapp.profile.domain.repositories;

import com.example.agroapp.profile.domain.model.aggregates.Profile;
import com.example.agroapp.profile.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUser(User user);
    Optional<Profile> findByUserId(Long userId);

    void deleteByUser(User user);
}