package ru.leonid.labs.functions.factory;

import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}