package ru.leonid.labs.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.functions.api.TabulatedFunction;
import ru.leonid.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.leonid.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.leonid.labs.functions.impl.ArrayTabulatedFunction;
import ru.leonid.labs.functions.impl.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DisplayName("Тесты для DifferentialOperator")
class DifferentialOperatorTests {

    @Test
    void testDefaultConstructorUsesArrayFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        assertInstanceOf(ArrayTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void testConstructorWithLinkedListFactory() {
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, operator.getFactory());
    }

    @Test
    void testDeriveWithNewLinkedListFunction(){
        //Создание производной табулированной функции
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction function = operator.derive(new LinkedListTabulatedFunction(x -> x*x + 2*x + 1, -3, 3, 5));

        double[] xValues = new double[] {-3, -1.5, 0, 1.5, 3};
        double[] yValues = new double[] {-4, -1, 2, 5, 8};
        double maxEpsilon = function.getY(1) - function.getY(0);

        for (int i = 0; i < function.getCount()-1; i++) { //вычисляем интервал для проивзодной
            if((function.getY(i+1) - function.getY(i))>maxEpsilon){
                maxEpsilon = function.getY(i+1) - function.getY(i);
            }
        }

        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(xValues[i], function.getX(i));
            assertEquals(yValues[i], function.getY(i), maxEpsilon);
        }
    }

    @Test
    void testDeriveWithNewArrayFunction(){
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction function = operator.derive(new ArrayTabulatedFunction(x -> x*x + 2*x + 1, -3, 3, 5));

        double[] xValues = new double[] {-3, -1.5, 0, 1.5, 3};
        double[] yValues = new double[] {-4, -1, 2, 5, 8};
        double maxEpsilon = function.getY(1) - function.getY(0);

        for (int i = 0; i < function.getCount()-1; i++) { //вычисляем интервал для проивзодной
            if((function.getY(i+1) - function.getY(i))>maxEpsilon){
                maxEpsilon = function.getY(i+1) - function.getY(i);
            }
        }

        System.out.println(maxEpsilon);
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(xValues[i], function.getX(i));
            assertEquals(yValues[i], function.getY(i), 3);
        }
    }
}
