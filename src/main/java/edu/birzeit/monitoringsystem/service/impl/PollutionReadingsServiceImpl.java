package edu.birzeit.monitoringsystem.service.impl;

import edu.birzeit.monitoringsystem.service.PollutionReadingsService;
import edu.birzeit.monitoringsystem.domain.PollutionReadings;
import edu.birzeit.monitoringsystem.repository.PollutionReadingsRepository;
import edu.birzeit.monitoringsystem.service.dto.PollutionReadingsDTO;
import edu.birzeit.monitoringsystem.service.mapper.PollutionReadingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PollutionReadings}.
 */
@Service
@Transactional
public class PollutionReadingsServiceImpl implements PollutionReadingsService {

    private final Logger log = LoggerFactory.getLogger(PollutionReadingsServiceImpl.class);

    private final PollutionReadingsRepository pollutionReadingsRepository;

    private final PollutionReadingsMapper pollutionReadingsMapper;

    public PollutionReadingsServiceImpl(PollutionReadingsRepository pollutionReadingsRepository, PollutionReadingsMapper pollutionReadingsMapper) {
        this.pollutionReadingsRepository = pollutionReadingsRepository;
        this.pollutionReadingsMapper = pollutionReadingsMapper;
    }

    @Override
    public PollutionReadingsDTO save(PollutionReadingsDTO pollutionReadingsDTO) {
        log.debug("Request to save PollutionReadings : {}", pollutionReadingsDTO);
        PollutionReadings pollutionReadings = pollutionReadingsMapper.toEntity(pollutionReadingsDTO);
        pollutionReadings = pollutionReadingsRepository.save(pollutionReadings);
        return pollutionReadingsMapper.toDto(pollutionReadings);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PollutionReadingsDTO> findAll() {
        log.debug("Request to get all PollutionReadings");
        return pollutionReadingsRepository.findAll().stream()
            .map(pollutionReadingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PollutionReadingsDTO> findOne(Long id) {
        log.debug("Request to get PollutionReadings : {}", id);
        return pollutionReadingsRepository.findById(id)
            .map(pollutionReadingsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PollutionReadings : {}", id);
        pollutionReadingsRepository.deleteById(id);
    }
}
