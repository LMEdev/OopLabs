package ru.leonid.labs.lab_2.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для ConstantFunction")
class ConstantFunctionTest {

    @Test
    void testApplyConstantFunction() {
        MathFunction constFunc = new ConstantFunction(5.0);
        assertEquals(5.0, constFunc.apply(10), 0.001);
        assertEquals(5.0, constFunc.apply(-10), 0.001);
    }

    @Test
    void testApplyZeroFunction() {
        MathFunction zeroFunc = new ZeroFunction();
        assertEquals(0.0, zeroFunc.apply(10), 0.001);
        assertEquals(0.0, zeroFunc.apply(-10), 0.001);
    }

    @Test
    void testApplyUnitFunction() {
        MathFunction unitFunc = new UnitFunction();
        assertEquals(1.0, unitFunc.apply(10), 0.001);
        assertEquals(1.0, unitFunc.apply(-10), 0.001);
    }
}
