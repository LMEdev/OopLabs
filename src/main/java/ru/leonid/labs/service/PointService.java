package ru.leonid.labs.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.entity.PointEntity;
import ru.leonid.labs.mapper.PointMapper;
import ru.leonid.labs.repository.PointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointService {

    private final PointRepository pointRepository;
    private final PointMapper pointMapper;

    public PointService(PointRepository pointRepository, PointMapper pointMapper) {
        this.pointRepository = pointRepository;
        this.pointMapper = pointMapper;
    }

    public PointDTO create(PointDTO dto) {
        PointEntity entity = pointMapper.toEntity(dto);
        PointEntity savedEntity = pointRepository.save(entity);
        return pointMapper.toDto(savedEntity);
    }

    public PointDTO read(Long id) {
        PointEntity entity = pointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Point not found"));
        return pointMapper.toDto(entity);
    }

    public PointDTO update(PointDTO dto) {
        PointEntity entity = pointMapper.toEntity(dto);
        PointEntity updatedEntity = pointRepository.save(entity);
        return pointMapper.toDto(updatedEntity);
    }

    public void delete(Long id) {
        pointRepository.deleteById(id);
    }

    public List<PointDTO> getAllPoints() {
        List<PointEntity> entities = (List<PointEntity>) pointRepository.findAll();
        return entities.stream()
                .map(pointMapper::toDto)
                .collect(Collectors.toList());
    }
}
