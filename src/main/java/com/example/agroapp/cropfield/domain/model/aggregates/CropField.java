package com.example.agroapp.cropfield.domain.model.aggregates;

import com.example.agroapp.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

/**
 * CropField aggregate root
 * Represents a crop field with planting information, location, and health status.
 * Extends AuditableAbstractAggregateRoot to inherit id, createdAt, updatedAt fields,
 * and domain event support.
 */
@Entity
@Getter
public class CropField extends AuditableAbstractAggregateRoot<CropField> {

    /**
     * The title/name of the crop (e.g., "Wheat", "Corn")
     */
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * Number of days since planting or until harvest
     */
    @NotNull(message = "Days is required")
    @Column(nullable = false)
    private Integer days;

    /**
     * Date when the crop was planted
     */
    @NotNull(message = "Planting date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date plantingDate;

    /**
     * Expected or actual harvest date
     */
    @NotNull(message = "Harvest date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date harvestDate;

    /**
     * Field location or name (e.g., "Grain Field, Los Grandes")
     */
    @NotBlank(message = "Field location is required")
    @Size(max = 200, message = "Field location must not exceed 200 characters")
    @Column(nullable = false, length = 200)
    private String field;

    /**
     * Health status of the crop (e.g., "Healthy", "Needs Attention")
     */
    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String status;

    /**
     * Default constructor required by JPA
     */
    protected CropField() {
    }

    /**
     * Constructor for creating a new crop field
     *
     * @param title The crop name
     * @param days Number of days
     * @param plantingDate When the crop was planted
     * @param harvestDate Expected harvest date
     * @param field Field location
     * @param status Health status
     */
    public CropField(String title, Integer days, Date plantingDate, Date harvestDate, String field, String status) {
        this.title = title;
        this.days = days;
        this.plantingDate = plantingDate;
        this.harvestDate = harvestDate;
        this.field = field;
        this.status = status;
    }

    /**
     * Update the crop field information
     *
     * @param title The crop name
     * @param days Number of days
     * @param plantingDate Planting date
     * @param harvestDate Harvest date
     * @param field Field location
     * @param status Health status
     * @return The updated CropField
     */
    public CropField update(String title, Integer days, Date plantingDate, Date harvestDate, String field, String status) {
        this.title = title;
        this.days = days;
        this.plantingDate = plantingDate;
        this.harvestDate = harvestDate;
        this.field = field;
        this.status = status;
        return this;
    }

    /**
     * Update only the status of the crop
     *
     * @param status The new status
     * @return The updated CropField
     */
    public CropField updateStatus(String status) {
        this.status = status;
        return this;
    }
}
