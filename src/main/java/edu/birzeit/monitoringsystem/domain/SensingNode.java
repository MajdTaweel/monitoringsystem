package edu.birzeit.monitoringsystem.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import edu.birzeit.monitoringsystem.domain.enumeration.SensingNodeType;

import edu.birzeit.monitoringsystem.domain.enumeration.SensingNodeStatus;

/**
 * A SensingNode.
 */
@Entity
@Table(name = "sensing_node")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SensingNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sensing_node_type", nullable = false)
    private SensingNodeType sensingNodeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SensingNodeStatus status;

    @Column(name = "battery_life")
    private Double batteryLife;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "sensingNode")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MagnetometerReadings> magnetometerReadings = new HashSet<>();

    @OneToMany(mappedBy = "sensingNode")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PollutionReadings> pollutionReadings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensingNodeType getSensingNodeType() {
        return sensingNodeType;
    }

    public SensingNode sensingNodeType(SensingNodeType sensingNodeType) {
        this.sensingNodeType = sensingNodeType;
        return this;
    }

    public void setSensingNodeType(SensingNodeType sensingNodeType) {
        this.sensingNodeType = sensingNodeType;
    }

    public SensingNodeStatus getStatus() {
        return status;
    }

    public SensingNode status(SensingNodeStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(SensingNodeStatus status) {
        this.status = status;
    }

    public Double getBatteryLife() {
        return batteryLife;
    }

    public SensingNode batteryLife(Double batteryLife) {
        this.batteryLife = batteryLife;
        return this;
    }

    public void setBatteryLife(Double batteryLife) {
        this.batteryLife = batteryLife;
    }

    public User getUser() {
        return user;
    }

    public SensingNode user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MagnetometerReadings> getMagnetometerReadings() {
        return magnetometerReadings;
    }

    public SensingNode magnetometerReadings(Set<MagnetometerReadings> magnetometerReadings) {
        this.magnetometerReadings = magnetometerReadings;
        return this;
    }

    public SensingNode addMagnetometerReadings(MagnetometerReadings magnetometerReadings) {
        this.magnetometerReadings.add(magnetometerReadings);
        magnetometerReadings.setSensingNode(this);
        return this;
    }

    public SensingNode removeMagnetometerReadings(MagnetometerReadings magnetometerReadings) {
        this.magnetometerReadings.remove(magnetometerReadings);
        magnetometerReadings.setSensingNode(null);
        return this;
    }

    public void setMagnetometerReadings(Set<MagnetometerReadings> magnetometerReadings) {
        this.magnetometerReadings = magnetometerReadings;
    }

    public Set<PollutionReadings> getPollutionReadings() {
        return pollutionReadings;
    }

    public SensingNode pollutionReadings(Set<PollutionReadings> pollutionReadings) {
        this.pollutionReadings = pollutionReadings;
        return this;
    }

    public SensingNode addPollutionReadings(PollutionReadings pollutionReadings) {
        this.pollutionReadings.add(pollutionReadings);
        pollutionReadings.setSensingNode(this);
        return this;
    }

    public SensingNode removePollutionReadings(PollutionReadings pollutionReadings) {
        this.pollutionReadings.remove(pollutionReadings);
        pollutionReadings.setSensingNode(null);
        return this;
    }

    public void setPollutionReadings(Set<PollutionReadings> pollutionReadings) {
        this.pollutionReadings = pollutionReadings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensingNode)) {
            return false;
        }
        return id != null && id.equals(((SensingNode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SensingNode{" +
            "id=" + getId() +
            ", sensingNodeType='" + getSensingNodeType() + "'" +
            ", status='" + getStatus() + "'" +
            ", batteryLife=" + getBatteryLife() +
            "}";
    }
}
