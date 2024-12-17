package ru.leonid.labs.functions.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.leonid.labs.exceptions.ArrayIsNotSortedException;
import ru.leonid.labs.exceptions.DifferentLengthOfArraysException;
import ru.leonid.labs.exceptions.InterpolationException;
import ru.leonid.labs.function.api.MathFunction;
import ru.leonid.labs.function.api.Point;
import ru.leonid.labs.function.impl.ArrayTabulatedFunction;
import ru.leonid.labs.function.impl.LinkedListTabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тесты для LinkedListTabulatedFunction")
class LinkedListTabulatedFunctionTest {

    @Test
    void testIteratorUsingWhileLoop() {
        double[] xCoordinates = {1.0, 2.0, 4.5, 10.0};
        double[] yCoordinates = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xCoordinates, yCoordinates);

        Iterator<Point> iterator = function.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(function.getX(index), point.x, 1e-9);
            assertEquals(function.getY(index), point.y, 1e-9);
            index++;
        }

        assertEquals(function.getCount(), index, "Количество точек должно совпадать");
    }

    @Test
    void testIteratorUsingForEachLoop() {
        double[] xCoordinates = {1.0, 2.0, 4.5, 10.0};
        double[] yCoordinates = {0.0, 3.0, 2.0, 1.1};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xCoordinates, yCoordinates);

        int index = 0;
        for (Point point : function) {
            assertEquals(function.getX(index), point.x, 1e-9);
            assertEquals(function.getY(index), point.y, 1e-9);
            index++;
        }

        assertEquals(function.getCount(), index, "Количество точек должно совпадать");
    }

    @Test
    void shouldThrowExceptionWhenArrayIsUnsortedInConstructor() {
        double[] xData = {8.0, 1.5, 3.5, 0.5};
        double[] yData = {0.5, 2.2, 1.8, 1.0};

        assertThrows(ArrayIsNotSortedException.class, () -> new LinkedListTabulatedFunction(xData, yData));
    }

    @Test
    void shouldThrowExceptionWhenArraysHaveDifferentLengthsInConstructor() {
        double[] xData = {2.0, 4.0, 6.0};
        double[] yData = {1.0, 3.5, 4.5, 5.0};

        assertThrows(DifferentLengthOfArraysException.class, () -> new LinkedListTabulatedFunction(xData, yData));
    }

    @Test
    void shouldThrowExceptionWhenArrayHasSingleElementInConstructor() {
        double[] xData = {5.0};
        double[] yData = {10.0};

        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xData, yData));
    }

    @Test
    void shouldThrowInterpolationExceptionWhenXOutOfRange() {
        double[] xData = {1.0, 2.5, 3.0};
        double[] yData = {3.0, 4.0, 5.0};

        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xData, yData);
        assertThrows(InterpolationException.class, () -> function.interpolate(3.8, 1));
    }


    @Test
    void testConstructorWithArrays() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

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
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(3, func.getCount());
        assertEquals(1.0, func.getX(0), 0.001);
        assertEquals(2.0, func.getX(1), 0.001);
        assertEquals(3.0, func.getX(2), 0.001);
        assertEquals(2.0, func.getY(0), 0.001);
        assertEquals(4.0, func.getY(1), 0.001);
        assertEquals(6.0, func.getY(2), 0.001);
    }


    @Test
    void testIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, func.indexOfX(2.0));
        assertEquals(-1, func.indexOfX(4.0));
    }

    @Test
    void testIndexOfY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, func.indexOfY(4.0));
        assertEquals(-1, func.indexOfY(7.0));
    }

    @Test
    void testSetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        func.setY(1, 5.0);
        assertEquals(5.0, func.getY(1), 0.001);
    }

    @Test
    void testBounds() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1.0, func.leftBound(), 0.001);
        assertEquals(3.0, func.rightBound(), 0.001);
    }

    @Test
    void testFunctionCombination() {
        double[] xValues1 = {1.0, 2.0, 3.0};
        double[] yValues1 = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(xValues1, yValues1);

        double[] xValues2 = {2.0, 4.0, 6.0};
        double[] yValues2 = {4.0, 8.0, 12.0};
        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(xValues2, yValues2);

        assertEquals(6.0, func1.apply(3.0), 0.001);
        assertEquals(8.0, func2.apply(4.0), 0.001);
    }

    @Test
    void testInsertAndReplace() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1.0, 2.0}, new double[]{2.0, 4.0});

        //1. Замена существующего значения
        function.insert(1.0, 3.0);
        assertEquals(2, function.getCount());
        assertEquals(3.0, function.getY(0), 0.001);

        //2. Вставка в начало
        function.insert(0.0, 1.0);
        assertEquals(3, function.getCount());
        assertEquals(1.0, function.getY(0), 0.001);

        //3. Вставка в середину
        function.insert(1.5, 2.5);
        assertEquals(4, function.getCount());
        assertEquals(2.5, function.getY(2), 0.001);

        //4. Вставка в конец
        function.insert(3.0, 6.0);
        assertEquals(5, function.getCount());
        assertEquals(6.0, function.getY(4), 0.001);
    }

    @Test
    void testRemove() {
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0, 6.0});

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
