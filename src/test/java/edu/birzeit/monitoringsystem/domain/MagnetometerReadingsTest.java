package edu.birzeit.monitoringsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.birzeit.monitoringsystem.web.rest.TestUtil;

public class MagnetometerReadingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MagnetometerReadings.class);
        MagnetometerReadings magnetometerReadings1 = new MagnetometerReadings();
        magnetometerReadings1.setId(1L);
        MagnetometerReadings magnetometerReadings2 = new MagnetometerReadings();
        magnetometerReadings2.setId(magnetometerReadings1.getId());
        assertThat(magnetometerReadings1).isEqualTo(magnetometerReadings2);
        magnetometerReadings2.setId(2L);
        assertThat(magnetometerReadings1).isNotEqualTo(magnetometerReadings2);
        magnetometerReadings1.setId(null);
        assertThat(magnetometerReadings1).isNotEqualTo(magnetometerReadings2);
    }
}
