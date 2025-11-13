package com.example.agroapp.community.domain.model.aggregates;

import com.example.agroapp.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * CommunityPost aggregate root
 * Represents a community post or tip shared by users in the agricultural community.
 * Extends AuditableAbstractAggregateRoot to inherit id, createdAt, updatedAt fields,
 * and domain event support.
 */
@Entity
@Getter
public class CommunityPost extends AuditableAbstractAggregateRoot<CommunityPost> {

    /**
     * The name or identifier of the user who created the post
     */
    @NotBlank(message = "User name is required")
    @Size(max = 100, message = "User name must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String user;

    /**
     * The content/description of the community post
     */
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;

    /**
     * Default constructor required by JPA
     */
    protected CommunityPost() {
    }

    /**
     * Constructor for creating a new community post
     *
     * @param user The user who is creating the post
     * @param description The content of the post
     */
    public CommunityPost(String user, String description) {
        this.user = user;
        this.description = description;
    }

    /**
     * Update the post content
     *
     * @param description The new description
     * @return The updated CommunityPost
     */
    public CommunityPost updateDescription(String description) {
        this.description = description;
        return this;
    }
}
