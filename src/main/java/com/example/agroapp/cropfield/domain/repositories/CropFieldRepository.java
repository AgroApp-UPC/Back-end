package com.example.agroapp.cropfield.domain.repositories;

import com.example.agroapp.cropfield.domain.model.aggregates.CropField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for CropField aggregate
 * Spring Data JPA will automatically provide the implementation
 */
@Repository
public interface CropFieldRepository extends JpaRepository<CropField, Long> {

    /**
     * Find all crop fields by title (crop name)
     *
     * @param title The crop title to search for
     * @return List of crop fields with that title
     */
    List<CropField> findByTitle(String title);

    /**
     * Find crop fields by status
     *
     * @param status The status to filter by
     * @return List of crop fields with that status
     */
    List<CropField> findByStatus(String status);

    /**
     * Find crop fields by field location
     *
     * @param field The field location
     * @return List of crop fields in that location
     */
    List<CropField> findByField(String field);

    /**
     * Find crop fields with planting date after a specific date
     *
     * @param date The date to compare
     * @return List of crop fields planted after the date
     */
    List<CropField> findByPlantingDateAfter(Date date);

    /**
     * Find crop fields with harvest date before a specific date
     *
     * @param date The date to compare
     * @return List of crop fields to be harvested before the date
     */
    List<CropField> findByHarvestDateBefore(Date date);

    /**
     * Find crop fields containing specific text in the title
     *
     * @param keyword The keyword to search for
     * @return List of crop fields matching the keyword
     */
    List<CropField> findByTitleContaining(String keyword);
}
