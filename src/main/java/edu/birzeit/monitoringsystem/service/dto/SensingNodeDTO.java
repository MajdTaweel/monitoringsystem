package edu.birzeit.monitoringsystem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import edu.birzeit.monitoringsystem.domain.enumeration.SensingNodeType;
import edu.birzeit.monitoringsystem.domain.enumeration.SensingNodeStatus;

/**
 * A DTO for the {@link edu.birzeit.monitoringsystem.domain.SensingNode} entity.
 */
public class SensingNodeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private SensingNodeType sensingNodeType;

    private SensingNodeStatus status;

    private Double batteryLife;


    private Long userId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensingNodeType getSensingNodeType() {
        return sensingNodeType;
    }

    public void setSensingNodeType(SensingNodeType sensingNodeType) {
        this.sensingNodeType = sensingNodeType;
    }

    public SensingNodeStatus getStatus() {
        return status;
    }

    public void setStatus(SensingNodeStatus status) {
        this.status = status;
    }

    public Double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(Double batteryLife) {
        this.batteryLife = batteryLife;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensingNodeDTO)) {
            return false;
        }

        return id != null && id.equals(((SensingNodeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SensingNodeDTO{" +
            "id=" + getId() +
            ", sensingNodeType='" + getSensingNodeType() + "'" +
            ", status='" + getStatus() + "'" +
            ", batteryLife=" + getBatteryLife() +
            ", userId=" + getUserId() +
            "}";
    }
}
