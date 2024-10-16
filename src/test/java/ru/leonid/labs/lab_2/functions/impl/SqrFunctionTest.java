package ru.leonid.labs.lab_2.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест для SqrFunciton")
class SqrFunctionTest {

    @Test
    void testApply() {
        MathFunction sqrFunction = new SqrFunction();
        double input = 3.0;
        double expected = Math.pow(input, 2);
        double result = sqrFunction.apply(input);
        assertEquals(expected, result, 0.0001, "The apply method should return the square of the input value.");
    }
}