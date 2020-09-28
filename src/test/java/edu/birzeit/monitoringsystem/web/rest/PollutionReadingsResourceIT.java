package edu.birzeit.monitoringsystem.web.rest;

import edu.birzeit.monitoringsystem.MonitoringsystemApp;
import edu.birzeit.monitoringsystem.domain.PollutionReadings;
import edu.birzeit.monitoringsystem.repository.PollutionReadingsRepository;
import edu.birzeit.monitoringsystem.service.PollutionReadingsService;
import edu.birzeit.monitoringsystem.service.dto.PollutionReadingsDTO;
import edu.birzeit.monitoringsystem.service.mapper.PollutionReadingsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PollutionReadingsResource} REST controller.
 */
@SpringBootTest(classes = MonitoringsystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PollutionReadingsResourceIT {

    private static final Double DEFAULT_CO_2 = 1D;
    private static final Double UPDATED_CO_2 = 2D;

    private static final Double DEFAULT_SOUND = 1D;
    private static final Double UPDATED_SOUND = 2D;

    @Autowired
    private PollutionReadingsRepository pollutionReadingsRepository;

    @Autowired
    private PollutionReadingsMapper pollutionReadingsMapper;

    @Autowired
    private PollutionReadingsService pollutionReadingsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPollutionReadingsMockMvc;

    private PollutionReadings pollutionReadings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PollutionReadings createEntity(EntityManager em) {
        PollutionReadings pollutionReadings = new PollutionReadings()
            .co2(DEFAULT_CO_2)
            .sound(DEFAULT_SOUND);
        return pollutionReadings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PollutionReadings createUpdatedEntity(EntityManager em) {
        PollutionReadings pollutionReadings = new PollutionReadings()
            .co2(UPDATED_CO_2)
            .sound(UPDATED_SOUND);
        return pollutionReadings;
    }

    @BeforeEach
    public void initTest() {
        pollutionReadings = createEntity(em);
    }

    @Test
    @Transactional
    public void createPollutionReadings() throws Exception {
        int databaseSizeBeforeCreate = pollutionReadingsRepository.findAll().size();
        // Create the PollutionReadings
        PollutionReadingsDTO pollutionReadingsDTO = pollutionReadingsMapper.toDto(pollutionReadings);
        restPollutionReadingsMockMvc.perform(post("/api/pollution-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pollutionReadingsDTO)))
            .andExpect(status().isCreated());

        // Validate the PollutionReadings in the database
        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeCreate + 1);
        PollutionReadings testPollutionReadings = pollutionReadingsList.get(pollutionReadingsList.size() - 1);
        assertThat(testPollutionReadings.getCo2()).isEqualTo(DEFAULT_CO_2);
        assertThat(testPollutionReadings.getSound()).isEqualTo(DEFAULT_SOUND);
    }

    @Test
    @Transactional
    public void createPollutionReadingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pollutionReadingsRepository.findAll().size();

        // Create the PollutionReadings with an existing ID
        pollutionReadings.setId(1L);
        PollutionReadingsDTO pollutionReadingsDTO = pollutionReadingsMapper.toDto(pollutionReadings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPollutionReadingsMockMvc.perform(post("/api/pollution-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pollutionReadingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PollutionReadings in the database
        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCo2IsRequired() throws Exception {
        int databaseSizeBeforeTest = pollutionReadingsRepository.findAll().size();
        // set the field null
        pollutionReadings.setCo2(null);

        // Create the PollutionReadings, which fails.
        PollutionReadingsDTO pollutionReadingsDTO = pollutionReadingsMapper.toDto(pollutionReadings);


        restPollutionReadingsMockMvc.perform(post("/api/pollution-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pollutionReadingsDTO)))
            .andExpect(status().isBadRequest());

        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSoundIsRequired() throws Exception {
        int databaseSizeBeforeTest = pollutionReadingsRepository.findAll().size();
        // set the field null
        pollutionReadings.setSound(null);

        // Create the PollutionReadings, which fails.
        PollutionReadingsDTO pollutionReadingsDTO = pollutionReadingsMapper.toDto(pollutionReadings);


        restPollutionReadingsMockMvc.perform(post("/api/pollution-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pollutionReadingsDTO)))
            .andExpect(status().isBadRequest());

        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPollutionReadings() throws Exception {
        // Initialize the database
        pollutionReadingsRepository.saveAndFlush(pollutionReadings);

        // Get all the pollutionReadingsList
        restPollutionReadingsMockMvc.perform(get("/api/pollution-readings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pollutionReadings.getId().intValue())))
            .andExpect(jsonPath("$.[*].co2").value(hasItem(DEFAULT_CO_2.doubleValue())))
            .andExpect(jsonPath("$.[*].sound").value(hasItem(DEFAULT_SOUND.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPollutionReadings() throws Exception {
        // Initialize the database
        pollutionReadingsRepository.saveAndFlush(pollutionReadings);

        // Get the pollutionReadings
        restPollutionReadingsMockMvc.perform(get("/api/pollution-readings/{id}", pollutionReadings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pollutionReadings.getId().intValue()))
            .andExpect(jsonPath("$.co2").value(DEFAULT_CO_2.doubleValue()))
            .andExpect(jsonPath("$.sound").value(DEFAULT_SOUND.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPollutionReadings() throws Exception {
        // Get the pollutionReadings
        restPollutionReadingsMockMvc.perform(get("/api/pollution-readings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePollutionReadings() throws Exception {
        // Initialize the database
        pollutionReadingsRepository.saveAndFlush(pollutionReadings);

        int databaseSizeBeforeUpdate = pollutionReadingsRepository.findAll().size();

        // Update the pollutionReadings
        PollutionReadings updatedPollutionReadings = pollutionReadingsRepository.findById(pollutionReadings.getId()).get();
        // Disconnect from session so that the updates on updatedPollutionReadings are not directly saved in db
        em.detach(updatedPollutionReadings);
        updatedPollutionReadings
            .co2(UPDATED_CO_2)
            .sound(UPDATED_SOUND);
        PollutionReadingsDTO pollutionReadingsDTO = pollutionReadingsMapper.toDto(updatedPollutionReadings);

        restPollutionReadingsMockMvc.perform(put("/api/pollution-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pollutionReadingsDTO)))
            .andExpect(status().isOk());

        // Validate the PollutionReadings in the database
        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeUpdate);
        PollutionReadings testPollutionReadings = pollutionReadingsList.get(pollutionReadingsList.size() - 1);
        assertThat(testPollutionReadings.getCo2()).isEqualTo(UPDATED_CO_2);
        assertThat(testPollutionReadings.getSound()).isEqualTo(UPDATED_SOUND);
    }

    @Test
    @Transactional
    public void updateNonExistingPollutionReadings() throws Exception {
        int databaseSizeBeforeUpdate = pollutionReadingsRepository.findAll().size();

        // Create the PollutionReadings
        PollutionReadingsDTO pollutionReadingsDTO = pollutionReadingsMapper.toDto(pollutionReadings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPollutionReadingsMockMvc.perform(put("/api/pollution-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pollutionReadingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PollutionReadings in the database
        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePollutionReadings() throws Exception {
        // Initialize the database
        pollutionReadingsRepository.saveAndFlush(pollutionReadings);

        int databaseSizeBeforeDelete = pollutionReadingsRepository.findAll().size();

        // Delete the pollutionReadings
        restPollutionReadingsMockMvc.perform(delete("/api/pollution-readings/{id}", pollutionReadings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PollutionReadings> pollutionReadingsList = pollutionReadingsRepository.findAll();
        assertThat(pollutionReadingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
