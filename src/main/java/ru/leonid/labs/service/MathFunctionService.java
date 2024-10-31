package ru.leonid.labs.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.entity.MathFunctionEntity;
import ru.leonid.labs.mapper.MathFunctionMapper;
import ru.leonid.labs.repository.MathFunctionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MathFunctionService {

    private final MathFunctionRepository repository;
    private final MathFunctionMapper mathFunctionMapper;

    public MathFunctionService(MathFunctionRepository repository, MathFunctionMapper mathFunctionMapper) {
        this.repository = repository;
        this.mathFunctionMapper = mathFunctionMapper;
    }

    public MathFunctionDTO create(MathFunctionDTO dto) {
        MathFunctionEntity entity = mathFunctionMapper.toEntity(dto);
        MathFunctionEntity savedEntity = repository.save(entity);
        return mathFunctionMapper.toDto(savedEntity);
    }

    public MathFunctionDTO read(Long id) {
        MathFunctionEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Function not found"));
        return mathFunctionMapper.toDto(entity);
    }

    public MathFunctionDTO update(MathFunctionDTO dto) {
        MathFunctionEntity entity = mathFunctionMapper.toEntity(dto);
        MathFunctionEntity updatedEntity = repository.save(entity);
        return mathFunctionMapper.toDto(updatedEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<MathFunctionDTO> findFunctionsByName(String functionName) {
        List<MathFunctionEntity> entities = repository.findByName(functionName);
        return entities.stream()
                .map(mathFunctionMapper::toDto)
                .collect(Collectors.toList());
    }
}