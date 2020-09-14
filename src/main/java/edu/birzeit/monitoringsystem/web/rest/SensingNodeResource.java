package edu.birzeit.monitoringsystem.web.rest;

import edu.birzeit.monitoringsystem.service.SensingNodeService;
import edu.birzeit.monitoringsystem.web.rest.errors.BadRequestAlertException;
import edu.birzeit.monitoringsystem.service.dto.SensingNodeDTO;

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
 * REST controller for managing {@link edu.birzeit.monitoringsystem.domain.SensingNode}.
 */
@RestController
@RequestMapping("/api")
public class SensingNodeResource {

    private final Logger log = LoggerFactory.getLogger(SensingNodeResource.class);

    private static final String ENTITY_NAME = "sensingNode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SensingNodeService sensingNodeService;

    public SensingNodeResource(SensingNodeService sensingNodeService) {
        this.sensingNodeService = sensingNodeService;
    }

    /**
     * {@code POST  /sensing-nodes} : Create a new sensingNode.
     *
     * @param sensingNodeDTO the sensingNodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sensingNodeDTO, or with status {@code 400 (Bad Request)} if the sensingNode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sensing-nodes")
    public ResponseEntity<SensingNodeDTO> createSensingNode(@Valid @RequestBody SensingNodeDTO sensingNodeDTO) throws URISyntaxException {
        log.debug("REST request to save SensingNode : {}", sensingNodeDTO);
        if (sensingNodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sensingNode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SensingNodeDTO result = sensingNodeService.save(sensingNodeDTO);
        return ResponseEntity.created(new URI("/api/sensing-nodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sensing-nodes} : Updates an existing sensingNode.
     *
     * @param sensingNodeDTO the sensingNodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sensingNodeDTO,
     * or with status {@code 400 (Bad Request)} if the sensingNodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sensingNodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sensing-nodes")
    public ResponseEntity<SensingNodeDTO> updateSensingNode(@Valid @RequestBody SensingNodeDTO sensingNodeDTO) throws URISyntaxException {
        log.debug("REST request to update SensingNode : {}", sensingNodeDTO);
        if (sensingNodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SensingNodeDTO result = sensingNodeService.save(sensingNodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sensingNodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sensing-nodes} : get all the sensingNodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sensingNodes in body.
     */
    @GetMapping("/sensing-nodes")
    public List<SensingNodeDTO> getAllSensingNodes() {
        log.debug("REST request to get all SensingNodes");
        return sensingNodeService.findAll();
    }

    /**
     * {@code GET  /sensing-nodes/:id} : get the "id" sensingNode.
     *
     * @param id the id of the sensingNodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sensingNodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sensing-nodes/{id}")
    public ResponseEntity<SensingNodeDTO> getSensingNode(@PathVariable Long id) {
        log.debug("REST request to get SensingNode : {}", id);
        Optional<SensingNodeDTO> sensingNodeDTO = sensingNodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sensingNodeDTO);
    }

    /**
     * {@code DELETE  /sensing-nodes/:id} : delete the "id" sensingNode.
     *
     * @param id the id of the sensingNodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sensing-nodes/{id}")
    public ResponseEntity<Void> deleteSensingNode(@PathVariable Long id) {
        log.debug("REST request to delete SensingNode : {}", id);
        sensingNodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
