package ru.leonid.labs.functions.api;

import ru.leonid.labs.functions.impl.CompositeFunction;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
