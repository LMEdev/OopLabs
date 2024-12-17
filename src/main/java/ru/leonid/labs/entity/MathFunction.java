package ru.leonid.labs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "math_function")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "points")
public class MathFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "function_type", nullable = false)
    private String functionType;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "x_from", nullable = false)
    private Double XFrom;

    @Column(name = "x_to", nullable = false)
    private Double XTo;

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Point> points = new ArrayList<>();
}
