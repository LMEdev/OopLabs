package ru.leonid.labs.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@Entity
@Table(name = "mathematical_functions")
@NoArgsConstructor
@AllArgsConstructor
public class MathFunctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "function_id", nullable = false, updatable = false)
    private Long functionId;

    @Column(name = "function_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "x_from", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private Double xFrom;

    @Column(name = "x_to", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private Double xTo;

    @Column(name = "point_count", nullable = false, columnDefinition = "INTEGER")
    private Integer count;

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PointEntity> points;
}