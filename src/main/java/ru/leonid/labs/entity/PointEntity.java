package ru.leonid.labs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "function_points")
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id", nullable = false, updatable = false)
    private Long pointId;

    @ManyToOne
    @JoinColumn(name = "math_function_id", nullable = false)
    private MathFunctionEntity function;

    @Column(name = "x_value", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private Double xValue;

    @Column(name = "y_value", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private Double yValue;
}

