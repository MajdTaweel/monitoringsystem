package edu.birzeit.monitoringsystem.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SensingNodeMapperTest {

    private SensingNodeMapper sensingNodeMapper;

    @BeforeEach
    public void setUp() {
        sensingNodeMapper = new SensingNodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sensingNodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sensingNodeMapper.fromId(null)).isNull();
    }
}
