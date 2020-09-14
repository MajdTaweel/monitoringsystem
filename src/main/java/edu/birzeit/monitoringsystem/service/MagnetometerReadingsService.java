package edu.birzeit.monitoringsystem.service;

import edu.birzeit.monitoringsystem.service.dto.MagnetometerReadingsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link edu.birzeit.monitoringsystem.domain.MagnetometerReadings}.
 */
public interface MagnetometerReadingsService {

    /**
     * Save a magnetometerReadings.
     *
     * @param magnetometerReadingsDTO the entity to save.
     * @return the persisted entity.
     */
    MagnetometerReadingsDTO save(MagnetometerReadingsDTO magnetometerReadingsDTO);

    /**
     * Get all the magnetometerReadings.
     *
     * @return the list of entities.
     */
    List<MagnetometerReadingsDTO> findAll();


    /**
     * Get the "id" magnetometerReadings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MagnetometerReadingsDTO> findOne(Long id);

    /**
     * Delete the "id" magnetometerReadings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
