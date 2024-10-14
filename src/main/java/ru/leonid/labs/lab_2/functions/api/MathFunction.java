package ru.leonid.labs.lab_2.functions.api;

import ru.leonid.labs.lab_2.functions.impl.CompositeFunction;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
