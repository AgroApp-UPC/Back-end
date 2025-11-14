package com.example.agroapp.cropfield.interfaces.rest;

import com.example.agroapp.cropfield.application.services.CropFieldApplicationService;
import com.example.agroapp.cropfield.domain.model.aggregates.CropField;
import com.example.agroapp.cropfield.interfaces.rest.resources.CropFieldResource;
import com.example.agroapp.cropfield.interfaces.rest.resources.CreateCropFieldResource;
import com.example.agroapp.cropfield.interfaces.rest.resources.UpdateCropFieldResource;
import com.example.agroapp.cropfield.interfaces.rest.transform.CropFieldResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Crop Field operations
 * Exposes HTTP endpoints for managing crop fields
 * Base path: /api/v1/crop-fields
 */
@RestController
@RequestMapping("/api/v1/crop-fields")
@Tag(name = "Crop Fields", description = "Operations related to crop field management")
public class CropFieldController {

    private final CropFieldApplicationService cropFieldApplicationService;

    @Autowired
    public CropFieldController(CropFieldApplicationService cropFieldApplicationService) {
        this.cropFieldApplicationService = cropFieldApplicationService;
    }

    /**
     * Create a new crop field
     * POST /api/v1/crop-fields
     */
    @Operation(summary = "Create a new crop field", description = "Creates a new crop field with all required information")
    @PostMapping
    public ResponseEntity<CropFieldResource> createCropField(@Valid @RequestBody CreateCropFieldResource resource) {
        CropField cropField = cropFieldApplicationService.createCropField(
                resource.title(),
                resource.days(),
                resource.plantingDate(),
                resource.harvestDate(),
                resource.field(),
                resource.status()
        );
        CropFieldResource cropFieldResource = CropFieldResourceFromEntityAssembler.toResourceFromEntity(cropField);
        return new ResponseEntity<>(cropFieldResource, HttpStatus.CREATED);
    }

    /**
     * Get all crop fields
     * GET /api/v1/crop-fields
     */
    @Operation(summary = "Get all crop fields", description = "Retrieves all crop fields")
    @GetMapping
    public ResponseEntity<List<CropFieldResource>> getAllCropFields() {
        List<CropField> cropFields = cropFieldApplicationService.getAllCropFields();
        List<CropFieldResource> resources = cropFields.stream()
                .map(CropFieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get a specific crop field by ID
     * GET /api/v1/crop-fields/{id}
     */
    @Operation(summary = "Get crop field by ID", description = "Retrieves a specific crop field by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<CropFieldResource> getCropFieldById(@PathVariable Long id) {
        return cropFieldApplicationService.getCropFieldById(id)
                .map(cropField -> ResponseEntity.ok(
                        CropFieldResourceFromEntityAssembler.toResourceFromEntity(cropField)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get crop fields by title
     * GET /api/v1/crop-fields?title={title}
     */
    @Operation(summary = "Get crop fields by title", description = "Retrieves all crop fields with a specific title")
    @GetMapping(params = "title")
    public ResponseEntity<List<CropFieldResource>> getCropFieldsByTitle(@RequestParam String title) {
        List<CropField> cropFields = cropFieldApplicationService.getCropFieldsByTitle(title);
        List<CropFieldResource> resources = cropFields.stream()
                .map(CropFieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get crop fields by status
     * GET /api/v1/crop-fields?status={status}
     */
    @Operation(summary = "Get crop fields by status", description = "Retrieves all crop fields with a specific status")
    @GetMapping(params = "status")
    public ResponseEntity<List<CropFieldResource>> getCropFieldsByStatus(@RequestParam String status) {
        List<CropField> cropFields = cropFieldApplicationService.getCropFieldsByStatus(status);
        List<CropFieldResource> resources = cropFields.stream()
                .map(CropFieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get crop fields by field location
     * GET /api/v1/crop-fields?field={field}
     */
    @Operation(summary = "Get crop fields by location", description = "Retrieves all crop fields in a specific location")
    @GetMapping(params = "field")
    public ResponseEntity<List<CropFieldResource>> getCropFieldsByField(@RequestParam String field) {
        List<CropField> cropFields = cropFieldApplicationService.getCropFieldsByField(field);
        List<CropFieldResource> resources = cropFields.stream()
                .map(CropFieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Search crop fields by keyword
     * GET /api/v1/crop-fields/search?keyword={keyword}
     */
    @Operation(summary = "Search crop fields", description = "Search crop fields by keyword in title")
    @GetMapping("/search")
    public ResponseEntity<List<CropFieldResource>> searchCropFields(@RequestParam String keyword) {
        List<CropField> cropFields = cropFieldApplicationService.searchCropFields(keyword);
        List<CropFieldResource> resources = cropFields.stream()
                .map(CropFieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Update a crop field", description = "Updates all information of an existing crop field")
    @PutMapping("/{id}")
    public ResponseEntity<CropFieldResource> updateCropField(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCropFieldResource resource) {
        return cropFieldApplicationService.updateCropField(
                        id,
                        resource.title(),
                        resource.days(),
                        resource.plantingDate(),
                        resource.harvestDate(),
                        resource.field(),
                        resource.status()
                )
                .map(cropField -> ResponseEntity.ok(
                        CropFieldResourceFromEntityAssembler.toResourceFromEntity(cropField)
                ))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Update crop field status", description = "Updates only the status of a crop field")
    @PatchMapping("/{id}/status")
    public ResponseEntity<CropFieldResource> updateCropFieldStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return cropFieldApplicationService.updateCropFieldStatus(id, status)
                .map(cropField -> ResponseEntity.ok(
                        CropFieldResourceFromEntityAssembler.toResourceFromEntity(cropField)
                ))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a crop field", description = "Deletes a crop field by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCropField(@PathVariable Long id) {
        boolean deleted = cropFieldApplicationService.deleteCropField(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
