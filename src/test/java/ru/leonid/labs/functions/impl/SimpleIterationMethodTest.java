package ru.leonid.labs.lab_2.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.functions.api.MathFunction;
import ru.leonid.labs.functions.impl.SimpleIterationMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты для SimpleIterationMethod")
class SimpleIterationMethodTest {

    @Test
    void shouldFindRootForSimpleEquation() {
        MathFunction equation = x -> (x + 2) / 3;
        SimpleIterationMethod solver = new SimpleIterationMethod(new MathFunction[]{equation}, 1e-9, 100);

        double result = solver.apply(0);

        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void shouldSolveSystemOfEquations() {
        MathFunction eq1 = x -> (x + 2) / 3;
        MathFunction eq2 = x -> (x + 1) / 2;

        SimpleIterationMethod solver = new SimpleIterationMethod(new MathFunction[]{eq1, eq2}, 1e-9, 100);

        double result = solver.apply(0);

        assertTrue(Math.abs(result - 1.0) < 1e-9 || Math.abs(result - 1.5) < 1e-9);
    }

    @Test
    void shouldThrowExceptionWhenNoConvergence() {
        MathFunction equation = x -> 2 * x;
        SimpleIterationMethod solver = new SimpleIterationMethod(new MathFunction[]{equation}, 1e-9, 100);

        assertThrows(ArithmeticException.class, () -> solver.apply(1.0));
    }

    @Test
    void shouldHandleIdentityEquation() {
        MathFunction equation = x -> x;
        SimpleIterationMethod solver = new SimpleIterationMethod(new MathFunction[]{equation}, 1e-9, 100);

        double result = solver.apply(1.0);

        assertEquals(1.0, result, 1e-9);
    }
}