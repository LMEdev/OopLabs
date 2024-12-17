package ru.leonid.labs.function.factory;

import ru.leonid.labs.function.api.TabulatedFunction;
import ru.leonid.labs.function.impl.LinkedListTabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}