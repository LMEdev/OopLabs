package ru.leonid.labs.lab_2.functions;

import org.junit.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;
import ru.leonid.labs.lab_2.functions.impl.SqrFunction;

import static org.junit.Assert.assertEquals;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        MathFunction sqrFunction = new SqrFunction();
        double input = 3.0;
        double expected = Math.pow(input, 2);
        double result = sqrFunction.apply(input);
        assertEquals("The apply method should return the square of the input value.", expected, result, 0.0001);
    }
}