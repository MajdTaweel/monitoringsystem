package edu.birzeit.monitoringsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PollutionReadings.
 */
@Entity
@Table(name = "pollution_readings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PollutionReadings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "co_2", nullable = false)
    private Double co2;

    @NotNull
    @Column(name = "sound", nullable = false)
    private Double sound;

    @ManyToOne
    @JsonIgnoreProperties(value = "pollutionReadings", allowSetters = true)
    private SensingNode sensingNode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCo2() {
        return co2;
    }

    public PollutionReadings co2(Double co2) {
        this.co2 = co2;
        return this;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getSound() {
        return sound;
    }

    public PollutionReadings sound(Double sound) {
        this.sound = sound;
        return this;
    }

    public void setSound(Double sound) {
        this.sound = sound;
    }

    public SensingNode getSensingNode() {
        return sensingNode;
    }

    public PollutionReadings sensingNode(SensingNode sensingNode) {
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
        if (!(o instanceof PollutionReadings)) {
            return false;
        }
        return id != null && id.equals(((PollutionReadings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PollutionReadings{" +
            "id=" + getId() +
            ", co2=" + getCo2() +
            ", sound=" + getSound() +
            "}";
    }
}
