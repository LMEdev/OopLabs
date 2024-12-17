package ru.leonid.labs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.entity.Point;
import ru.leonid.labs.mapper.PointMapper;
import ru.leonid.labs.repository.PointRepository;
import ru.leonid.labs.service.impl.PointServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Тесты для MathFunctionService")
class PointServiceTest {

    @Mock
    private PointRepository repository;

    @Mock
    private PointMapper mapper;

    @InjectMocks
    private PointServiceImpl service;

    private Point point;
    private PointDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = PointDTO.builder()
                .id(1L)
                .xValue(1.0)
                .yValue(2.0)
                .build();

        point = Point.builder()
                .id(1L)
                .xValue(1.0)
                .yValue(2.0)
                .build();
    }

    @Test
    @DisplayName("Создание точки")
    void createPoint() {
        when(mapper.toEntity(dto)).thenReturn(point);
        when(repository.save(point)).thenReturn(point);
        when(mapper.toDTO(point)).thenReturn(dto);

        PointDTO result = service.createPoint(dto);

        assertThat(result).isEqualTo(dto);
        verify(repository).save(point);
    }

    @Test
    @DisplayName("Получение точки по ID")
    void getPointById() {
        when(repository.findById(1L)).thenReturn(Optional.of(point));
        when(mapper.toDTO(point)).thenReturn(dto);

        PointDTO result = service.getPointById(1L);

        assertThat(result).isEqualTo(dto);
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Получение всех точек")
    void getAllPoints() {
        when(repository.findAll()).thenReturn(List.of(point));
        when(mapper.toDTO(point)).thenReturn(dto);

        List<PointDTO> result = service.getAllPoints();

        assertThat(result).containsExactly(dto);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Обновление точки")
    void updatePoint() {
        when(repository.findById(1L)).thenReturn(Optional.of(point));
        when(repository.save(point)).thenReturn(point);
        when(mapper.toDTO(point)).thenReturn(dto);

        PointDTO result = service.updatePoint(1L, dto);

        assertThat(result).isEqualTo(dto);
        verify(repository).findById(1L);
        verify(repository).save(point);
    }

    @Test
    @DisplayName("Удаление точки - успешное выполнение")
    void deletePoint_Success() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean result = service.deletePoint(1L);

        assertThat(result).isTrue();
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Удаление точки - несуществующий ID")
    void deletePoint_Failure() {
        when(repository.existsById(1L)).thenReturn(false);

        boolean result = service.deletePoint(1L);

        assertThat(result).isFalse();
        verify(repository, never()).deleteById(1L);
    }
}