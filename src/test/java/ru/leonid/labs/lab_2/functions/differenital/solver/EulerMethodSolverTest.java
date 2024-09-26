package ru.leonid.labs.lab_2.functions.differenital.solver;

import org.junit.Test;
import ru.leonid.labs.lab_2.functions.differenital.DifferentialEquation;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class EulerMethodSolverTest {

    @Test
    public void testSolveWithSmallStep() {
        List<Integer> indices = List.of(1, 2); // Пример индексов
        DifferentialEquation equation = new DifferentialEquation(indices);

        double initialValue = 0.0;
        double stepSize = 0.01;
        int steps = 100;

        EulerMethodSolver solver = new EulerMethodSolver(equation, stepSize, steps);
        double[] results = solver.solve(initialValue);

        //проверка, что значения увеличиваются (если это предполагается)
        for (int i = 1; i < results.length; i++) {
            assertTrue(results[i] >= results[i - 1]);
        }

        //проверка, что значения не выходят за разумные пределы
        for (double result : results) {
            assertTrue(result < 10); //предполагаем, что решение не должно быть слишком большим
        }
    }

    @Test
    public void testSufficientSteps() {
        List<Integer> indices = List.of(1, 2);
        DifferentialEquation equation = new DifferentialEquation(indices);

        double initialValue = 0.0;
        double stepSize = 0.1;  // Больше шаг
        int steps = 10;

        EulerMethodSolver solver = new EulerMethodSolver(equation, stepSize, steps);
        double[] results1 = solver.solve(initialValue);

        //уменьшаем шаг
        stepSize = 0.01;
        steps = 100;
        EulerMethodSolver solverFine = new EulerMethodSolver(equation, stepSize, steps);
        double[] results2 = solverFine.solve(initialValue);

        //проверка, что результаты с меньшим шагом более точные
        for (int i = 0; i < results1.length; i++) {
            assertTrue(Math.abs(results1[i] - results2[i * 10]) < 0.1);
        }
    }
}
