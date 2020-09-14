package edu.birzeit.monitoringsystem.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PollutionReadingsMapperTest {

    private PollutionReadingsMapper pollutionReadingsMapper;

    @BeforeEach
    public void setUp() {
        pollutionReadingsMapper = new PollutionReadingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(pollutionReadingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pollutionReadingsMapper.fromId(null)).isNull();
    }
}
