package edu.birzeit.monitoringsystem.web.rest;

import edu.birzeit.monitoringsystem.service.MagnetometerReadingsService;
import edu.birzeit.monitoringsystem.web.rest.errors.BadRequestAlertException;
import edu.birzeit.monitoringsystem.service.dto.MagnetometerReadingsDTO;

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
 * REST controller for managing {@link edu.birzeit.monitoringsystem.domain.MagnetometerReadings}.
 */
@RestController
@RequestMapping("/api")
public class MagnetometerReadingsResource {

    private final Logger log = LoggerFactory.getLogger(MagnetometerReadingsResource.class);

    private static final String ENTITY_NAME = "magnetometerReadings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MagnetometerReadingsService magnetometerReadingsService;

    public MagnetometerReadingsResource(MagnetometerReadingsService magnetometerReadingsService) {
        this.magnetometerReadingsService = magnetometerReadingsService;
    }

    /**
     * {@code POST  /magnetometer-readings} : Create a new magnetometerReadings.
     *
     * @param magnetometerReadingsDTO the magnetometerReadingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new magnetometerReadingsDTO, or with status {@code 400 (Bad Request)} if the magnetometerReadings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/magnetometer-readings")
    public ResponseEntity<MagnetometerReadingsDTO> createMagnetometerReadings(@Valid @RequestBody MagnetometerReadingsDTO magnetometerReadingsDTO) throws URISyntaxException {
        log.debug("REST request to save MagnetometerReadings : {}", magnetometerReadingsDTO);
        if (magnetometerReadingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new magnetometerReadings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MagnetometerReadingsDTO result = magnetometerReadingsService.save(magnetometerReadingsDTO);
        return ResponseEntity.created(new URI("/api/magnetometer-readings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /magnetometer-readings} : Updates an existing magnetometerReadings.
     *
     * @param magnetometerReadingsDTO the magnetometerReadingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated magnetometerReadingsDTO,
     * or with status {@code 400 (Bad Request)} if the magnetometerReadingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the magnetometerReadingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/magnetometer-readings")
    public ResponseEntity<MagnetometerReadingsDTO> updateMagnetometerReadings(@Valid @RequestBody MagnetometerReadingsDTO magnetometerReadingsDTO) throws URISyntaxException {
        log.debug("REST request to update MagnetometerReadings : {}", magnetometerReadingsDTO);
        if (magnetometerReadingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MagnetometerReadingsDTO result = magnetometerReadingsService.save(magnetometerReadingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, magnetometerReadingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /magnetometer-readings} : get all the magnetometerReadings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of magnetometerReadings in body.
     */
    @GetMapping("/magnetometer-readings")
    public List<MagnetometerReadingsDTO> getAllMagnetometerReadings() {
        log.debug("REST request to get all MagnetometerReadings");
        return magnetometerReadingsService.findAll();
    }

    /**
     * {@code GET  /magnetometer-readings/:id} : get the "id" magnetometerReadings.
     *
     * @param id the id of the magnetometerReadingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the magnetometerReadingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/magnetometer-readings/{id}")
    public ResponseEntity<MagnetometerReadingsDTO> getMagnetometerReadings(@PathVariable Long id) {
        log.debug("REST request to get MagnetometerReadings : {}", id);
        Optional<MagnetometerReadingsDTO> magnetometerReadingsDTO = magnetometerReadingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(magnetometerReadingsDTO);
    }

    /**
     * {@code DELETE  /magnetometer-readings/:id} : delete the "id" magnetometerReadings.
     *
     * @param id the id of the magnetometerReadingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/magnetometer-readings/{id}")
    public ResponseEntity<Void> deleteMagnetometerReadings(@PathVariable Long id) {
        log.debug("REST request to delete MagnetometerReadings : {}", id);
        magnetometerReadingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
