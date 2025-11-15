package com.example.agroapp.field.application.services;

import com.example.agroapp.field.domain.model.aggregates.Field;
import com.example.agroapp.field.domain.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Application service for Field use cases
 * This service contains the business logic for managing fields
 * It coordinates between the domain model and the repository
 */
@Service
public class FieldApplicationService {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldApplicationService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    /**
     * Create a new field
     *
     * @param name               Field name
     * @param imageUrl           Image URL
     * @param product            Product
     * @param location           Location
     * @param fieldSize          Field size
     * @param crop               Crop type
     * @param daysSincePlanting  Days since planting
     * @param plantingDate       Planting date
     * @param expectedHarvestDate Expected harvest date
     * @param soilType           Soil type
     * @param watering           Watering schedule
     * @param sunlight           Sunlight exposure
     * @param status             Health status
     * @return The created Field
     */
    @Transactional
    public Field createField(String name,
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

        Field field = new Field(name, imageUrl, product, location, fieldSize, crop,
                daysSincePlanting, plantingDate, expectedHarvestDate, soilType, watering, sunlight, status);
        return fieldRepository.save(field);
    }

    /**
     * Get all fields
     *
     * @return List of all fields
     */
    @Transactional(readOnly = true)
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    /**
     * Get a specific field by ID
     *
     * @param id The field ID
     * @return Optional containing the field if found
     */
    @Transactional(readOnly = true)
    public Optional<Field> getFieldById(Long id) {
        return fieldRepository.findById(id);
    }

    /**
     * Get all fields by name
     *
     * @param name The field name
     * @return List of fields with that name
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsByName(String name) {
        return fieldRepository.findByName(name);
    }

    /**
     * Search fields by name keyword
     *
     * @param keyword The keyword to search for
     * @return List of fields containing the keyword in the name
     */
    @Transactional(readOnly = true)
    public List<Field> searchFieldsByName(String keyword) {
        return fieldRepository.findByNameContaining(keyword);
    }

    /**
     * Get all fields by crop type
     *
     * @param crop The crop type
     * @return List of fields with that crop
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsByCrop(String crop) {
        return fieldRepository.findByCrop(crop);
    }

    /**
     * Get all fields by product
     *
     * @param product The product
     * @return List of fields with that product
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsByProduct(String product) {
        return fieldRepository.findByProduct(product);
    }

    /**
     * Get all fields by location
     *
     * @param location The location
     * @return List of fields in that location
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsByLocation(String location) {
        return fieldRepository.findByLocation(location);
    }

    /**
     * Get all fields by status
     *
     * @param status The health status
     * @return List of fields with that status
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsByStatus(String status) {
        return fieldRepository.findByStatus(status);
    }

    /**
     * Get fields planted after a specific date
     *
     * @param date The date to compare
     * @return List of fields planted after the date
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsPlantedAfter(Date date) {
        return fieldRepository.findByPlantingDateAfter(date);
    }

    /**
     * Get fields to be harvested before a specific date
     *
     * @param date The date to compare
     * @return List of fields to be harvested before the date
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsToHarvestBefore(Date date) {
        return fieldRepository.findByExpectedHarvestDateBefore(date);
    }

    /**
     * Get fields by soil type
     *
     * @param soilType The soil type
     * @return List of fields with that soil type
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsBySoilType(String soilType) {
        return fieldRepository.findBySoilType(soilType);
    }

    /**
     * Get fields with days since planting greater than a value
     *
     * @param days The minimum days since planting
     * @return List of fields that satisfy the condition
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsWithMinDaysSincePlanting(Integer days) {
        return fieldRepository.findByDaysSincePlantingGreaterThan(days);
    }

    /**
     * Get fields with days since planting less than a value
     *
     * @param days The maximum days since planting
     * @return List of fields that satisfy the condition
     */
    @Transactional(readOnly = true)
    public List<Field> getFieldsWithMaxDaysSincePlanting(Integer days) {
        return fieldRepository.findByDaysSincePlantingLessThan(days);
    }

    /**
     * Update a field
     *
     * @param id                  The field ID
     * @param name                Field name
     * @param imageUrl            Image URL
     * @param product             Product
     * @param location            Location
     * @param fieldSize           Field size
     * @param crop                Crop type
     * @param daysSincePlanting   Days since planting
     * @param plantingDate        Planting date
     * @param expectedHarvestDate Expected harvest date
     * @param soilType            Soil type
     * @param watering            Watering schedule
     * @param sunlight            Sunlight exposure
     * @param status              Health status
     * @return Optional containing the updated field if found
     */
    @Transactional
    public Optional<Field> updateField(Long id,
                                       String name,
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

        return fieldRepository.findById(id)
                .map(field -> {
                    field.update(name, imageUrl, product, location, fieldSize, crop,
                            daysSincePlanting, plantingDate, expectedHarvestDate,
                            soilType, watering, sunlight, status);
                    return fieldRepository.save(field);
                });
    }

    /**
     * Update only the status of a field
     *
     * @param id     The field ID
     * @param status The new status
     * @return Optional containing the updated field if found
     */
    @Transactional
    public Optional<Field> updateFieldStatus(Long id, String status) {
        return fieldRepository.findById(id)
                .map(field -> {
                    field.updateStatus(status);
                    return fieldRepository.save(field);
                });
    }

    /**
     * Delete a field
     *
     * @param id The field ID
     * @return true if deleted, false if not found
     */
    @Transactional
    public boolean deleteField(Long id) {
        if (fieldRepository.existsById(id)) {
            fieldRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
