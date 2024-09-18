package ru.leonid.labs.lab_2.functions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        MathFunction identity = new IdentityFunction();
        double input = 5.0;
        double result = identity.apply(input);
        assertEquals("The apply method should return the input value.", input, result, 0.0001);
    }
}
