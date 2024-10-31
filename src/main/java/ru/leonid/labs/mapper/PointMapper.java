package ru.leonid.labs.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.entity.PointEntity;

@Mapper(componentModel = "spring")
public interface PointMapper {
    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    @Mapping(source = "functionId", target = "function.functionId")
    PointEntity toEntity(PointDTO dto);

    @Mapping(source = "function.functionId", target = "functionId")
    PointDTO toDto(PointEntity entity);
}
