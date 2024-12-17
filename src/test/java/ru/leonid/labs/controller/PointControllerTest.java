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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.leonid.labs.controller.impl.PointControllerImpl;
import ru.leonid.labs.dto.PointDTO;
import ru.leonid.labs.service.PointService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для PointControllerTest")
class PointControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PointService pointService;

    @InjectMocks
    private PointControllerImpl controller;

    private PointDTO pointDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        pointDTO = new PointDTO(1L, 10.0, 20.0, 1L);
    }

    @Test
    void givenValidPoint_whenCreatePoint_thenReturnCreated() throws Exception {
        when(pointService.createPoint(pointDTO)).thenReturn(pointDTO);

        mockMvc.perform(post("/api/points")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"xvalue\": 10.0, \"yvalue\": 20.0, \"functionId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.xvalue").value(10.0))
                .andExpect(jsonPath("$.yvalue").value(20.0))
                .andExpect(jsonPath("$.functionId").value(1));
    }

    @Test
    void givenInvalidPoint_whenCreatePoint_thenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/points")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": null, \"xValue\": null, \"yValue\": 20.0, \"functionId\": 1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenPointExists_whenGetPointById_thenReturnOk() throws Exception {
        when(pointService.getPointById(1L)).thenReturn(pointDTO);

        MvcResult result = mockMvc.perform(get("/api/points/1"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

        mockMvc.perform(get("/api/points/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.xvalue").value(10.0))
                .andExpect(jsonPath("$.yvalue").value(20.0))
                .andExpect(jsonPath("$.functionId").value(1));
    }

    @Test
    void givenPointNotFound_whenGetPointById_thenReturnNotFound() throws Exception {
        when(pointService.getPointById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/points/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenPointsExist_whenGetAllPoints_thenReturnOk() throws Exception {
        when(pointService.getAllPoints()).thenReturn(List.of(pointDTO));

        mockMvc.perform(get("/api/points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].xvalue").value(10.0))
                .andExpect(jsonPath("$[0].yvalue").value(20.0))
                .andExpect(jsonPath("$[0].functionId").value(1));
    }

    @Test
    void givenValidPoint_whenUpdatePoint_thenReturnOk() throws Exception {
        PointDTO updatedPoint = new PointDTO(1L, 15.0, 25.0, 2L);
        when(pointService.updatePoint(1L, updatedPoint)).thenReturn(updatedPoint);

        mockMvc.perform(put("/api/points/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"xvalue\": 15.0, \"yvalue\": 25.0, \"functionId\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.xvalue").value(15.0))
                .andExpect(jsonPath("$.yvalue").value(25.0))
                .andExpect(jsonPath("$.functionId").value(2));
    }

    @Test
    void givenPointNotFound_whenUpdatePoint_thenReturnNotFound() throws Exception {
        PointDTO updatedPoint = new PointDTO(1L, 15.0, 25.0, 2L);
        when(pointService.updatePoint(99L, updatedPoint)).thenReturn(null);

        mockMvc.perform(put("/api/points/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"xvalue\": 15.0, \"yvalue\": 25.0, \"functionId\": 2}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenPointExists_whenDeletePoint_thenReturnNoContent() throws Exception {
        when(pointService.deletePoint(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/points/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void givenPointNotFound_whenDeletePoint_thenReturnNotFound() throws Exception {
        when(pointService.deletePoint(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/points/99"))
                .andExpect(status().isNotFound());
    }
}