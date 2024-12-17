package ru.leonid.labs.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.leonid.labs.controller.impl.MathFunctionControllerImpl;
import ru.leonid.labs.dto.MathFunctionDTO;
import ru.leonid.labs.service.MathFunctionService;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для MathFunctionController")
class MathFunctionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MathFunctionService mathFunctionService;

    @InjectMocks
    private MathFunctionControllerImpl controller;

    private MathFunctionDTO functionDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        functionDTO = MathFunctionDTO.builder()
                .functionType("sin")
                .count(2)
                .XFrom(-10.0)
                .XTo(10.0)
                .points(List.of())
                .build();
    }

    @Test
    void givenValidFunction_whenCreateFunction_thenReturnCreated() throws Exception {
        when(mathFunctionService.createFunction(functionDTO)).thenReturn(functionDTO);

        mockMvc.perform(post("/api/math-functions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"functionType\": \"sin\", \"count\": 2, \"XFrom\": -10.0, \"XTo\": 10.0, \"points\": []}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.functionType").value("sin"))
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.XFrom").value(-10.0))
                .andExpect(jsonPath("$.XTo").value(10.0));
    }

    @Test
    void givenFunctionsExist_whenGetAllFunctions_thenReturnOk() throws Exception {
        when(mathFunctionService.getAllFunctions()).thenReturn(List.of(functionDTO));

        mockMvc.perform(get("/api/math-functions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].functionType").value("sin"))
                .andExpect(jsonPath("$[0].count").value(2))
                .andExpect(jsonPath("$[0].XFrom").value(-10.0))
                .andExpect(jsonPath("$[0].XTo").value(10.0));
    }

    @Test
    void givenFunctionExists_whenGetFunctionById_thenReturnOk() throws Exception {
        when(mathFunctionService.getFunctionById(1L)).thenReturn(functionDTO);

        mockMvc.perform(get("/api/math-functions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.functionType").value("sin"))
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.XFrom").value(-10.0))
                .andExpect(jsonPath("$.XTo").value(10.0));
    }

    @Test
    void givenValidFunction_whenUpdateFunction_thenReturnOk() throws Exception {
        MathFunctionDTO updatedFunction = MathFunctionDTO.builder()
                .functionType("sin")
                .count(3)
                .XFrom(-20.0)
                .XTo(20.0)
                .points(List.of())
                .build();
        when(mathFunctionService.updateFunction(1L, updatedFunction)).thenReturn(updatedFunction);

        mockMvc.perform(put("/api/math-functions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"functionType\": \"sin\", \"count\": 3, \"XFrom\": -20.0, \"XTo\": 20.0, \"points\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.functionType").value("sin"))
                .andExpect(jsonPath("$.count").value(3))
                .andExpect(jsonPath("$.XFrom").value(-20.0))
                .andExpect(jsonPath("$.XTo").value(20.0));
    }

    @Test
    void givenFunctionExists_whenDeleteFunction_thenReturnNoContent() throws Exception {
        when(mathFunctionService.deleteFunction(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/math-functions/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void givenFunctionNotFound_whenDeleteFunction_thenReturnNotFound() throws Exception {
        when(mathFunctionService.deleteFunction(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/math-functions/99"))
                .andExpect(status().isNotFound());
    }
}