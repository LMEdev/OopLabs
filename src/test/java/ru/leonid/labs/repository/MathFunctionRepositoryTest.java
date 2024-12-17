package ru.leonid.labs.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.leonid.labs.entity.MathFunction;
import ru.leonid.labs.entity.Point;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тесты для MathFunctionRepository")
class MathFunctionRepositoryTest {
    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @Test
    @DisplayName("Сохранение и поиск функции по типу")
    void findByFunctionType_ShouldReturnCorrectMathFunction() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("sin")
                .count(10)
                .XFrom(-1.0)
                .XTo(1.0)
                .build();

        mathFunctionRepository.save(mathFunction);

        Optional<MathFunction> result = mathFunctionRepository.findByFunctionType("sin");

        assertThat(result).isPresent();
        assertThat(result.get().getFunctionType()).isEqualTo("sin");
        assertThat(result.get().getCount()).isEqualTo(10);
        assertThat(result.get().getXFrom()).isEqualTo(-1.0);
        assertThat(result.get().getXTo()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("Обновление функции")
    void updateMathFunction_ShouldUpdateCorrectly() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("tan")
                .count(15)
                .XFrom(-3.14)
                .XTo(3.14)
                .build();

        MathFunction savedFunction = mathFunctionRepository.save(mathFunction);

        savedFunction.setCount(20);
        mathFunctionRepository.save(savedFunction);

        Optional<MathFunction> updatedFunction = mathFunctionRepository.findById(savedFunction.getId());
        assertThat(updatedFunction).isPresent();
        assertThat(updatedFunction.get().getCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("Удаление функции")
    void deleteMathFunction_ShouldDeleteCorrectly() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("log")
                .count(8)
                .XFrom(1.0)
                .XTo(10.0)
                .build();

        MathFunction savedFunction = mathFunctionRepository.save(mathFunction);

        mathFunctionRepository.delete(savedFunction);

        Optional<MathFunction> result = mathFunctionRepository.findById(savedFunction.getId());
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Удаление всех функций")
    void deleteAllFunctions_ShouldRemoveAll() {
        MathFunction function1 = MathFunction.builder()
                .functionType("exp")
                .count(10)
                .XFrom(0.0)
                .XTo(5.0)
                .build();

        MathFunction function2 = MathFunction.builder()
                .functionType("sqrt")
                .count(6)
                .XFrom(1.0)
                .XTo(25.0)
                .build();

        mathFunctionRepository.save(function1);
        mathFunctionRepository.save(function2);

        mathFunctionRepository.deleteAll();

        long count = mathFunctionRepository.count();
        assertThat(count).isEqualTo(0);
    }

    @Test
    @DisplayName("Удаление функции с каскадным удалением точек")
    void deleteMathFunction_ShouldCascadeRemovePoints() {
        MathFunction mathFunction = MathFunction.builder()
                .functionType("cos")
                .count(5)
                .XFrom(0.0)
                .XTo(3.14)
                .build();

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

        mathFunction.getPoints().add(point1);
        mathFunction.getPoints().add(point2);

        mathFunctionRepository.save(mathFunction);

        mathFunctionRepository.delete(mathFunction);

        Optional<MathFunction> result = mathFunctionRepository.findByFunctionType("cos");
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Поиск несуществующей функции")
    void findByFunctionType_ShouldReturnEmpty() {
        Optional<MathFunction> result = mathFunctionRepository.findByFunctionType("non-existent");

        assertThat(result).isEmpty();
    }

}