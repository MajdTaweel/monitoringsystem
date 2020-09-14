package edu.birzeit.monitoringsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MagnetometerReadings.
 */
@Entity
@Table(name = "magnetometer_readings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MagnetometerReadings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "x", nullable = false)
    private Double x;

    @NotNull
    @Column(name = "y", nullable = false)
    private Double y;

    @NotNull
    @Column(name = "z", nullable = false)
    private Double z;

    @ManyToOne
    @JsonIgnoreProperties(value = "magnetometerReadings", allowSetters = true)
    private SensingNode sensingNode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public MagnetometerReadings x(Double x) {
        this.x = x;
        return this;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public MagnetometerReadings y(Double y) {
        this.y = y;
        return this;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public MagnetometerReadings z(Double z) {
        this.z = z;
        return this;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public SensingNode getSensingNode() {
        return sensingNode;
    }

    public MagnetometerReadings sensingNode(SensingNode sensingNode) {
        this.sensingNode = sensingNode;
        return this;
    }

    public void setSensingNode(SensingNode sensingNode) {
        this.sensingNode = sensingNode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MagnetometerReadings)) {
            return false;
        }
        return id != null && id.equals(((MagnetometerReadings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MagnetometerReadings{" +
            "id=" + getId() +
            ", x=" + getX() +
            ", y=" + getY() +
            ", z=" + getZ() +
            "}";
    }
}
