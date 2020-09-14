package edu.birzeit.monitoringsystem.repository;

import edu.birzeit.monitoringsystem.domain.MagnetometerReadings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MagnetometerReadings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagnetometerReadingsRepository extends JpaRepository<MagnetometerReadings, Long> {
}
