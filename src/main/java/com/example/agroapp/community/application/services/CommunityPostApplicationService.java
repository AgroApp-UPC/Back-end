package com.example.agroapp.community.application.services;

import com.example.agroapp.community.domain.model.aggregates.CommunityPost;
import com.example.agroapp.community.domain.repositories.CommunityPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Application service for CommunityPost use cases
 * This service contains the business logic for managing community posts
 * It coordinates between the domain model and the repository
 */
@Service
public class CommunityPostApplicationService {

    private final CommunityPostRepository communityPostRepository;

    @Autowired
    public CommunityPostApplicationService(CommunityPostRepository communityPostRepository) {
        this.communityPostRepository = communityPostRepository;
    }

    /**
     * Create a new community post
     *
     * @param user The user creating the post
     * @param description The content of the post
     * @return The created CommunityPost
     */
    @Transactional
    public CommunityPost createPost(String user, String description) {
        CommunityPost post = new CommunityPost(user, description);
        return communityPostRepository.save(post);
    }

    /**
     * Get all community posts
     *
     * @return List of all posts
     */
    @Transactional(readOnly = true)
    public List<CommunityPost> getAllPosts() {
        return communityPostRepository.findAll();
    }

    /**
     * Get a specific post by ID
     *
     * @param id The post ID
     * @return Optional containing the post if found
     */
    @Transactional(readOnly = true)
    public Optional<CommunityPost> getPostById(Long id) {
        return communityPostRepository.findById(id);
    }

    /**
     * Get all posts by a specific user
     *
     * @param user The username
     * @return List of posts by that user
     */
    @Transactional(readOnly = true)
    public List<CommunityPost> getPostsByUser(String user) {
        return communityPostRepository.findByUser(user);
    }

    /**
     * Search posts by keyword in description
     *
     * @param keyword The keyword to search for
     * @return List of posts containing the keyword
     */
    @Transactional(readOnly = true)
    public List<CommunityPost> searchPosts(String keyword) {
        return communityPostRepository.findByDescriptionContaining(keyword);
    }

    /**
     * Update a post's description
     *
     * @param id The post ID
     * @param description The new description
     * @return Optional containing the updated post if found
     */
    @Transactional
    public Optional<CommunityPost> updatePost(Long id, String description) {
        return communityPostRepository.findById(id)
                .map(post -> {
                    post.updateDescription(description);
                    return communityPostRepository.save(post);
                });
    }

    /**
     * Delete a post
     *
     * @param id The post ID
     * @return true if deleted, false if not found
     */
    @Transactional
    public boolean deletePost(Long id) {
        if (communityPostRepository.existsById(id)) {
            communityPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
