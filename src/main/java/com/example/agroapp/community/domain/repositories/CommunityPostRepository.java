package com.example.agroapp.community.domain.repositories;

import com.example.agroapp.community.domain.model.aggregates.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for CommunityPost aggregate
 * Spring Data JPA will automatically provide the implementation
 */
@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    /**
     * Find all posts by a specific user
     *
     * @param user The username to search for
     * @return List of posts by that user
     */
    List<CommunityPost> findByUser(String user);

    /**
     * Find posts containing specific text in the description
     *
     * @param keyword The keyword to search for
     * @return List of posts containing the keyword
     */
    List<CommunityPost> findByDescriptionContaining(String keyword);
}
