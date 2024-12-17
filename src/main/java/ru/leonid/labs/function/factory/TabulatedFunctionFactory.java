package ru.leonid.labs.function.factory;

import ru.leonid.labs.function.api.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
