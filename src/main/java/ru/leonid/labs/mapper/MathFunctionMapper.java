package ru.leonid.labs.mapper;

import org.mapstruct.*;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.entity.MathFunction;


@Mapper(componentModel = "spring")
public interface MathFunctionMapper {

    @Mapping(target = "points")
    MathFunction toEntity(MathFunctionDTO dto);

    MathFunctionDTO toDTO(MathFunction entity);
}
