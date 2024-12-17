package ru.leonid.labs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leonid.labs.dto.MathFunctionDTO;

import java.util.List;

@RequestMapping("/api/math-functions")
public interface MathFunctionController {

    @Operation(summary = "Создать новую математическую функцию",
            description = "Создаёт новую математическую функцию и возвращает созданный объект.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Функция успешно создана"),
    })
    @PostMapping
    ResponseEntity<MathFunctionDTO> createFunction(@RequestBody MathFunctionDTO dto);

    @Operation(summary = "Получить математическую функцию по ID",
            description = "Возвращает математическую функцию на основе переданного идентификатора.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Функция найдена"),
            @ApiResponse(responseCode = "404", description = "Функция с указанным ID не найдена")
    })
    @GetMapping("/{id}")
    ResponseEntity<MathFunctionDTO> getFunctionById(@PathVariable Long id);

    @Operation(summary = "Получить все математические функции",
            description = "Возвращает список всех существующих математических функций.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список математических функций успешно получен")
    })
    @GetMapping
    ResponseEntity<List<MathFunctionDTO>> getAllFunctions();

    @Operation(summary = "Обновить математическую функцию",
            description = "Обновляет математическую функцию по переданному ID и возвращает обновлённый объект.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Функция успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Функция с указанным ID не найдена")
    })
    @PutMapping("/{id}")
    ResponseEntity<MathFunctionDTO> updateFunction(@PathVariable Long id, @RequestBody MathFunctionDTO dto);

    @Operation(summary = "Удалить математическую функцию",
            description = "Удаляет математическую функцию по переданному ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Функция успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Функция с указанным ID не найдена")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFunction(@PathVariable Long id);
}