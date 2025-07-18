package edu.birzeit.monitoringsystem.web.rest;

import edu.birzeit.monitoringsystem.service.PollutionReadingsService;
import edu.birzeit.monitoringsystem.web.rest.errors.BadRequestAlertException;
import edu.birzeit.monitoringsystem.service.dto.PollutionReadingsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link edu.birzeit.monitoringsystem.domain.PollutionReadings}.
 */
@RestController
@RequestMapping("/api")
public class PollutionReadingsResource {

    private final Logger log = LoggerFactory.getLogger(PollutionReadingsResource.class);

    private static final String ENTITY_NAME = "pollutionReadings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PollutionReadingsService pollutionReadingsService;

    public PollutionReadingsResource(PollutionReadingsService pollutionReadingsService) {
        this.pollutionReadingsService = pollutionReadingsService;
    }

    /**
     * {@code POST  /pollution-readings} : Create a new pollutionReadings.
     *
     * @param pollutionReadingsDTO the pollutionReadingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pollutionReadingsDTO, or with status {@code 400 (Bad Request)} if the pollutionReadings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pollution-readings")
    public ResponseEntity<PollutionReadingsDTO> createPollutionReadings(@Valid @RequestBody PollutionReadingsDTO pollutionReadingsDTO) throws URISyntaxException {
        log.debug("REST request to save PollutionReadings : {}", pollutionReadingsDTO);
        if (pollutionReadingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new pollutionReadings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PollutionReadingsDTO result = pollutionReadingsService.save(pollutionReadingsDTO);
        return ResponseEntity.created(new URI("/api/pollution-readings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pollution-readings} : Updates an existing pollutionReadings.
     *
     * @param pollutionReadingsDTO the pollutionReadingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pollutionReadingsDTO,
     * or with status {@code 400 (Bad Request)} if the pollutionReadingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pollutionReadingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pollution-readings")
    public ResponseEntity<PollutionReadingsDTO> updatePollutionReadings(@Valid @RequestBody PollutionReadingsDTO pollutionReadingsDTO) throws URISyntaxException {
        log.debug("REST request to update PollutionReadings : {}", pollutionReadingsDTO);
        if (pollutionReadingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PollutionReadingsDTO result = pollutionReadingsService.save(pollutionReadingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pollutionReadingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pollution-readings} : get all the pollutionReadings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pollutionReadings in body.
     */
    @GetMapping("/pollution-readings")
    public List<PollutionReadingsDTO> getAllPollutionReadings() {
        log.debug("REST request to get all PollutionReadings");
        return pollutionReadingsService.findAll();
    }

    /**
     * {@code GET  /pollution-readings/:id} : get the "id" pollutionReadings.
     *
     * @param id the id of the pollutionReadingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pollutionReadingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pollution-readings/{id}")
    public ResponseEntity<PollutionReadingsDTO> getPollutionReadings(@PathVariable Long id) {
        log.debug("REST request to get PollutionReadings : {}", id);
        Optional<PollutionReadingsDTO> pollutionReadingsDTO = pollutionReadingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pollutionReadingsDTO);
    }

    /**
     * {@code DELETE  /pollution-readings/:id} : delete the "id" pollutionReadings.
     *
     * @param id the id of the pollutionReadingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pollution-readings/{id}")
    public ResponseEntity<Void> deletePollutionReadings(@PathVariable Long id) {
        log.debug("REST request to delete PollutionReadings : {}", id);
        pollutionReadingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
