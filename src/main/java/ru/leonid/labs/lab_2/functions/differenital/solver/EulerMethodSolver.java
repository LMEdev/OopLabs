package ru.leonid.labs.lab_2.functions.differenital.solver;

import ru.leonid.labs.lab_2.functions.differenital.DifferentialEquation;

public class EulerMethodSolver {
    private final DifferentialEquation equation;
    private final double stepSize;
    private final int steps;

    public EulerMethodSolver(DifferentialEquation equation, double stepSize, int steps) {
        this.equation = equation;
        this.stepSize = stepSize;
        this.steps = steps;
    }

    public double[] solve(double initialValue) {
        double[] results = new double[steps + 1];
        results[0] = initialValue;

        for (int i = 1; i <= steps; i++) {
            results[i] = results[i - 1] + stepSize * equation.apply(results[i - 1]);
        }

        return results;
    }
}
