package edu.birzeit.monitoringsystem.web.rest;

import edu.birzeit.monitoringsystem.MonitoringsystemApp;
import edu.birzeit.monitoringsystem.domain.MagnetometerReadings;
import edu.birzeit.monitoringsystem.repository.MagnetometerReadingsRepository;
import edu.birzeit.monitoringsystem.service.MagnetometerReadingsService;
import edu.birzeit.monitoringsystem.service.dto.MagnetometerReadingsDTO;
import edu.birzeit.monitoringsystem.service.mapper.MagnetometerReadingsMapper;

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
 * Integration tests for the {@link MagnetometerReadingsResource} REST controller.
 */
@SpringBootTest(classes = MonitoringsystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MagnetometerReadingsResourceIT {

    private static final Double DEFAULT_X = 1D;
    private static final Double UPDATED_X = 2D;

    private static final Double DEFAULT_Y = 1D;
    private static final Double UPDATED_Y = 2D;

    private static final Double DEFAULT_Z = 1D;
    private static final Double UPDATED_Z = 2D;

    @Autowired
    private MagnetometerReadingsRepository magnetometerReadingsRepository;

    @Autowired
    private MagnetometerReadingsMapper magnetometerReadingsMapper;

    @Autowired
    private MagnetometerReadingsService magnetometerReadingsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMagnetometerReadingsMockMvc;

    private MagnetometerReadings magnetometerReadings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MagnetometerReadings createEntity(EntityManager em) {
        MagnetometerReadings magnetometerReadings = new MagnetometerReadings()
            .x(DEFAULT_X)
            .y(DEFAULT_Y)
            .z(DEFAULT_Z);
        return magnetometerReadings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MagnetometerReadings createUpdatedEntity(EntityManager em) {
        MagnetometerReadings magnetometerReadings = new MagnetometerReadings()
            .x(UPDATED_X)
            .y(UPDATED_Y)
            .z(UPDATED_Z);
        return magnetometerReadings;
    }

    @BeforeEach
    public void initTest() {
        magnetometerReadings = createEntity(em);
    }

    @Test
    @Transactional
    public void createMagnetometerReadings() throws Exception {
        int databaseSizeBeforeCreate = magnetometerReadingsRepository.findAll().size();
        // Create the MagnetometerReadings
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(magnetometerReadings);
        restMagnetometerReadingsMockMvc.perform(post("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isCreated());

        // Validate the MagnetometerReadings in the database
        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeCreate + 1);
        MagnetometerReadings testMagnetometerReadings = magnetometerReadingsList.get(magnetometerReadingsList.size() - 1);
        assertThat(testMagnetometerReadings.getX()).isEqualTo(DEFAULT_X);
        assertThat(testMagnetometerReadings.getY()).isEqualTo(DEFAULT_Y);
        assertThat(testMagnetometerReadings.getZ()).isEqualTo(DEFAULT_Z);
    }

    @Test
    @Transactional
    public void createMagnetometerReadingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = magnetometerReadingsRepository.findAll().size();

        // Create the MagnetometerReadings with an existing ID
        magnetometerReadings.setId(1L);
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(magnetometerReadings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMagnetometerReadingsMockMvc.perform(post("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MagnetometerReadings in the database
        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkXIsRequired() throws Exception {
        int databaseSizeBeforeTest = magnetometerReadingsRepository.findAll().size();
        // set the field null
        magnetometerReadings.setX(null);

        // Create the MagnetometerReadings, which fails.
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(magnetometerReadings);


        restMagnetometerReadingsMockMvc.perform(post("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isBadRequest());

        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkYIsRequired() throws Exception {
        int databaseSizeBeforeTest = magnetometerReadingsRepository.findAll().size();
        // set the field null
        magnetometerReadings.setY(null);

        // Create the MagnetometerReadings, which fails.
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(magnetometerReadings);


        restMagnetometerReadingsMockMvc.perform(post("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isBadRequest());

        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZIsRequired() throws Exception {
        int databaseSizeBeforeTest = magnetometerReadingsRepository.findAll().size();
        // set the field null
        magnetometerReadings.setZ(null);

        // Create the MagnetometerReadings, which fails.
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(magnetometerReadings);


        restMagnetometerReadingsMockMvc.perform(post("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isBadRequest());

        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMagnetometerReadings() throws Exception {
        // Initialize the database
        magnetometerReadingsRepository.saveAndFlush(magnetometerReadings);

        // Get all the magnetometerReadingsList
        restMagnetometerReadingsMockMvc.perform(get("/api/magnetometer-readings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(magnetometerReadings.getId().intValue())))
            .andExpect(jsonPath("$.[*].x").value(hasItem(DEFAULT_X.doubleValue())))
            .andExpect(jsonPath("$.[*].y").value(hasItem(DEFAULT_Y.doubleValue())))
            .andExpect(jsonPath("$.[*].z").value(hasItem(DEFAULT_Z.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMagnetometerReadings() throws Exception {
        // Initialize the database
        magnetometerReadingsRepository.saveAndFlush(magnetometerReadings);

        // Get the magnetometerReadings
        restMagnetometerReadingsMockMvc.perform(get("/api/magnetometer-readings/{id}", magnetometerReadings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(magnetometerReadings.getId().intValue()))
            .andExpect(jsonPath("$.x").value(DEFAULT_X.doubleValue()))
            .andExpect(jsonPath("$.y").value(DEFAULT_Y.doubleValue()))
            .andExpect(jsonPath("$.z").value(DEFAULT_Z.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMagnetometerReadings() throws Exception {
        // Get the magnetometerReadings
        restMagnetometerReadingsMockMvc.perform(get("/api/magnetometer-readings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMagnetometerReadings() throws Exception {
        // Initialize the database
        magnetometerReadingsRepository.saveAndFlush(magnetometerReadings);

        int databaseSizeBeforeUpdate = magnetometerReadingsRepository.findAll().size();

        // Update the magnetometerReadings
        MagnetometerReadings updatedMagnetometerReadings = magnetometerReadingsRepository.findById(magnetometerReadings.getId()).get();
        // Disconnect from session so that the updates on updatedMagnetometerReadings are not directly saved in db
        em.detach(updatedMagnetometerReadings);
        updatedMagnetometerReadings
            .x(UPDATED_X)
            .y(UPDATED_Y)
            .z(UPDATED_Z);
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(updatedMagnetometerReadings);

        restMagnetometerReadingsMockMvc.perform(put("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isOk());

        // Validate the MagnetometerReadings in the database
        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeUpdate);
        MagnetometerReadings testMagnetometerReadings = magnetometerReadingsList.get(magnetometerReadingsList.size() - 1);
        assertThat(testMagnetometerReadings.getX()).isEqualTo(UPDATED_X);
        assertThat(testMagnetometerReadings.getY()).isEqualTo(UPDATED_Y);
        assertThat(testMagnetometerReadings.getZ()).isEqualTo(UPDATED_Z);
    }

    @Test
    @Transactional
    public void updateNonExistingMagnetometerReadings() throws Exception {
        int databaseSizeBeforeUpdate = magnetometerReadingsRepository.findAll().size();

        // Create the MagnetometerReadings
        MagnetometerReadingsDTO magnetometerReadingsDTO = magnetometerReadingsMapper.toDto(magnetometerReadings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMagnetometerReadingsMockMvc.perform(put("/api/magnetometer-readings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(magnetometerReadingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MagnetometerReadings in the database
        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMagnetometerReadings() throws Exception {
        // Initialize the database
        magnetometerReadingsRepository.saveAndFlush(magnetometerReadings);

        int databaseSizeBeforeDelete = magnetometerReadingsRepository.findAll().size();

        // Delete the magnetometerReadings
        restMagnetometerReadingsMockMvc.perform(delete("/api/magnetometer-readings/{id}", magnetometerReadings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MagnetometerReadings> magnetometerReadingsList = magnetometerReadingsRepository.findAll();
        assertThat(magnetometerReadingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
