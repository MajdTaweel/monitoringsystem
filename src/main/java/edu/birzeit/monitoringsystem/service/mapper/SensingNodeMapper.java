package edu.birzeit.monitoringsystem.service.mapper;


import edu.birzeit.monitoringsystem.domain.*;
import edu.birzeit.monitoringsystem.service.dto.SensingNodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SensingNode} and its DTO {@link SensingNodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface SensingNodeMapper extends EntityMapper<SensingNodeDTO, SensingNode> {

    @Mapping(source = "user.id", target = "userId")
    SensingNodeDTO toDto(SensingNode sensingNode);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "magnetometerReadings", ignore = true)
    @Mapping(target = "removeMagnetometerReadings", ignore = true)
    @Mapping(target = "pollutionReadings", ignore = true)
    @Mapping(target = "removePollutionReadings", ignore = true)
    SensingNode toEntity(SensingNodeDTO sensingNodeDTO);

    default SensingNode fromId(Long id) {
        if (id == null) {
            return null;
        }
        SensingNode sensingNode = new SensingNode();
        sensingNode.setId(id);
        return sensingNode;
    }
}
