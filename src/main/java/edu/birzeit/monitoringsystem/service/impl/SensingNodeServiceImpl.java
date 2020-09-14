package edu.birzeit.monitoringsystem.service.impl;

import edu.birzeit.monitoringsystem.service.SensingNodeService;
import edu.birzeit.monitoringsystem.domain.SensingNode;
import edu.birzeit.monitoringsystem.repository.SensingNodeRepository;
import edu.birzeit.monitoringsystem.service.dto.SensingNodeDTO;
import edu.birzeit.monitoringsystem.service.mapper.SensingNodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SensingNode}.
 */
@Service
@Transactional
public class SensingNodeServiceImpl implements SensingNodeService {

    private final Logger log = LoggerFactory.getLogger(SensingNodeServiceImpl.class);

    private final SensingNodeRepository sensingNodeRepository;

    private final SensingNodeMapper sensingNodeMapper;

    public SensingNodeServiceImpl(SensingNodeRepository sensingNodeRepository, SensingNodeMapper sensingNodeMapper) {
        this.sensingNodeRepository = sensingNodeRepository;
        this.sensingNodeMapper = sensingNodeMapper;
    }

    @Override
    public SensingNodeDTO save(SensingNodeDTO sensingNodeDTO) {
        log.debug("Request to save SensingNode : {}", sensingNodeDTO);
        SensingNode sensingNode = sensingNodeMapper.toEntity(sensingNodeDTO);
        sensingNode = sensingNodeRepository.save(sensingNode);
        return sensingNodeMapper.toDto(sensingNode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensingNodeDTO> findAll() {
        log.debug("Request to get all SensingNodes");
        return sensingNodeRepository.findAll().stream()
            .map(sensingNodeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SensingNodeDTO> findOne(Long id) {
        log.debug("Request to get SensingNode : {}", id);
        return sensingNodeRepository.findById(id)
            .map(sensingNodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SensingNode : {}", id);
        sensingNodeRepository.deleteById(id);
    }
}
