package edu.birzeit.monitoringsystem.service.impl;

import edu.birzeit.monitoringsystem.service.MagnetometerReadingsService;
import edu.birzeit.monitoringsystem.domain.MagnetometerReadings;
import edu.birzeit.monitoringsystem.repository.MagnetometerReadingsRepository;
import edu.birzeit.monitoringsystem.service.dto.MagnetometerReadingsDTO;
import edu.birzeit.monitoringsystem.service.mapper.MagnetometerReadingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MagnetometerReadings}.
 */
@Service
@Transactional
public class MagnetometerReadingsServiceImpl implements MagnetometerReadingsService {

    private final Logger log = LoggerFactory.getLogger(MagnetometerReadingsServiceImpl.class);

    private final MagnetometerReadingsRepository magnetometerReadingsRepository;

    private final MagnetometerReadingsMapper magnetometerReadingsMapper;

    public MagnetometerReadingsServiceImpl(MagnetometerReadingsRepository magnetometerReadingsRepository, MagnetometerReadingsMapper magnetometerReadingsMapper) {
        this.magnetometerReadingsRepository = magnetometerReadingsRepository;
        this.magnetometerReadingsMapper = magnetometerReadingsMapper;
    }

    @Override
    public MagnetometerReadingsDTO save(MagnetometerReadingsDTO magnetometerReadingsDTO) {
        log.debug("Request to save MagnetometerReadings : {}", magnetometerReadingsDTO);
        MagnetometerReadings magnetometerReadings = magnetometerReadingsMapper.toEntity(magnetometerReadingsDTO);
        magnetometerReadings = magnetometerReadingsRepository.save(magnetometerReadings);
        return magnetometerReadingsMapper.toDto(magnetometerReadings);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MagnetometerReadingsDTO> findAll() {
        log.debug("Request to get all MagnetometerReadings");
        return magnetometerReadingsRepository.findAll().stream()
            .map(magnetometerReadingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MagnetometerReadingsDTO> findOne(Long id) {
        log.debug("Request to get MagnetometerReadings : {}", id);
        return magnetometerReadingsRepository.findById(id)
            .map(magnetometerReadingsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MagnetometerReadings : {}", id);
        magnetometerReadingsRepository.deleteById(id);
    }
}
