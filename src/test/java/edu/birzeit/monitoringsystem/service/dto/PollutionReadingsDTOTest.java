package edu.birzeit.monitoringsystem.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.birzeit.monitoringsystem.web.rest.TestUtil;

public class PollutionReadingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PollutionReadingsDTO.class);
        PollutionReadingsDTO pollutionReadingsDTO1 = new PollutionReadingsDTO();
        pollutionReadingsDTO1.setId(1L);
        PollutionReadingsDTO pollutionReadingsDTO2 = new PollutionReadingsDTO();
        assertThat(pollutionReadingsDTO1).isNotEqualTo(pollutionReadingsDTO2);
        pollutionReadingsDTO2.setId(pollutionReadingsDTO1.getId());
        assertThat(pollutionReadingsDTO1).isEqualTo(pollutionReadingsDTO2);
        pollutionReadingsDTO2.setId(2L);
        assertThat(pollutionReadingsDTO1).isNotEqualTo(pollutionReadingsDTO2);
        pollutionReadingsDTO1.setId(null);
        assertThat(pollutionReadingsDTO1).isNotEqualTo(pollutionReadingsDTO2);
    }
}
