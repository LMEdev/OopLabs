package ru.leonid.labs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.entity.MathFunction;
import ru.leonid.labs.entity.Point;
import ru.leonid.labs.mapper.MathFunctionMapper;
import ru.leonid.labs.mapper.PointMapper;
import ru.leonid.labs.repository.MathFunctionRepository;
import ru.leonid.labs.service.impl.MathFunctionServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


@DisplayName("Тесты для MathFunctionService")
class MathFunctionServiceTest {

    @Mock
    private MathFunctionRepository repository;

    @Mock
    private MathFunctionMapper mapper;

    @Mock
    private PointMapper pointMapper;

    @InjectMocks
    private MathFunctionServiceImpl service;

    private MathFunctionDTO dto;
    private MathFunction entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = MathFunctionDTO.builder()
                .id(1L)
                .functionType("sin")
                .count(10)
                .XFrom(-1.0)
                .XTo(1.0)
                .points(List.of())
                .build();

        entity = MathFunction.builder()
                .id(1L)
                .functionType("sin")
                .count(10)
                .XFrom(-1.0)
                .XTo(1.0)
                .points(List.of())
                .build();
    }

    @Test
    @DisplayName("Создание новой функции")
    void createFunction_ShouldReturnCreatedDto() {
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(dto);

        MathFunctionDTO result = service.createFunction(dto);

        assertThat(result).isEqualTo(dto);
        verify(repository).save(entity);
        verify(mapper).toEntity(dto);
        verify(mapper).toDTO(entity);
    }

    @Test
    @DisplayName("Получение функции по ID")
    void getFunctionById_ShouldReturnDto_WhenFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        MathFunctionDTO result = service.getFunctionById(1L);

        assertThat(result).isEqualTo(dto);
        verify(repository).findById(1L);
        verify(mapper).toDTO(entity);
    }

    @Test
    @DisplayName("Получение функции по ID - функция не найдена")
    void getFunctionById_ShouldThrowException_WhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getFunctionById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Function not found");
    }

    @Test
    @DisplayName("Обновление функции")
    void updateFunction_ShouldUpdateAndReturnDto() {
        MathFunctionDTO updatedDto = MathFunctionDTO.builder()
                .id(1L)
                .functionType("cos")
                .count(20)
                .XFrom(0.0)
                .XTo(3.14)
                .points(List.of(
                        PointDTO.builder()
                                .id(100L)
                                .xValue(1.0)
                                .yValue(2.0)
                                .build()
                ))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(updatedDto);
        when(pointMapper.toEntity(any(PointDTO.class))).thenAnswer(invocation -> {
            PointDTO dto = invocation.getArgument(0);
            return Point.builder()
                    .id(dto.getId())
                    .xValue(dto.getXValue())
                    .yValue(dto.getYValue())
                    .build();
        });

        MathFunctionDTO result = service.updateFunction(1L, updatedDto);

        assertThat(result).isEqualTo(updatedDto);
        verify(repository).findById(1L);
        verify(repository).save(entity);
        verify(mapper).toDTO(entity);
    }

    @Test
    @DisplayName("Удаление функции")
    void deleteFunction_ShouldDeleteEntity() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteFunction(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Поиск функции по типу")
    void findFunctionByType_ShouldReturnDto_WhenFound() {
        when(repository.findByFunctionType("sin")).thenReturn(Optional.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        MathFunctionDTO result = service.findFunctionByType("sin");

        assertThat(result).isEqualTo(dto);
        verify(repository).findByFunctionType("sin");
        verify(mapper).toDTO(entity);
    }

    @Test
    @DisplayName("Поиск функции по типу - функция не найдена")
    void findFunctionByType_ShouldThrowException_WhenNotFound() {
        when(repository.findByFunctionType("sin")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findFunctionByType("sin"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Function not found with type: sin");
    }

    @Test
    @DisplayName("Получение всех математических функций")
    void getAllFunctions_ShouldReturnListOfFunctions() {
        MathFunction entity2 = MathFunction.builder()
                .id(2L)
                .functionType("cos")
                .count(15)
                .XFrom(0.0)
                .XTo(3.14)
                .points(List.of())
                .build();

        MathFunctionDTO dto2 = MathFunctionDTO.builder()
                .id(2L)
                .functionType("cos")
                .count(15)
                .XFrom(0.0)
                .XTo(3.14)
                .points(List.of())
                .build();

        List<MathFunction> entities = List.of(entity, entity2);
        List<MathFunctionDTO> dtos = List.of(dto, dto2);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDTO(entity)).thenReturn(dto);
        when(mapper.toDTO(entity2)).thenReturn(dto2);

        List<MathFunctionDTO> result = service.getAllFunctions();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(dto);
        assertThat(result.get(1)).isEqualTo(dto2);

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDTO(entity);
        verify(mapper, times(1)).toDTO(entity2);
    }
}