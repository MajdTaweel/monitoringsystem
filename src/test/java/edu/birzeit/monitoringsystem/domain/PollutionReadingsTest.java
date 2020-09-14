package edu.birzeit.monitoringsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.birzeit.monitoringsystem.web.rest.TestUtil;

public class PollutionReadingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PollutionReadings.class);
        PollutionReadings pollutionReadings1 = new PollutionReadings();
        pollutionReadings1.setId(1L);
        PollutionReadings pollutionReadings2 = new PollutionReadings();
        pollutionReadings2.setId(pollutionReadings1.getId());
        assertThat(pollutionReadings1).isEqualTo(pollutionReadings2);
        pollutionReadings2.setId(2L);
        assertThat(pollutionReadings1).isNotEqualTo(pollutionReadings2);
        pollutionReadings1.setId(null);
        assertThat(pollutionReadings1).isNotEqualTo(pollutionReadings2);
    }
}
