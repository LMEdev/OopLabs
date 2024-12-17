package ru.leonid.labs.operation;

import ru.leonid.labs.function.api.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    protected LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> ((function.apply(x) - function.apply(x - step))
                    / step);
    }

    @Override
    public double apply(double x) {
        throw new UnsupportedOperationException();
    }
}
