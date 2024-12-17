package ru.leonid.labs.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.leonid.labs.entity.MathFunction;
import ru.leonid.labs.entity.Point;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DisplayName("Тесты для PointRepository")
class PointRepositoryTest {
    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @Autowired
    private PointRepository pointRepository;

    @Test
    @DisplayName("Сохранение точки с привязкой к функции")
    void savePoint_ShouldSaveCorrectly() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("sin")
                .count(10)
                .XFrom(-1.0)
                .XTo(1.0)
                .build();
        mathFunction = mathFunctionRepository.save(mathFunction);

        Point point = Point.builder()
                .xValue(0.0)
                .yValue(1.0)
                .function(mathFunction)
                .build();

        Point savedPoint = pointRepository.save(point);

        assertThat(savedPoint.getId()).isNotNull();
        assertThat(savedPoint.getXValue()).isEqualTo(0.0);
        assertThat(savedPoint.getYValue()).isEqualTo(1.0);
        assertThat(savedPoint.getFunction().getId()).isEqualTo(mathFunction.getId());
    }

    @Test
    @DisplayName("Поиск точек по ID функции")
    void findByFunctionId_ShouldReturnPoints() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("cos")
                .count(5)
                .XFrom(0.0)
                .XTo(3.14)
                .build();
        mathFunction = mathFunctionRepository.save(mathFunction);

        Point point1 = Point.builder()
                .xValue(0.0)
                .yValue(1.0)
                .function(mathFunction)
                .build();

        Point point2 = Point.builder()
                .xValue(1.57)
                .yValue(0.0)
                .function(mathFunction)
                .build();

        pointRepository.save(point1);
        pointRepository.save(point2);

        List<Point> points = pointRepository.findByFunctionId(mathFunction.getId());

        assertThat(points).hasSize(2);
        assertThat(points).extracting(Point::getXValue).containsExactlyInAnyOrder(0.0, 1.57);
    }

    @Test
    @DisplayName("Удаление точки")
    void deletePoint_ShouldRemoveCorrectly() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("tan")
                .count(3)
                .XFrom(-3.14)
                .XTo(3.14)
                .build();
        mathFunction = mathFunctionRepository.save(mathFunction);

        Point point = Point.builder()
                .xValue(1.0)
                .yValue(1.0)
                .function(mathFunction)
                .build();

        point = pointRepository.save(point);

        pointRepository.delete(point);

        List<Point> points = pointRepository.findByFunctionId(mathFunction.getId());
        assertThat(points).isEmpty();
    }

    @Test
    @DisplayName("Удаление всех точек для функции")
    void deleteAllPoints_ShouldRemoveAllPointsForFunction() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("exp")
                .count(2)
                .XFrom(0.0)
                .XTo(10.0)
                .build();
        mathFunction = mathFunctionRepository.save(mathFunction);

        Point point1 = Point.builder()
                .xValue(0.0)
                .yValue(1.0)
                .function(mathFunction)
                .build();

        Point point2 = Point.builder()
                .xValue(2.0)
                .yValue(7.39)
                .function(mathFunction)
                .build();

        pointRepository.save(point1);
        pointRepository.save(point2);

        pointRepository.deleteAll(pointRepository.findByFunctionId(mathFunction.getId()));

        List<Point> points = pointRepository.findByFunctionId(mathFunction.getId());
        assertThat(points).isEmpty();
    }
}