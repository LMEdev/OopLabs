package ru.leonid.labs.function.impl;

import ru.leonid.labs.function.api.MathFunction;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SimpleIterationMethod implements MathFunction{
    private final MathFunction[] systemFunctions;
    private final double epsilon;
    private final int iterationLimit;

    public SimpleIterationMethod(MathFunction[] systemFunctions, double epsilon, int maxItertions) {
        this.systemFunctions = systemFunctions;
        this.epsilon = epsilon;
        this.iterationLimit = maxItertions;
    }

    @Override
    public double apply(double initialValue) {
        double[] currentValues = new double[systemFunctions.length];
        Arrays.fill(currentValues, initialValue);

        for (int itertation = 0; itertation < iterationLimit; ++itertation){
            double[] newValues = computeNextValues(currentValues);

            if (hasConverged(currentValues, newValues)){
                return newValues[0];
            }

            currentValues = newValues;
        }

        throw new ArithmeticException("Solution not found");
    }

    private double[] computeNextValues(double[] currentGuess) {
        return IntStream.range(0, systemFunctions.length)
                .mapToDouble(i -> systemFunctions[i].apply(currentGuess[i]))
                .toArray();
    }

    private boolean hasConverged(double[] currentGuess, double[] nextGuess) {
        return IntStream.range(0, currentGuess.length)
                .allMatch(i -> Math.abs(nextGuess[i] - currentGuess[i]) < epsilon);
    }
}
