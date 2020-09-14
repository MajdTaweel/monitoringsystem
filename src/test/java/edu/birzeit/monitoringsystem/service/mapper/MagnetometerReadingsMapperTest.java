package edu.birzeit.monitoringsystem.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MagnetometerReadingsMapperTest {

    private MagnetometerReadingsMapper magnetometerReadingsMapper;

    @BeforeEach
    public void setUp() {
        magnetometerReadingsMapper = new MagnetometerReadingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(magnetometerReadingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(magnetometerReadingsMapper.fromId(null)).isNull();
    }
}
