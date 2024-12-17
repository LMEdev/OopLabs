package ru.leonid.labs.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.leonid.labs.controller.PointController;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.service.PointService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PointControllerImpl implements PointController {

    private final PointService pointService;

    @Override
    public ResponseEntity<PointDTO> createPoint(@Valid @RequestBody PointDTO pointDTO) {
        if (pointDTO.getXValue() == null || pointDTO.getYValue() == null) {
            return ResponseEntity.badRequest().body(null);  // Проверка на null значения
        }

        PointDTO createdPoint = pointService.createPoint(pointDTO);
        return ResponseEntity.ok(createdPoint);
    }

    @Override
    public ResponseEntity<PointDTO> getPointById(Long id) {
        PointDTO point = pointService.getPointById(id);
        if (point != null) {
            return ResponseEntity.ok(point);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<PointDTO>> getAllPoints() {
        List<PointDTO> points = pointService.getAllPoints();
        return ResponseEntity.ok(points);
    }

    @Override
    public ResponseEntity<PointDTO> updatePoint(Long id, PointDTO pointDTO) {
        PointDTO updatedPoint = pointService.updatePoint(id, pointDTO);
        if (updatedPoint != null) {
            return ResponseEntity.ok(updatedPoint);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deletePoint(Long id) {
        boolean deleted = pointService.deletePoint(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
