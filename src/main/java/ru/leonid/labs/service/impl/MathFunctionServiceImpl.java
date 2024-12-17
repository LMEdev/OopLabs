package ru.leonid.labs.service.impl;

import ru.leonid.labs.dto.MathFunctionDTO;
import lombok.RequiredArgsConstructor;
import ru.leonid.labs.entity.Point;
import ru.leonid.labs.mapper.MathFunctionMapper;
import ru.leonid.labs.mapper.PointMapper;
import org.springframework.stereotype.Service;
import ru.leonid.labs.entity.MathFunction;
import ru.leonid.labs.repository.MathFunctionRepository;
import ru.leonid.labs.service.MathFunctionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MathFunctionServiceImpl implements MathFunctionService {

    private final MathFunctionRepository repository;
    private final MathFunctionMapper mapper;
    private final PointMapper pointMapper;

    @Override
    public MathFunctionDTO createFunction(MathFunctionDTO dto){
        MathFunction entity = mapper.toEntity(dto);
        System.out.println("Received functionType: " + entity.getFunctionType());
        System.out.println("Received count: " + entity.getCount());
        System.out.println("Received xFrom: " + entity.getXFrom());
        System.out.println("Received xTo: " + entity.getXTo());
        MathFunction savedEntity = repository.save(entity);
        System.out.println("Received functionType: " + savedEntity.getFunctionType());
        System.out.println("Received count: " + savedEntity.getCount());
        System.out.println("Received xFrom: " + savedEntity.getXFrom());
        System.out.println("Received xTo: " + savedEntity.getXTo());
        return mapper.toDTO(savedEntity);
    }

    @Override
    public MathFunctionDTO getFunctionById(Long id){
        MathFunction entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Function not found"));
        return mapper.toDTO(entity);
    }

    @Override
    public List<MathFunctionDTO> getAllFunctions() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public MathFunctionDTO updateFunction(Long id, MathFunctionDTO dto) {
        MathFunction entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Function not found"));

        entity.setFunctionType(dto.getFunctionType());
        entity.setCount(dto.getCount());
        entity.setXFrom(dto.getXFrom());
        entity.setXTo(dto.getXTo());
        entity.setPoints(dto.getPoints() != null
                ? dto.getPoints().stream()
                .map(pointMapper::toEntity) // Используем внедрённый бин pointMapper
                .collect(Collectors.toList())
                : List.of());

        MathFunction savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Override
    public boolean deleteFunction(Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public MathFunctionDTO findFunctionByType(String functionType) {
        Optional<MathFunction> entity = repository.findByFunctionType(functionType);
        return entity.map(mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Function not found with type: " + functionType));
    }
}
