package com.example.agroapp.field.domain.model.aggregates;

import com.example.agroapp.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

/**
 * Field aggregate root
 * Representa un campo agrícola con información general,
 * cultivo, riego y estado de salud.
 *
 * Extiende AuditableAbstractAggregateRoot para heredar
 * id, createdAt, updatedAt y soporte de domain events.
 */
@Entity
@Getter
public class Field extends AuditableAbstractAggregateRoot<Field> {

    /**
     * Nombre del campo (e.g., "Campo de Granos, Los Grandes")
     */
    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    @Column(nullable = false, length = 200)
    private String name;

    /**
     * URL de la imagen del campo
     */
    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Column(length = 500)
    private String imageUrl;

    /**
     * Producto principal (e.g., "Trigo")
     */
    @NotBlank(message = "Product is required")
    @Size(max = 100, message = "Product must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String product;

    /**
     * Ubicación del campo (e.g., "Lurín")
     */
    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String location;

    /**
     * Tamaño del campo (e.g., "5,000 m2")
     * Podrías normalizar esto a un valor numérico + unidad si lo necesitas.
     */
    @NotBlank(message = "Field size is required")
    @Size(max = 50, message = "Field size must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String fieldSize;

    /**
     * Tipo de cultivo (e.g., "Trigo")
     */
    @NotBlank(message = "Crop is required")
    @Size(max = 100, message = "Crop must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String crop;

    /**
     * Días desde la siembra
     */
    @NotNull(message = "Days since planting is required")
    @Column(nullable = false)
    private Integer daysSincePlanting;

    /**
     * Fecha de siembra
     */
    @NotNull(message = "Planting date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date plantingDate;

    /**
     * Fecha esperada de cosecha
     */
    @NotNull(message = "Expected harvest date is required")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date expectedHarvestDate;

    /**
     * Tipo de suelo (e.g., "Loamy Soil")
     */
    @NotBlank(message = "Soil type is required")
    @Size(max = 100, message = "Soil type must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String soilType;

    /**
     * Esquema de riego (e.g., "2x daily, 1500L each time")
     */
    @NotBlank(message = "Watering info is required")
    @Size(max = 100, message = "Watering info must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String watering;

    /**
     * Exposición solar (e.g., "7 hours/day")
     */
    @NotBlank(message = "Sunlight info is required")
    @Size(max = 100, message = "Sunlight info must not exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String sunlight;

    /**
     * Estado de salud del campo (e.g., "Healthy")
     */
    @NotBlank(message = "Status is required")
    @Size(max = 50, message = "Status must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String status;

    /**
     * Constructor por defecto requerido por JPA
     */
    protected Field() {
    }

    /**
     * Constructor para crear un nuevo Field
     */
    public Field(String name,
                 String imageUrl,
                 String product,
                 String location,
                 String fieldSize,
                 String crop,
                 Integer daysSincePlanting,
                 Date plantingDate,
                 Date expectedHarvestDate,
                 String soilType,
                 String watering,
                 String sunlight,
                 String status) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.product = product;
        this.location = location;
        this.fieldSize = fieldSize;
        this.crop = crop;
        this.daysSincePlanting = daysSincePlanting;
        this.plantingDate = plantingDate;
        this.expectedHarvestDate = expectedHarvestDate;
        this.soilType = soilType;
        this.watering = watering;
        this.sunlight = sunlight;
        this.status = status;
    }

    /**
     * Actualiza toda la información del Field
     */
    public Field update(String name,
                        String imageUrl,
                        String product,
                        String location,
                        String fieldSize,
                        String crop,
                        Integer daysSincePlanting,
                        Date plantingDate,
                        Date expectedHarvestDate,
                        String soilType,
                        String watering,
                        String sunlight,
                        String status) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.product = product;
        this.location = location;
        this.fieldSize = fieldSize;
        this.crop = crop;
        this.daysSincePlanting = daysSincePlanting;
        this.plantingDate = plantingDate;
        this.expectedHarvestDate = expectedHarvestDate;
        this.soilType = soilType;
        this.watering = watering;
        this.sunlight = sunlight;
        this.status = status;
        return this;
    }

    /**
     * Actualiza sólo el estado del Field
     */
    public Field updateStatus(String status) {
        this.status = status;
        return this;
    }
}
