package ru.leonid.labs.lab_2.functions;

import org.junit.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;
import ru.leonid.labs.lab_2.functions.impl.ConstantFunction;
import ru.leonid.labs.lab_2.functions.impl.IdentityFunction;
import ru.leonid.labs.lab_2.functions.impl.SqrFunction;

import static org.junit.Assert.assertEquals;

public class MathFunctionTest {

    @Test
    public void testAndThenWithSimpleFunctions() {
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
    public void testComplexChainOfFunctions() {
        MathFunction identity = new IdentityFunction();
        MathFunction square = new SqrFunction();
        MathFunction constant = new ConstantFunction(3);

        //f(g(h(x)))
        MathFunction composite = square.andThen(identity).andThen(constant);
        assertEquals(3.0, composite.apply(10.0), 0.001);
    }
}
