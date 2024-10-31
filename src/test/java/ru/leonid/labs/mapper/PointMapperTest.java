package ru.leonid.labs.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.entity.MathFunctionEntity;
import ru.leonid.labs.entity.PointEntity;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для мапперов")
class PointMapperTest {

    private final MathFunctionMapper mathFunctionMapper = Mappers.getMapper(MathFunctionMapper.class);
    private final PointMapper pointMapper = Mappers.getMapper(PointMapper.class);

    @Test
    @DisplayName("Проверка маппинга MathFunctionDTO в MathFunctionEntity")
    void testMathFunctionDtoToEntityMapping() {
        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setFunctionId(1L);
        dto.setName("Test Function");
        dto.setXFrom(-5.0);
        dto.setXTo(5.0);
        dto.setCount(10);

        MathFunctionEntity entity = mathFunctionMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getFunctionId(), entity.getFunctionId());
        assertEquals(dto.getName(), entity.getName());
    }


    @Test
    @DisplayName("Проверка маппинга MathFunctionEntity в MathFunctionDTO")
    void testMathFunctionEntityToDtoMapping() {
        MathFunctionEntity entity = new MathFunctionEntity();
        entity.setFunctionId(1L);
        entity.setName("Test Function");
        entity.setXFrom(-5.0);
        entity.setXTo(5.0);
        entity.setCount(10);

        MathFunctionDTO dto = mathFunctionMapper.toDto(entity);

        assertEquals(entity.getFunctionId(), dto.getFunctionId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getXFrom(), dto.getXFrom());
        assertEquals(entity.getXTo(), dto.getXTo());
        assertEquals(entity.getCount(), dto.getCount());
    }

    @Test
    @DisplayName("Проверка маппинга PointDTO в PointEntity")
    void testPointDtoToEntityMapping() {
        PointDTO dto = new PointDTO();
        dto.setPointId(1L);
        dto.setFunctionId(1L);
        dto.setXValue(3.0);
        dto.setYValue(4.0);

        PointEntity entity = pointMapper.toEntity(dto);

        assertEquals(dto.getPointId(), entity.getPointId());
        assertEquals(dto.getFunctionId(), entity.getFunction().getFunctionId());
        assertEquals(dto.getXValue(), entity.getXValue());
        assertEquals(dto.getYValue(), entity.getYValue());
    }

    @Test
    @DisplayName("Проверка маппинга PointEntity в PointDTO")
    void testPointEntityToDtoMapping() {
        PointEntity entity = new PointEntity();
        entity.setPointId(1L);
        MathFunctionEntity functionEntity = new MathFunctionEntity();
        functionEntity.setFunctionId(1L);
        entity.setFunction(functionEntity);
        entity.setXValue(3.0);
        entity.setYValue(4.0);

        PointDTO dto = pointMapper.toDto(entity);

        assertEquals(entity.getPointId(), dto.getPointId());
        assertEquals(entity.getFunction().getFunctionId(), dto.getFunctionId());
        assertEquals(entity.getXValue(), dto.getXValue());
        assertEquals(entity.getYValue(), dto.getYValue());
    }
}
