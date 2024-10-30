package ru.leonid.labs.operations;

import ru.leonid.labs.functions.api.MathFunction;

public interface DifferentialOperator<T> extends MathFunction {
    T derive(T function);
}
