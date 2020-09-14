package edu.birzeit.monitoringsystem.service.mapper;


import edu.birzeit.monitoringsystem.domain.*;
import edu.birzeit.monitoringsystem.service.dto.MagnetometerReadingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MagnetometerReadings} and its DTO {@link MagnetometerReadingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SensingNodeMapper.class})
public interface MagnetometerReadingsMapper extends EntityMapper<MagnetometerReadingsDTO, MagnetometerReadings> {

    @Mapping(source = "sensingNode.id", target = "sensingNodeId")
    MagnetometerReadingsDTO toDto(MagnetometerReadings magnetometerReadings);

    @Mapping(source = "sensingNodeId", target = "sensingNode")
    MagnetometerReadings toEntity(MagnetometerReadingsDTO magnetometerReadingsDTO);

    default MagnetometerReadings fromId(Long id) {
        if (id == null) {
            return null;
        }
        MagnetometerReadings magnetometerReadings = new MagnetometerReadings();
        magnetometerReadings.setId(id);
        return magnetometerReadings;
    }
}
