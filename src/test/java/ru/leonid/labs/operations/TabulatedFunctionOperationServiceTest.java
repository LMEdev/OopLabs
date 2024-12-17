package ru.leonid.labs.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.exceptions.InconsistentFunctionsException;
import ru.leonid.labs.function.api.Point;
import ru.leonid.labs.function.api.TabulatedFunction;
import ru.leonid.labs.function.factory.ArrayTabulatedFunctionFactory;
import ru.leonid.labs.function.factory.LinkedListTabulatedFunctionFactory;
import ru.leonid.labs.function.factory.TabulatedFunctionFactory;
import ru.leonid.labs.function.impl.ArrayTabulatedFunction;
import ru.leonid.labs.function.impl.LinkedListTabulatedFunction;
import ru.leonid.labs.operation.TabulatedFunctionOperationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест для TabulatedFunctionOperationService")
class TabulatedFunctionOperationServiceTest {

    @Test
    void testAsPointsWithArrayTabulatedFunction() {
        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(
                new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{1.0, 4.0, 9.0, 16.0}
        );

        Point[] points = TabulatedFunctionOperationService.asPoints(arrayTabulatedFunction);

        assertEquals(4, points.length);
        for (int i = 0; i < points.length; i++) {
            assertEquals(new double[]{1.0, 2.0, 3.0, 4.0}[i], points[i].x, 1e-9);
            assertEquals(new double[]{1.0, 4.0, 9.0, 16.0}[i], points[i].y, 1e-9);
        }
    }

    @Test
    void testAsPointsWithLinkedListTabulatedFunction() {
        TabulatedFunction function = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0, 4.0},
                new double[]{1.0, 4.0, 9.0, 16.0}
        );

        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        assertNotNull(points);
        assertEquals(4, points.length);
        for (int i = 0; i < points.length; i++) {
            assertNotNull(points[i]);
            assertEquals(new double[]{1.0, 2.0, 3.0, 4.0}[i], points[i].x);
            assertEquals(new double[]{1.0, 4.0, 9.0, 16.0}[i], points[i].y);
        }
    }

    @Test
    void testAdditionWithArrayTabulatedFunction() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());

        TabulatedFunction f1 = new ArrayTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{1.0, 2.0, 3.0}
        );
        TabulatedFunction f2 = new ArrayTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{4.0, 5.0, 6.0}
        );

        TabulatedFunction result = service.add(f1, f2);
        double[] expectedY = {5.0, 7.0, 9.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }

    @Test
    void testSubtractionWithDifferentFactories() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());

        TabulatedFunction f1 = new ArrayTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{5.0, 6.0, 7.0}
        );
        TabulatedFunction f2 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{1.0, 2.0, 3.0}
        );

        TabulatedFunction result = service.subtract(f1, f2);
        double[] expectedY = {4.0, 4.0, 4.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }

    @Test
    void testMultiplyWithDifferentFactories() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());

        TabulatedFunction f1 = new ArrayTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{5.0, 6.0, 7.0}
        );
        TabulatedFunction f2 = new LinkedListTabulatedFunction(
                new double[]{1.0, 2.0, 3.0},
                new double[]{1.0, 2.0, 3.0}
        );

        TabulatedFunction result = service.multiply(f1, f2);
        double[] expectedY = {5.0, 12.0, 21.0};

        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedY[i], result.getY(i));
        }
    }


    @Test
    void testDivisionUsingDifferentTabulatedFunctionFactories() {
        TabulatedFunctionFactory arrayFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService(arrayFunctionFactory);

        TabulatedFunction firstFunction = arrayFunctionFactory.create(
                new double[]{1.0, 2.0, 3.0},
                new double[]{5.0, 6.0, 6.0}
        );
        TabulatedFunction secondFunction = linkedListFunctionFactory.create(
                new double[]{1.0, 2.0, 3.0},
                new double[]{1.0, 2.0, 3.0}
        );

        TabulatedFunction divisionResult = operationService.division(firstFunction, secondFunction);
        double[] expectedResults = {5.0, 3.0, 2.0};

        for (int index = 0; index < divisionResult.getCount(); index++) {
            assertEquals(expectedResults[index], divisionResult.getY(index));
        }
    }


    @Test
    void testAddingFunctionsWithDifferentLengthsShouldThrowException() {
        TabulatedFunctionFactory functionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService(functionFactory);

        TabulatedFunction firstFunction = functionFactory.create(new double[]{1.0, 2.0, 3.0}, new double[]{1.0, 2.0, 3.0});
        TabulatedFunction secondFunction = functionFactory.create(new double[]{1.0, 2.0}, new double[]{4.0, 5.0});

        assertThrows(InconsistentFunctionsException.class, () -> operationService.add(firstFunction, secondFunction));
    }

}