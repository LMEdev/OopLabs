package ru.leonid.labs.lab_2.functions.impl;

import ru.leonid.labs.lab_2.functions.api.MathFunction;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LinkedListTabulatedFunctionTest {

    @Test
    public void testConstructorWithArrays() {
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
    public void testConstructorWithFunction() {
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
    public void testLeftExtrapolation() {
        MathFunction source = x -> x * 2;
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(0.0, func.extrapolateLeft(0.0), 0.001);
    }

    @Test
    public void testRightExtrapolation() {
        MathFunction source = x -> x * 2;
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(8.0, func.extrapolateRight(4.0), 0.001);
    }

    @Test
    public void testInterpolation() {
        MathFunction source = x -> x * 2;
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(source, 1.0, 3.0, 3);

        assertEquals(4.0, func.interpolate(2.0, 0), 0.001);
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, func.indexOfX(2.0));
        assertEquals(-1, func.indexOfX(4.0));
    }

    @Test
    public void testIndexOfY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, func.indexOfY(4.0));
        assertEquals(-1, func.indexOfY(7.0));
    }

    @Test
    public void testSetY() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        func.setY(1, 5.0);
        assertEquals(5.0, func.getY(1), 0.001);
    }

    @Test
    public void testBounds() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1.0, func.leftBound(), 0.001);
        assertEquals(3.0, func.rightBound(), 0.001);
    }

    @Test
    public void testFloorIndexOfX() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(1, func.floorIndexOfX(2.5));
        assertEquals(0, func.floorIndexOfX(0.5));
        assertEquals(2, func.floorIndexOfX(3.5));
    }

    @Test
    public void testFunctionCombination() {
        double[] xValues1 = {1.0, 2.0, 3.0};
        double[] yValues1 = {2.0, 4.0, 6.0};
        ArrayTabulatedFunction func1 = new ArrayTabulatedFunction(xValues1, yValues1);

        double[] xValues2 = {2.0, 4.0, 6.0};
        double[] yValues2 = {4.0, 8.0, 12.0};
        LinkedListTabulatedFunction func2 = new LinkedListTabulatedFunction(xValues2, yValues2);

        assertEquals(6.0, func1.apply(3.0), 0.001);
        assertEquals(8.0, func2.apply(4.0), 0.001);
    }
}
