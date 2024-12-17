package ru.leonid.labs.function.impl;

import ru.leonid.labs.function.api.MathFunction;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
