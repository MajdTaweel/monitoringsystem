package edu.birzeit.monitoringsystem.service.mapper;


import edu.birzeit.monitoringsystem.domain.*;
import edu.birzeit.monitoringsystem.service.dto.PollutionReadingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PollutionReadings} and its DTO {@link PollutionReadingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SensingNodeMapper.class})
public interface PollutionReadingsMapper extends EntityMapper<PollutionReadingsDTO, PollutionReadings> {

    @Mapping(source = "sensingNode.id", target = "sensingNodeId")
    PollutionReadingsDTO toDto(PollutionReadings pollutionReadings);

    @Mapping(source = "sensingNodeId", target = "sensingNode")
    PollutionReadings toEntity(PollutionReadingsDTO pollutionReadingsDTO);

    default PollutionReadings fromId(Long id) {
        if (id == null) {
            return null;
        }
        PollutionReadings pollutionReadings = new PollutionReadings();
        pollutionReadings.setId(id);
        return pollutionReadings;
    }
}
