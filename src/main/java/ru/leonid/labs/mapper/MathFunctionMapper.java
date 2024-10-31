package ru.leonid.labs.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.entity.MathFunctionEntity;

@Mapper(componentModel = "spring")
public interface MathFunctionMapper {


    MathFunctionEntity toEntity(MathFunctionDTO dto);


    MathFunctionDTO toDto(MathFunctionEntity entity);
}