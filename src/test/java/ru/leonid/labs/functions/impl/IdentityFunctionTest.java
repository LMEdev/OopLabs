package ru.leonid.labs.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.function.api.MathFunction;
import ru.leonid.labs.function.impl.IdentityFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест для IdentityFunction")
class IdentityFunctionTest {

    @Test
    void testApply() {
        MathFunction identity = new IdentityFunction();
        double input = 5.0;
        double result = identity.apply(input);
        assertEquals(input, result, 0.0001, "The apply method should return the input value.");
    }
}
