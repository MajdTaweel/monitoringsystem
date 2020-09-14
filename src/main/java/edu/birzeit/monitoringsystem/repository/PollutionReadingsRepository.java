package edu.birzeit.monitoringsystem.repository;

import edu.birzeit.monitoringsystem.domain.PollutionReadings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PollutionReadings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollutionReadingsRepository extends JpaRepository<PollutionReadings, Long> {
}
