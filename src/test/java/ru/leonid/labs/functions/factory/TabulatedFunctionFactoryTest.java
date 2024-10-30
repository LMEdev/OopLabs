package ru.leonid.labs.functions.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.impl.ArrayTabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;


import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DisplayName("Тесты для TabulatedFunctionFactoryTest")
class TabulatedFunctionFactoryTest {

    @Test
    void testArrayTabulatedFunctionFactory() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        TabulatedFunction function = factory.create(xValues, yValues);
        assertInstanceOf(ArrayTabulatedFunction.class, function, "The created function should be an instance of ArrayTabulatedFunction");
    }

    @Test
    void testLinkedListTabulatedFunctionFactory() {
        TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {4.0, 5.0, 6.0};

        TabulatedFunction function = factory.create(xValues, yValues);
        assertInstanceOf(LinkedListTabulatedFunction.class, function, "The created function should be an instance of LinkedListTabulatedFunction");
    }
}
