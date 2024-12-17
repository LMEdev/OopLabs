package ru.leonid.labs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leonid.labs.dto.PointDTO;

import java.util.List;

@RequestMapping("/api/points")
public interface PointController {

    @Operation(summary = "Создать новую точку",
            description = "Создает новую точку на основе переданных координат и возвращает созданный объект.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Точка успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PostMapping
    ResponseEntity<PointDTO> createPoint(@RequestBody PointDTO pointDTO);

    @Operation(summary = "Получить точку по ID",
            description = "Возвращает точку на основе переданного идентификатора.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Точка успешно найдена"),
            @ApiResponse(responseCode = "404", description = "Точка с указанным ID не найдена")
    })
    @GetMapping("/{id}")
    ResponseEntity<PointDTO> getPointById(@PathVariable Long id);

    @Operation(summary = "Получить список всех точек",
            description = "Возвращает список всех существующих точек в базе данных.")
    @ApiResponse(responseCode = "200", description = "Список точек успешно получен")
    @GetMapping
    ResponseEntity<List<PointDTO>> getAllPoints();

    @Operation(summary = "Обновить точку",
            description = "Обновляет существующую точку на основе переданного идентификатора и новых данных.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Точка успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Точка с указанным ID не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    })
    @PutMapping("/{id}")
    ResponseEntity<PointDTO> updatePoint(@PathVariable Long id, @RequestBody PointDTO pointDTO);

    @Operation(summary = "Удалить точку",
            description = "Удаляет точку на основе переданного идентификатора.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Точка успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Точка с указанным ID не найдена")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePoint(@PathVariable Long id);
}