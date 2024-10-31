package ru.leonid.labs.dto;

import lombok.Data;

@Data
public class PointDTO {
    private Long pointId;
    private Long functionId;
    private Double xValue;
    private Double yValue;
}
