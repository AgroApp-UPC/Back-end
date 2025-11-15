package com.example.agroapp.field.domain.repositories;

import com.example.agroapp.field.domain.model.aggregates.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for Field aggregate.
 * Spring Data JPA will automatically generate the implementation.
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    /**
     * Find fields by name
     */
    List<Field> findByName(String name);

    /**
     * Find fields containing text in the name
     */
    List<Field> findByNameContaining(String keyword);

    /**
     * Find fields by crop type (e.g., "Trigo")
     */
    List<Field> findByCrop(String crop);

    /**
     * Find fields by product (may be same as crop in your dataset)
     */
    List<Field> findByProduct(String product);

    /**
     * Find fields by location (e.g., "Lurín")
     */
    List<Field> findByLocation(String location);

    /**
     * Find fields by health status (e.g., "Healthy")
     */
    List<Field> findByStatus(String status);

    /**
     * Find fields where planting date is after a given date
     */
    List<Field> findByPlantingDateAfter(Date date);

    /**
     * Find fields where expected harvest date is before a given date
     */
    List<Field> findByExpectedHarvestDateBefore(Date date);

    /**
     * Find fields by soil type (e.g., "Loamy Soil")
     */
    List<Field> findBySoilType(String soilType);

    /**
     * Find fields with a minimum number of days since planting
     */
    List<Field> findByDaysSincePlantingGreaterThan(Integer days);

    /**
     * Find fields with days since planting less than a value
     */
    List<Field> findByDaysSincePlantingLessThan(Integer days);
}
