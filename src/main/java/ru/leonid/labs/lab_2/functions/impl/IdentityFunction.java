package ru.leonid.labs.lab_2.functions.impl;

import ru.leonid.labs.lab_2.functions.api.MathFunction;


public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
}
