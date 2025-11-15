package com.example.agroapp.field.interfaces.rest;

import com.example.agroapp.field.application.services.FieldApplicationService;
import com.example.agroapp.field.domain.model.aggregates.Field;
import com.example.agroapp.field.interfaces.rest.resources.CreateFieldResource;
import com.example.agroapp.field.interfaces.rest.resources.FieldResource;
import com.example.agroapp.field.interfaces.rest.resources.UpdateFieldResource;
import com.example.agroapp.field.interfaces.rest.transform.FieldResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Field operations
 * Exposes HTTP endpoints for managing fields
 * Base path: /api/v1/fields
 */
@RestController
@RequestMapping("/api/v1/fields")
@Tag(name = "Fields", description = "Operations related to field management")
public class FieldController {

    private final FieldApplicationService fieldApplicationService;

    @Autowired
    public FieldController(FieldApplicationService fieldApplicationService) {
        this.fieldApplicationService = fieldApplicationService;
    }

    /**
     * Create a new field
     * POST /api/v1/fields
     */
    @Operation(summary = "Create a new field", description = "Creates a new field with all required information")
    @PostMapping
    public ResponseEntity<FieldResource> createField(@Valid @RequestBody CreateFieldResource resource) {
        Field field = fieldApplicationService.createField(
                resource.name(),
                resource.imageUrl(),
                resource.product(),
                resource.location(),
                resource.fieldSize(),
                resource.crop(),
                resource.daysSincePlanting(),
                resource.plantingDate(),
                resource.expectedHarvestDate(),
                resource.soilType(),
                resource.watering(),
                resource.sunlight(),
                resource.status()
        );
        FieldResource fieldResource = FieldResourceFromEntityAssembler.toResourceFromEntity(field);
        return new ResponseEntity<>(fieldResource, HttpStatus.CREATED);
    }

    /**
     * Get all fields
     * GET /api/v1/fields
     */
    @Operation(summary = "Get all fields", description = "Retrieves all fields")
    @GetMapping
    public ResponseEntity<List<FieldResource>> getAllFields() {
        List<Field> fields = fieldApplicationService.getAllFields();
        List<FieldResource> resources = fields.stream()
                .map(FieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get a specific field by ID
     * GET /api/v1/fields/{id}
     */
    @Operation(summary = "Get field by ID", description = "Retrieves a specific field by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<FieldResource> getFieldById(@PathVariable Long id) {
        return fieldApplicationService.getFieldById(id)
                .map(field -> ResponseEntity.ok(
                        FieldResourceFromEntityAssembler.toResourceFromEntity(field)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get fields by name
     * GET /api/v1/fields?name={name}
     */
    @Operation(summary = "Get fields by name", description = "Retrieves all fields with a specific name")
    @GetMapping(params = "name")
    public ResponseEntity<List<FieldResource>> getFieldsByName(@RequestParam String name) {
        List<Field> fields = fieldApplicationService.getFieldsByName(name);
        List<FieldResource> resources = fields.stream()
                .map(FieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get fields by status
     * GET /api/v1/fields?status={status}
     */
    @Operation(summary = "Get fields by status", description = "Retrieves all fields with a specific status")
    @GetMapping(params = "status")
    public ResponseEntity<List<FieldResource>> getFieldsByStatus(@RequestParam String status) {
        List<Field> fields = fieldApplicationService.getFieldsByStatus(status);
        List<FieldResource> resources = fields.stream()
                .map(FieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Get fields by location
     * GET /api/v1/fields?location={location}
     */
    @Operation(summary = "Get fields by location", description = "Retrieves all fields in a specific location")
    @GetMapping(params = "location")
    public ResponseEntity<List<FieldResource>> getFieldsByLocation(@RequestParam String location) {
        List<Field> fields = fieldApplicationService.getFieldsByLocation(location);
        List<FieldResource> resources = fields.stream()
                .map(FieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Search fields by keyword in name
     * GET /api/v1/fields/search?keyword={keyword}
     */
    @Operation(summary = "Search fields", description = "Search fields by keyword in name")
    @GetMapping("/search")
    public ResponseEntity<List<FieldResource>> searchFields(@RequestParam String keyword) {
        List<Field> fields = fieldApplicationService.searchFieldsByName(keyword);
        List<FieldResource> resources = fields.stream()
                .map(FieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update a field
     * PUT /api/v1/fields/{id}
     */
    @Operation(summary = "Update a field", description = "Updates all information of an existing field")
    @PutMapping("/{id}")
    public ResponseEntity<FieldResource> updateField(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFieldResource resource) {

        return fieldApplicationService.updateField(
                        id,
                        resource.name(),
                        resource.imageUrl(),
                        resource.product(),
                        resource.location(),
                        resource.fieldSize(),
                        resource.crop(),
                        resource.daysSincePlanting(),
                        resource.plantingDate(),
                        resource.expectedHarvestDate(),
                        resource.soilType(),
                        resource.watering(),
                        resource.sunlight(),
                        resource.status()
                )
                .map(field -> ResponseEntity.ok(
                        FieldResourceFromEntityAssembler.toResourceFromEntity(field)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update only the status of a field
     * PATCH /api/v1/fields/{id}/status?status={status}
     */
    @Operation(summary = "Update field status", description = "Updates only the status of a field")
    @PatchMapping("/{id}/status")
    public ResponseEntity<FieldResource> updateFieldStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return fieldApplicationService.updateFieldStatus(id, status)
                .map(field -> ResponseEntity.ok(
                        FieldResourceFromEntityAssembler.toResourceFromEntity(field)
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a field
     * DELETE /api/v1/fields/{id}
     */
    @Operation(summary = "Delete a field", description = "Deletes a field by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        boolean deleted = fieldApplicationService.deleteField(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
