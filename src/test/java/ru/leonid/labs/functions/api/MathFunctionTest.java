package ru.leonid.labs.functions.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.leonid.labs.functions.api.MathFunction;
import ru.leonid.labs.functions.impl.ConstantFunction;
import ru.leonid.labs.functions.impl.IdentityFunction;
import ru.leonid.labs.functions.impl.SqrFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для MathFunctionEntity")
class MathFunctionTest {

    @Test
    void testAndThenWithSimpleFunctions() {
        MathFunction identity = new IdentityFunction();
        MathFunction square = new SqrFunction();
        MathFunction constant = new ConstantFunction(5);

        //f(g(x))
        MathFunction composite = identity.andThen(square);
        assertEquals(4.0, composite.apply(2.0), 0.001);

        //f(g(h(x)))
        composite = identity.andThen(square).andThen(constant);
        assertEquals(5.0, composite.apply(2.0), 0.001);
    }

    @Test
    void testComplexChainOfFunctions() {
        MathFunction identity = new IdentityFunction();
        MathFunction square = new SqrFunction();
        MathFunction constant = new ConstantFunction(3);

        //f(g(h(x)))
        MathFunction composite = square.andThen(identity).andThen(constant);
        assertEquals(3.0, composite.apply(10.0), 0.001);
    }
}
