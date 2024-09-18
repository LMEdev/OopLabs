package ru.leonid.labs.lab_2.functions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;
import ru.leonid.labs.lab_2.functions.impl.CompositeFunction;
import ru.leonid.labs.lab_2.functions.impl.IdentityFunction;
import ru.leonid.labs.lab_2.functions.impl.SqrFunction;

public class CompositeFunctionTest {

    @Test
    public void testApplySimpleFunctions() {
        MathFunction sqrFunction = new SqrFunction();
        MathFunction identityFunction = new IdentityFunction();

        MathFunction compositeFunction = new CompositeFunction(identityFunction, sqrFunction);

        assertEquals(4.0, compositeFunction.apply(2.0), 0.001);
        assertEquals(9.0, compositeFunction.apply(3.0), 0.001);
    }

    @Test
    public void testApplyComplexFunctions() {
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
