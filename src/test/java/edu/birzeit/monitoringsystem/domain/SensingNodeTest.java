package edu.birzeit.monitoringsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.birzeit.monitoringsystem.web.rest.TestUtil;

public class SensingNodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SensingNode.class);
        SensingNode sensingNode1 = new SensingNode();
        sensingNode1.setId(1L);
        SensingNode sensingNode2 = new SensingNode();
        sensingNode2.setId(sensingNode1.getId());
        assertThat(sensingNode1).isEqualTo(sensingNode2);
        sensingNode2.setId(2L);
        assertThat(sensingNode1).isNotEqualTo(sensingNode2);
        sensingNode1.setId(null);
        assertThat(sensingNode1).isNotEqualTo(sensingNode2);
    }
}
