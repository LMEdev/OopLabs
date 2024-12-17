package ru.leonid.labs.function.impl;

import ru.leonid.labs.function.api.MathFunction;

import java.util.function.BinaryOperator;

public class EulerMethod implements MathFunction {
    private final BinaryOperator<Double> differentialEquation;
    private final double x0, y0; // Начальные условия
    private final double stepSize;

    public EulerMethod(BinaryOperator<Double> differentialEquation, double y0, double x0, double stepSize) {
        if (stepSize <= 0) {
            throw new IllegalArgumentException("Step size must be positive");
        }

        this.differentialEquation = differentialEquation;
        this.y0 = y0;
        this.x0 = x0;
        this.stepSize = stepSize;
    }

    @Override
    public double apply(double x) {
        if (x < x0) {
            throw new IllegalArgumentException("x must be greater than x0");
        }

        double y = y0; // Начальное значение y
        double currentX = x0; // Начальное значение x

        while (currentX < x) {
            y += stepSize * differentialEquation.apply(currentX, y); // Обновляем y
            currentX += stepSize; // Переходим к следующей точке x
        }
        return y; // Возвращаем приближенное значение y в точке x
    }
}
