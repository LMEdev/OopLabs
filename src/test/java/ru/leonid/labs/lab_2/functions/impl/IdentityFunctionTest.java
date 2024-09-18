package ru.leonid.labs.lab_2.functions;

import org.junit.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;
import ru.leonid.labs.lab_2.functions.impl.IdentityFunction;

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
