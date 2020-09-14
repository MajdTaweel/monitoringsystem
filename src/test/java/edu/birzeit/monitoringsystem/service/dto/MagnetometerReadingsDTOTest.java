package edu.birzeit.monitoringsystem.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.birzeit.monitoringsystem.web.rest.TestUtil;

public class MagnetometerReadingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MagnetometerReadingsDTO.class);
        MagnetometerReadingsDTO magnetometerReadingsDTO1 = new MagnetometerReadingsDTO();
        magnetometerReadingsDTO1.setId(1L);
        MagnetometerReadingsDTO magnetometerReadingsDTO2 = new MagnetometerReadingsDTO();
        assertThat(magnetometerReadingsDTO1).isNotEqualTo(magnetometerReadingsDTO2);
        magnetometerReadingsDTO2.setId(magnetometerReadingsDTO1.getId());
        assertThat(magnetometerReadingsDTO1).isEqualTo(magnetometerReadingsDTO2);
        magnetometerReadingsDTO2.setId(2L);
        assertThat(magnetometerReadingsDTO1).isNotEqualTo(magnetometerReadingsDTO2);
        magnetometerReadingsDTO1.setId(null);
        assertThat(magnetometerReadingsDTO1).isNotEqualTo(magnetometerReadingsDTO2);
    }
}
