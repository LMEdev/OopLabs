package ru.leonid.labs.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.function.api.MathFunction;
import ru.leonid.labs.function.impl.CompositeFunction;
import ru.leonid.labs.function.impl.IdentityFunction;
import ru.leonid.labs.function.impl.SqrFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для CompositeFunction")
class CompositeFunctionTest {

    @Test
    void testApplySimpleFunctions() {
        MathFunction sqrFunction = new SqrFunction();
        MathFunction identityFunction = new IdentityFunction();

        MathFunction compositeFunction = new CompositeFunction(identityFunction, sqrFunction);

        assertEquals(4.0, compositeFunction.apply(2.0), 0.001);
        assertEquals(9.0, compositeFunction.apply(3.0), 0.001);
    }

    @Test
    void testApplyComplexFunctions() {
        MathFunction sqrFunction = new SqrFunction();
        MathFunction identityFunction = new IdentityFunction();

        MathFunction compositeFunction1 = new CompositeFunction(sqrFunction, identityFunction);
        MathFunction compositeFunction2 = new CompositeFunction(identityFunction, compositeFunction1);

        assertEquals(4.0, compositeFunction1.apply(2.0), 0.001);
        assertEquals(9.0, compositeFunction1.apply(3.0), 0.001);
        assertEquals(4.0, compositeFunction2.apply(2.0), 0.001);
        assertEquals(9.0, compositeFunction2.apply(3.0), 0.001);
    }
}
