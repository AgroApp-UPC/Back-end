package com.example.agroapp.cropfield.application.services;

import com.example.agroapp.cropfield.domain.model.aggregates.CropField;
import com.example.agroapp.cropfield.domain.repositories.CropFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Application service for CropField use cases
 * This service contains the business logic for managing crop fields
 * It coordinates between the domain model and the repository
 */
@Service
public class CropFieldApplicationService {

    private final CropFieldRepository cropFieldRepository;

    @Autowired
    public CropFieldApplicationService(CropFieldRepository cropFieldRepository) {
        this.cropFieldRepository = cropFieldRepository;
    }

    /**
     * Create a new crop field
     *
     * @param title Crop name
     * @param days Number of days
     * @param plantingDate Planting date
     * @param harvestDate Harvest date
     * @param field Field location
     * @param status Health status
     * @return The created CropField
     */
    @Transactional
    public CropField createCropField(String title, Integer days, Date plantingDate, Date harvestDate,
                                     String field, String status) {
        CropField cropField = new CropField(title, days, plantingDate, harvestDate, field, status);
        return cropFieldRepository.save(cropField);
    }

    /**
     * Get all crop fields
     *
     * @return List of all crop fields
     */
    @Transactional(readOnly = true)
    public List<CropField> getAllCropFields() {
        return cropFieldRepository.findAll();
    }

    /**
     * Get a specific crop field by ID
     *
     * @param id The crop field ID
     * @return Optional containing the crop field if found
     */
    @Transactional(readOnly = true)
    public Optional<CropField> getCropFieldById(Long id) {
        return cropFieldRepository.findById(id);
    }

    /**
     * Get all crop fields by title (crop name)
     *
     * @param title The crop title
     * @return List of crop fields with that title
     */
    @Transactional(readOnly = true)
    public List<CropField> getCropFieldsByTitle(String title) {
        return cropFieldRepository.findByTitle(title);
    }

    /**
     * Get all crop fields by status
     *
     * @param status The health status
     * @return List of crop fields with that status
     */
    @Transactional(readOnly = true)
    public List<CropField> getCropFieldsByStatus(String status) {
        return cropFieldRepository.findByStatus(status);
    }

    /**
     * Get all crop fields by field location
     *
     * @param field The field location
     * @return List of crop fields in that location
     */
    @Transactional(readOnly = true)
    public List<CropField> getCropFieldsByField(String field) {
        return cropFieldRepository.findByField(field);
    }

    /**
     * Search crop fields by keyword in title
     *
     * @param keyword The keyword to search for
     * @return List of crop fields containing the keyword
     */
    @Transactional(readOnly = true)
    public List<CropField> searchCropFields(String keyword) {
        return cropFieldRepository.findByTitleContaining(keyword);
    }

    /**
     * Get crop fields planted after a specific date
     *
     * @param date The date to compare
     * @return List of crop fields planted after the date
     */
    @Transactional(readOnly = true)
    public List<CropField> getCropFieldsPlantedAfter(Date date) {
        return cropFieldRepository.findByPlantingDateAfter(date);
    }

    /**
     * Get crop fields to be harvested before a specific date
     *
     * @param date The date to compare
     * @return List of crop fields to be harvested before the date
     */
    @Transactional(readOnly = true)
    public List<CropField> getCropFieldsToHarvestBefore(Date date) {
        return cropFieldRepository.findByHarvestDateBefore(date);
    }

    /**
     * Update a crop field
     *
     * @param id The crop field ID
     * @param title Crop name
     * @param days Number of days
     * @param plantingDate Planting date
     * @param harvestDate Harvest date
     * @param field Field location
     * @param status Health status
     * @return Optional containing the updated crop field if found
     */
    @Transactional
    public Optional<CropField> updateCropField(Long id, String title, Integer days, Date plantingDate,
                                               Date harvestDate, String field, String status) {
        return cropFieldRepository.findById(id)
                .map(cropField -> {
                    cropField.update(title, days, plantingDate, harvestDate, field, status);
                    return cropFieldRepository.save(cropField);
                });
    }

    /**
     * Update only the status of a crop field
     *
     * @param id The crop field ID
     * @param status The new status
     * @return Optional containing the updated crop field if found
     */
    @Transactional
    public Optional<CropField> updateCropFieldStatus(Long id, String status) {
        return cropFieldRepository.findById(id)
                .map(cropField -> {
                    cropField.updateStatus(status);
                    return cropFieldRepository.save(cropField);
                });
    }

    /**
     * Delete a crop field
     *
     * @param id The crop field ID
     * @return true if deleted, false if not found
     */
    @Transactional
    public boolean deleteCropField(Long id) {
        if (cropFieldRepository.existsById(id)) {
            cropFieldRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
