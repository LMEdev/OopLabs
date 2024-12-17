package ru.leonid.labs.service;

import ru.leonid.labs.dto.MathFunctionDTO;

import java.util.List;

public interface MathFunctionService {

    MathFunctionDTO createFunction(MathFunctionDTO dto);

    MathFunctionDTO getFunctionById(Long id);

    List<MathFunctionDTO> getAllFunctions();

    MathFunctionDTO updateFunction(Long id, MathFunctionDTO dto);

    boolean deleteFunction(Long id);

    MathFunctionDTO findFunctionByType(String functionType);
}
