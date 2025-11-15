package com.example.agroapp.profile.domain.model.aggregates;

import com.example.agroapp.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles") // naming strategy -> profiles
public class Profile extends AuditableModel {

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 255)
    private String avatarUrl;

    @Column(length = 10)
    private String language; // ej: "es", "en"

    @Column(nullable = false)
    private boolean notificationsEnabled;

    @Column(nullable = false)
    private boolean alertsEnabled;
}
