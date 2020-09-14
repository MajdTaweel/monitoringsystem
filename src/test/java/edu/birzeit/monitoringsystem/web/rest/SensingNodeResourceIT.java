package edu.birzeit.monitoringsystem.web.rest;

import edu.birzeit.monitoringsystem.MonitoringsystemApp;
import edu.birzeit.monitoringsystem.domain.SensingNode;
import edu.birzeit.monitoringsystem.repository.SensingNodeRepository;
import edu.birzeit.monitoringsystem.service.SensingNodeService;
import edu.birzeit.monitoringsystem.service.dto.SensingNodeDTO;
import edu.birzeit.monitoringsystem.service.mapper.SensingNodeMapper;

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

import edu.birzeit.monitoringsystem.domain.enumeration.SensingNodeType;
import edu.birzeit.monitoringsystem.domain.enumeration.SensingNodeStatus;
/**
 * Integration tests for the {@link SensingNodeResource} REST controller.
 */
@SpringBootTest(classes = MonitoringsystemApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SensingNodeResourceIT {

    private static final String DEFAULT_SNID = "AAAAAAAAAA";
    private static final String UPDATED_SNID = "BBBBBBBBBB";

    private static final SensingNodeType DEFAULT_SENSING_NODE_TYPE = SensingNodeType.Magnetometer;
    private static final SensingNodeType UPDATED_SENSING_NODE_TYPE = SensingNodeType.Pollution;

    private static final SensingNodeStatus DEFAULT_STATUS = SensingNodeStatus.Online;
    private static final SensingNodeStatus UPDATED_STATUS = SensingNodeStatus.WaitingForConfig;

    private static final Double DEFAULT_BATTERY = 1D;
    private static final Double UPDATED_BATTERY = 2D;

    @Autowired
    private SensingNodeRepository sensingNodeRepository;

    @Autowired
    private SensingNodeMapper sensingNodeMapper;

    @Autowired
    private SensingNodeService sensingNodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSensingNodeMockMvc;

    private SensingNode sensingNode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SensingNode createEntity(EntityManager em) {
        SensingNode sensingNode = new SensingNode()
            .snid(DEFAULT_SNID)
            .sensingNodeType(DEFAULT_SENSING_NODE_TYPE)
            .status(DEFAULT_STATUS)
            .battery(DEFAULT_BATTERY);
        return sensingNode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SensingNode createUpdatedEntity(EntityManager em) {
        SensingNode sensingNode = new SensingNode()
            .snid(UPDATED_SNID)
            .sensingNodeType(UPDATED_SENSING_NODE_TYPE)
            .status(UPDATED_STATUS)
            .battery(UPDATED_BATTERY);
        return sensingNode;
    }

    @BeforeEach
    public void initTest() {
        sensingNode = createEntity(em);
    }

    @Test
    @Transactional
    public void createSensingNode() throws Exception {
        int databaseSizeBeforeCreate = sensingNodeRepository.findAll().size();
        // Create the SensingNode
        SensingNodeDTO sensingNodeDTO = sensingNodeMapper.toDto(sensingNode);
        restSensingNodeMockMvc.perform(post("/api/sensing-nodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensingNodeDTO)))
            .andExpect(status().isCreated());

        // Validate the SensingNode in the database
        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeCreate + 1);
        SensingNode testSensingNode = sensingNodeList.get(sensingNodeList.size() - 1);
        assertThat(testSensingNode.getSnid()).isEqualTo(DEFAULT_SNID);
        assertThat(testSensingNode.getSensingNodeType()).isEqualTo(DEFAULT_SENSING_NODE_TYPE);
        assertThat(testSensingNode.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSensingNode.getBattery()).isEqualTo(DEFAULT_BATTERY);
    }

    @Test
    @Transactional
    public void createSensingNodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sensingNodeRepository.findAll().size();

        // Create the SensingNode with an existing ID
        sensingNode.setId(1L);
        SensingNodeDTO sensingNodeDTO = sensingNodeMapper.toDto(sensingNode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSensingNodeMockMvc.perform(post("/api/sensing-nodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensingNodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SensingNode in the database
        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSnidIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensingNodeRepository.findAll().size();
        // set the field null
        sensingNode.setSnid(null);

        // Create the SensingNode, which fails.
        SensingNodeDTO sensingNodeDTO = sensingNodeMapper.toDto(sensingNode);


        restSensingNodeMockMvc.perform(post("/api/sensing-nodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensingNodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSensingNodeTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensingNodeRepository.findAll().size();
        // set the field null
        sensingNode.setSensingNodeType(null);

        // Create the SensingNode, which fails.
        SensingNodeDTO sensingNodeDTO = sensingNodeMapper.toDto(sensingNode);


        restSensingNodeMockMvc.perform(post("/api/sensing-nodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensingNodeDTO)))
            .andExpect(status().isBadRequest());

        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSensingNodes() throws Exception {
        // Initialize the database
        sensingNodeRepository.saveAndFlush(sensingNode);

        // Get all the sensingNodeList
        restSensingNodeMockMvc.perform(get("/api/sensing-nodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensingNode.getId().intValue())))
            .andExpect(jsonPath("$.[*].snid").value(hasItem(DEFAULT_SNID)))
            .andExpect(jsonPath("$.[*].sensingNodeType").value(hasItem(DEFAULT_SENSING_NODE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].battery").value(hasItem(DEFAULT_BATTERY.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getSensingNode() throws Exception {
        // Initialize the database
        sensingNodeRepository.saveAndFlush(sensingNode);

        // Get the sensingNode
        restSensingNodeMockMvc.perform(get("/api/sensing-nodes/{id}", sensingNode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sensingNode.getId().intValue()))
            .andExpect(jsonPath("$.snid").value(DEFAULT_SNID))
            .andExpect(jsonPath("$.sensingNodeType").value(DEFAULT_SENSING_NODE_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.battery").value(DEFAULT_BATTERY.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSensingNode() throws Exception {
        // Get the sensingNode
        restSensingNodeMockMvc.perform(get("/api/sensing-nodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSensingNode() throws Exception {
        // Initialize the database
        sensingNodeRepository.saveAndFlush(sensingNode);

        int databaseSizeBeforeUpdate = sensingNodeRepository.findAll().size();

        // Update the sensingNode
        SensingNode updatedSensingNode = sensingNodeRepository.findById(sensingNode.getId()).get();
        // Disconnect from session so that the updates on updatedSensingNode are not directly saved in db
        em.detach(updatedSensingNode);
        updatedSensingNode
            .snid(UPDATED_SNID)
            .sensingNodeType(UPDATED_SENSING_NODE_TYPE)
            .status(UPDATED_STATUS)
            .battery(UPDATED_BATTERY);
        SensingNodeDTO sensingNodeDTO = sensingNodeMapper.toDto(updatedSensingNode);

        restSensingNodeMockMvc.perform(put("/api/sensing-nodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensingNodeDTO)))
            .andExpect(status().isOk());

        // Validate the SensingNode in the database
        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeUpdate);
        SensingNode testSensingNode = sensingNodeList.get(sensingNodeList.size() - 1);
        assertThat(testSensingNode.getSnid()).isEqualTo(UPDATED_SNID);
        assertThat(testSensingNode.getSensingNodeType()).isEqualTo(UPDATED_SENSING_NODE_TYPE);
        assertThat(testSensingNode.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSensingNode.getBattery()).isEqualTo(UPDATED_BATTERY);
    }

    @Test
    @Transactional
    public void updateNonExistingSensingNode() throws Exception {
        int databaseSizeBeforeUpdate = sensingNodeRepository.findAll().size();

        // Create the SensingNode
        SensingNodeDTO sensingNodeDTO = sensingNodeMapper.toDto(sensingNode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSensingNodeMockMvc.perform(put("/api/sensing-nodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sensingNodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SensingNode in the database
        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSensingNode() throws Exception {
        // Initialize the database
        sensingNodeRepository.saveAndFlush(sensingNode);

        int databaseSizeBeforeDelete = sensingNodeRepository.findAll().size();

        // Delete the sensingNode
        restSensingNodeMockMvc.perform(delete("/api/sensing-nodes/{id}", sensingNode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SensingNode> sensingNodeList = sensingNodeRepository.findAll();
        assertThat(sensingNodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
