package ru.leonid.labs.lab_2.functions.differenital.solver;

import org.junit.Test;
import ru.leonid.labs.lab_2.functions.differenital.DifferentialEquation;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SimpleIterationSolverTest {

    @Test
    public void testSolveConvergence() {
        List<Integer> indices = List.of(1, 2); // пример индексов
        DifferentialEquation equation = new DifferentialEquation(indices);
        SimpleIterationSolver solver = new SimpleIterationSolver(equation, 100);

        double result = solver.solve(0.0); // начальное приближение
        double expected = 0.0; // Ожидаемое значение
        assertEquals(expected, result, 1E-5); // Проверка с учетом погрешности
    }

    @Test
    public void testSolveNoConvergence() {
        List<Integer> indices = List.of(1, 2); // Пример индексов
        DifferentialEquation equation = new DifferentialEquation(indices);
        SimpleIterationSolver solver = new SimpleIterationSolver(equation, 2);

        //Проверка, что выбрасывается исключение при отсутствии сходимости
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            solver.solve(100.0); //Начальное приближение, которое не будет сходиться
        });

        assertEquals("Maximum number of iterations reached without convergence.", thrown.getMessage());
    }
}
