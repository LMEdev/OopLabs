package ru.leonid.labs.dto;

import lombok.Data;

@Data
public class MathFunctionDTO {
    private Long functionId;
    private String name;
    private Double xFrom;
    private Double xTo;
    private Integer count;
}

