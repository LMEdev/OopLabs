package ru.leonid.labs.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.labs.controller.MathFunctionController;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.service.MathFunctionService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MathFunctionControllerImpl implements MathFunctionController {

    private final MathFunctionService mathFunctionService;

    @Override
    public ResponseEntity<MathFunctionDTO> createFunction(@RequestBody MathFunctionDTO dto) {
        MathFunctionDTO createdFunction = mathFunctionService.createFunction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFunction);
    }


    @Override
    public ResponseEntity<MathFunctionDTO> getFunctionById(Long id) {
        MathFunctionDTO function = mathFunctionService.getFunctionById(id);
        if (function == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(function);
    }

    @Override
    public ResponseEntity<List<MathFunctionDTO>> getAllFunctions() {
        List<MathFunctionDTO> functions = mathFunctionService.getAllFunctions();
        return ResponseEntity.ok(functions);
    }

    @Override
    public ResponseEntity<MathFunctionDTO> updateFunction(Long id, MathFunctionDTO dto) {
        MathFunctionDTO updatedFunction = mathFunctionService.updateFunction(id, dto);
        if (updatedFunction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedFunction);
    }

    @Override
    public ResponseEntity<Void> deleteFunction(Long id) {
        boolean deleted = mathFunctionService.deleteFunction(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}