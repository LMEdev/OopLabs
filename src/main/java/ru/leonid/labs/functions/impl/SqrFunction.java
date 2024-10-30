package ru.leonid.labs.functions.impl;

import ru.leonid.labs.functions.api.MathFunction;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
