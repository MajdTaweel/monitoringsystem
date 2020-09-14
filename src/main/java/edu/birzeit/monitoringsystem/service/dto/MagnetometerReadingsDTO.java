package edu.birzeit.monitoringsystem.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link edu.birzeit.monitoringsystem.domain.MagnetometerReadings} entity.
 */
public class MagnetometerReadingsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Double z;


    private Long sensingNodeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
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
        if (!(o instanceof MagnetometerReadingsDTO)) {
            return false;
        }

        return id != null && id.equals(((MagnetometerReadingsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MagnetometerReadingsDTO{" +
            "id=" + getId() +
            ", x=" + getX() +
            ", y=" + getY() +
            ", z=" + getZ() +
            ", sensingNodeId=" + getSensingNodeId() +
            "}";
    }
}
