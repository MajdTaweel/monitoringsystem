package edu.birzeit.monitoringsystem.service;

import edu.birzeit.monitoringsystem.service.dto.SensingNodeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link edu.birzeit.monitoringsystem.domain.SensingNode}.
 */
public interface SensingNodeService {

    /**
     * Save a sensingNode.
     *
     * @param sensingNodeDTO the entity to save.
     * @return the persisted entity.
     */
    SensingNodeDTO save(SensingNodeDTO sensingNodeDTO);

    /**
     * Get all the sensingNodes.
     *
     * @return the list of entities.
     */
    List<SensingNodeDTO> findAll();


    /**
     * Get the "id" sensingNode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SensingNodeDTO> findOne(Long id);

    /**
     * Delete the "id" sensingNode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
