package edu.birzeit.monitoringsystem.repository;

import edu.birzeit.monitoringsystem.domain.SensingNode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SensingNode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SensingNodeRepository extends JpaRepository<SensingNode, Long> {
}
