package ru.leonid.labs.lab_2.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.lab_2.functions.api.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для ArrayTabulatedFunction")
class ArrayTabulatedFunctionTest {

    @Test
    void testConstructorWithArrays() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(3, func.getCount());
        assertEquals(1.0, func.getX(0), 0.001);
        assertEquals(2.0, func.getX(1), 0.001);
        assertEquals(3.0, func.getX(2), 0.001);
        assertEquals(2.0, func.getY(0), 0.001);
        assertEquals(4.0, func.getY(1), 0.001);
        assertEquals(6.0, func.getY(2), 0.001);
    }

    @Test
    void testConstructorWithFunction() {
        MathFunction source = x -> x * 2;
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(3, func.getCount());
        assertEquals(1.0, func.getX(0), 0.001);
        assertEquals(2.0, func.getX(1), 0.001);
        assertEquals(3.0, func.getX(2), 0.001);
        assertEquals(2.0, func.getY(0), 0.001);
        assertEquals(4.0, func.getY(1), 0.001);
        assertEquals(6.0, func.getY(2), 0.001);
    }

    @Test
    void testLeftExtrapolation() {
        MathFunction source = x -> x * 2;
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(source, 1.0, 3.0, 3);

        //экстраполяция влево от самой левой точки (1.0)
        // Ожидаемое значение: 2.0 + (0.0 - 1.0) * (4.0 - 2.0) / (2.0 - 1.0) = 2.0 - 2.0 = 0.0
        assertEquals(0.0, func.extrapolateLeft(0.0), 0.001);
    }

    @Test
    void testRightExtrapolation() {
        MathFunction source = x -> x * 2;
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(8.0, func.extrapolateRight(4.0), 0.001);
    }

    @Test
    void testInterpolation() {
        MathFunction source = x -> x * 2;
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(4.0, func.interpolate(2.0, 0), 0.001);
    }

    @Test
    void testIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1, func.indexOfX(2.0));
        assertEquals(-1, func.indexOfX(4.0));
    }

    @Test
    void testIndexOfY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1, func.indexOfY(4.0));
        assertEquals(-1, func.indexOfY(7.0));
    }

    @Test
    void testSetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);

        func.setY(1, 5.0);
        assertEquals(5.0, func.getY(1), 0.001);
    }

    @Test
    void testBounds() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1.0, func.leftBound(), 0.001);
        assertEquals(3.0, func.rightBound(), 0.001);
    }

    @Test
    void testFloorIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);

        assertEquals(1, func.floorIndexOfX(2.5));
        assertEquals(0, func.floorIndexOfX(0.5));
        assertEquals(2, func.floorIndexOfX(3.5));
    }

    @Test
    void testInsert() {
        double[] xValues = {1, 3, 5};
        double[] yValues = {2, 6, 10};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        function.insert(4, 8);
        assertEquals(4, function.getX(2), 0.001);
        assertEquals(8, function.getY(2), 0.001);

        function.insert(0, 0);
        assertEquals(0, function.getX(0), 0.001);
        assertEquals(0, function.getY(0), 0.001);

        function.insert(6, 12);
        assertEquals(6, function.getX(function.getCount() - 1), 0.001);
        assertEquals(12, function.getY(function.getCount() - 1), 0.001);

        function.insert(4, 20);
        assertEquals(20, function.getY(3), 0.001);
    }

    @Test
    void testRemove() {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0, 6.0});

        function.remove(1);
        assertEquals(2, function.getCount());
        assertEquals(1.0, function.getX(0), 0.001);
        assertEquals(6.0, function.getY(1), 0.001);

        function.remove(0);
        assertEquals(1, function.getCount());
        assertEquals(6.0, function.getY(0), 0.001);

        function.remove(0);
        assertEquals(0, function.getCount());
    }

}
