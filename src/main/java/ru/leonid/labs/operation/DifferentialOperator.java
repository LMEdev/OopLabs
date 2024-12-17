package ru.leonid.labs.operation;

import ru.leonid.labs.function.api.MathFunction;

public interface DifferentialOperator<T> extends MathFunction {
    T derive(T function);
}
