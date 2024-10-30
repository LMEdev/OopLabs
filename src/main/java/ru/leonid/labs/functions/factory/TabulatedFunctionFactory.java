package ru.leonid.labs.functions.factory;

import ru.leonid.labs.functions.api.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
