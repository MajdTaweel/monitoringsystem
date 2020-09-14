package edu.birzeit.monitoringsystem.service;

import edu.birzeit.monitoringsystem.service.dto.PollutionReadingsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link edu.birzeit.monitoringsystem.domain.PollutionReadings}.
 */
public interface PollutionReadingsService {

    /**
     * Save a pollutionReadings.
     *
     * @param pollutionReadingsDTO the entity to save.
     * @return the persisted entity.
     */
    PollutionReadingsDTO save(PollutionReadingsDTO pollutionReadingsDTO);

    /**
     * Get all the pollutionReadings.
     *
     * @return the list of entities.
     */
    List<PollutionReadingsDTO> findAll();


    /**
     * Get the "id" pollutionReadings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PollutionReadingsDTO> findOne(Long id);

    /**
     * Delete the "id" pollutionReadings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
