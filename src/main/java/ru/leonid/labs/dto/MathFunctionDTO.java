package ru.leonid.labs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MathFunctionDTO {

    private Long id;
    private String functionType;
    private Integer count;
    @JsonProperty("XFrom")
    private Double XFrom;
    @JsonProperty("XTo")
    private Double XTo;
    private List<PointDTO> points;
}