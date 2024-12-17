package ru.leonid.labs.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.function.api.MathFunction;
import ru.leonid.labs.function.impl.EulerMethod;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест для EulerMethod")
class EulerMethodTest {

    @Test
    @DisplayName("Тест: Проверка метода Эйлера для простого дифференциального уравнения")
    void testEulerMethodSimpleEquation() {
        BinaryOperator<Double> differentialEquation = (x, y) -> x + y;
        double y0 = 1.0;
        double x0 = 0.0;
        double stepSize = 0.1;

        MathFunction eulerMethod = new EulerMethod(differentialEquation, y0, x0, stepSize);
        double xTarget = 2.0;

        double result = eulerMethod.apply(xTarget);
        assertEquals(10.45, result, 0.01);
    }
}