package ru.leonid.labs.mapper;

import org.mapstruct.Mapping;
import ru.leonid.labs.dto.PointDTO;
import org.mapstruct.Mapper;
import ru.leonid.labs.entity.MathFunction;
import ru.leonid.labs.entity.Point;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PointMapper {
    PointDTO toDTO(Point point);

    Point toEntity(PointDTO pointDTO);
}