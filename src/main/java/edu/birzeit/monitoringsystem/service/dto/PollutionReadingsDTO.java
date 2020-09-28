package edu.birzeit.monitoringsystem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link edu.birzeit.monitoringsystem.domain.PollutionReadings} entity.
 */
public class PollutionReadingsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Double co2;

    @NotNull
    private Double sound;


    private Long sensingNodeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getSound() {
        return sound;
    }

    public void setSound(Double sound) {
        this.sound = sound;
    }

    public Long getSensingNodeId() {
        return sensingNodeId;
    }

    public void setSensingNodeId(Long sensingNodeId) {
        this.sensingNodeId = sensingNodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PollutionReadingsDTO)) {
            return false;
        }

        return id != null && id.equals(((PollutionReadingsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PollutionReadingsDTO{" +
            "id=" + getId() +
            ", co2=" + getCo2() +
            ", sound=" + getSound() +
            ", sensingNodeId=" + getSensingNodeId() +
            "}";
    }
}
