package ru.leonid.labs.lab_2.functions.differenital.solver;

import ru.leonid.labs.lab_2.functions.differenital.DifferentialEquation;

public class SimpleIterationSolver {
    private final DifferentialEquation equation;
    private final static double EPSILON = 1E-5;
    private final int maxIterations;

    public SimpleIterationSolver(DifferentialEquation equation, int maxIterations) {
        this.equation = equation;
        this.maxIterations = maxIterations;
    }

    public double solve(double initialGuess) {
        double x = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double xNext = equation.apply(x);
            if (Math.abs(xNext - x) < EPSILON)
                return xNext;
            x = xNext;
        }
        throw new RuntimeException("Maximum number of iterations reached without convergence.");
    }

}
