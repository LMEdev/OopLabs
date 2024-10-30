package ru.leonid.labs.functions.impl;

import ru.leonid.labs.functions.api.MathFunction;


public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
}
