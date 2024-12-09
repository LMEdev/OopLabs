package ru.leonid.labs.functions.factory;

import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.impl.ArrayTabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}