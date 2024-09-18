package ru.leonid.labs.lab_2.functions.impl;

import ru.leonid.labs.lab_2.functions.api.MathFunction;

public class ConstantFunction implements MathFunction {
    private final double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    @Override
    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}

class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0.0);
    }
}

class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1.0);
    }
}