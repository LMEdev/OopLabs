package ru.leonid.labs.lab_2.functions.impl;

import ru.leonid.labs.lab_2.functions.api.AbstractTabulatedFunction;
import ru.leonid.labs.lab_2.functions.api.MathFunction;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private final double[] xValues;
    private final double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        super(xValues.length);

        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues.length != yValues.length");
        }

        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array length must be at least 2");
        }

        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] <= xValues[i - 1]) {
                throw new IllegalArgumentException("xValues are not sorted in increasing order");
            }
        }

        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        super(count);

        if (count < 2) {
            throw new IllegalArgumentException("Count must be at least 2");
        }

        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        this.xValues = new double[count];
        this.yValues = new double[count];

        double step = (xTo - xFrom) / (count - 1);

        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * step;
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    public int getCount() {
        return xValues.length;
    }

    @Override
    public double getX(int index) {
        checkIndex(index);
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        checkIndex(index);
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        checkIndex(index);
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < xValues.length; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < yValues.length; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[xValues.length - 1];
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            return 0;
        }
        if (x > rightBound()) {
            return getCount() - 1;
        }
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return getCount() - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return extrapolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return extrapolate(x, xValues[getCount() - 2], xValues[getCount() - 1], yValues[getCount() - 2], yValues[getCount() - 1]);
    }

    private double extrapolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
