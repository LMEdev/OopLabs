package ru.leonid.labs.service.impl;

import ru.leonid.labs.dto.PointDTO;
import lombok.RequiredArgsConstructor;
import ru.leonid.labs.mapper.PointMapper;
import org.springframework.stereotype.Service;
import ru.leonid.labs.entity.Point;
import ru.leonid.labs.repository.PointRepository;
import ru.leonid.labs.service.PointService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository repository;
    private final PointMapper mapper;

    @Override
    public PointDTO createPoint(PointDTO dto) {
        Point point = mapper.toEntity(dto);
        Point savedPoint = repository.save(point);
        return mapper.toDTO(savedPoint);
    }

    @Override
    public PointDTO getPointById(Long id) {
        Optional<Point> point = repository.findById(id);
        return point.map(mapper::toDTO).orElse(null);
    }

    @Override
    public List<PointDTO> getAllPoints() {
        List<Point> points = repository.findAll();
        return points.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PointDTO updatePoint(Long id, PointDTO pointDTO) {
        Optional<Point> existingPoint = repository.findById(id);
        if (existingPoint.isPresent()) {
            Point point = existingPoint.get();
            point.setXValue(pointDTO.getXValue());
            point.setYValue(pointDTO.getYValue());
            Point updatedPoint = repository.save(point);
            return mapper.toDTO(updatedPoint);
        }
        return null;
    }

    @Override
    public boolean deletePoint(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
