package ru.leonid.labs.function.factory;

import ru.leonid.labs.function.api.TabulatedFunction;
import ru.leonid.labs.function.impl.ArrayTabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}