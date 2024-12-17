package ru.leonid.labs.function.impl;

import ru.leonid.labs.function.api.MathFunction;


public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
}
