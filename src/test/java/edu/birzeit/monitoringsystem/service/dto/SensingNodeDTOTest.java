package edu.birzeit.monitoringsystem.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.birzeit.monitoringsystem.web.rest.TestUtil;

public class SensingNodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SensingNodeDTO.class);
        SensingNodeDTO sensingNodeDTO1 = new SensingNodeDTO();
        sensingNodeDTO1.setId(1L);
        SensingNodeDTO sensingNodeDTO2 = new SensingNodeDTO();
        assertThat(sensingNodeDTO1).isNotEqualTo(sensingNodeDTO2);
        sensingNodeDTO2.setId(sensingNodeDTO1.getId());
        assertThat(sensingNodeDTO1).isEqualTo(sensingNodeDTO2);
        sensingNodeDTO2.setId(2L);
        assertThat(sensingNodeDTO1).isNotEqualTo(sensingNodeDTO2);
        sensingNodeDTO1.setId(null);
        assertThat(sensingNodeDTO1).isNotEqualTo(sensingNodeDTO2);
    }
}
