package com.example.agroapp.community.interfaces.rest;

import com.example.agroapp.community.application.services.CommunityPostApplicationService;
import com.example.agroapp.community.domain.model.aggregates.CommunityPost;
import com.example.agroapp.community.interfaces.rest.resources.CommunityPostResource;
import com.example.agroapp.community.interfaces.rest.resources.CreateCommunityPostResource;
import com.example.agroapp.community.interfaces.rest.resources.UpdateCommunityPostResource;
import com.example.agroapp.community.interfaces.rest.transform.CommunityPostResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Community Post operations
 * Exposes HTTP endpoints for managing community posts
 * Base path: /api/v1/community/posts
 */
@RestController
@RequestMapping("/api/v1/community/posts")
@Tag(name = "Community Posts", description = "Operations related to community posts and tips")
public class CommunityPostController {

    private final CommunityPostApplicationService communityPostApplicationService;

    @Autowired
    public CommunityPostController(CommunityPostApplicationService communityPostApplicationService) {
        this.communityPostApplicationService = communityPostApplicationService;
    }

    /**
     * Create a new community post
     * POST /api/v1/community/posts
     */
    @Operation(summary = "Create a new community post", description = "Creates a new post with user and description")
    @PostMapping
    public ResponseEntity<CommunityPostResource> createPost(@Valid @RequestBody CreateCommunityPostResource resource) {
        CommunityPost post = communityPostApplicationService.createPost(
                resource.user(),
                resource.description()
        );
        CommunityPostResource postResource = CommunityPostResourceFromEntityAssembler.toResourceFromEntity(post);
        return new ResponseEntity<>(postResource, HttpStatus.CREATED);
    }

    /**
     * Get all community posts
     * GET /api/v1/community/posts
     */
    @Operation(summary = "Get all community posts", description = "Retrieves all community posts")
    @GetMapping
    public ResponseEntity<List<CommunityPostResource>> getAllPosts() {
        List<CommunityPost> posts = communityPostApplicationService.getAllPosts();
        List<CommunityPostResource> resources = posts.stream()
                .map(CommunityPostResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get a specific post by ID
     * GET /api/v1/community/posts/{id}
     */
    @Operation(summary = "Get post by ID", description = "Retrieves a specific community post by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<CommunityPostResource> getPostById(@PathVariable Long id) {
        return communityPostApplicationService.getPostById(id)
                .map(post -> ResponseEntity.ok(
                        CommunityPostResourceFromEntityAssembler.toResourceFromEntity(post)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get posts by user
     * GET /api/v1/community/posts?user={username}
     */
    @Operation(summary = "Get posts by user", description = "Retrieves all posts from a specific user")
    @GetMapping(params = "user")
    public ResponseEntity<List<CommunityPostResource>> getPostsByUser(@RequestParam String user) {
        List<CommunityPost> posts = communityPostApplicationService.getPostsByUser(user);
        List<CommunityPostResource> resources = posts.stream()
                .map(CommunityPostResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Search posts by keyword
     * GET /api/v1/community/posts/search?keyword={keyword}
     */
    @Operation(summary = "Search posts", description = "Search posts by keyword in description")
    @GetMapping("/search")
    public ResponseEntity<List<CommunityPostResource>> searchPosts(@RequestParam String keyword) {
        List<CommunityPost> posts = communityPostApplicationService.searchPosts(keyword);
        List<CommunityPostResource> resources = posts.stream()
                .map(CommunityPostResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update a post
     * PUT /api/v1/community/posts/{id}
     */
    @Operation(summary = "Update a post", description = "Updates the description of an existing post")
    @PutMapping("/{id}")
    public ResponseEntity<CommunityPostResource> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommunityPostResource resource) {
        return communityPostApplicationService.updatePost(id, resource.description())
                .map(post -> ResponseEntity.ok(
                        CommunityPostResourceFromEntityAssembler.toResourceFromEntity(post)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a post
     * DELETE /api/v1/community/posts/{id}
     */
    @Operation(summary = "Delete a post", description = "Deletes a community post by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean deleted = communityPostApplicationService.deletePost(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
