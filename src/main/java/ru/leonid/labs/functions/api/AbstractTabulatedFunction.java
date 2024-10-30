package ru.leonid.labs.functions.api;

import ru.leonid.labs.exceptions.ArrayIsNotSortedException;
import ru.leonid.labs.exceptions.DifferentLengthOfArraysException;

import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {
    private static final Long serialVersionUID = 4984723532365537189L;

    protected int count;

    protected AbstractTabulatedFunction(int count) {
        this.count = count;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            int index = indexOfX(x);
            if (index != -1) {
                return getY(index);
            } else {
                int floorIndex = floorIndexOfX(x);
                return interpolate(x, floorIndex);
            }
        }
    }

    protected void checkIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException("Index out of bounds: " + index);
        }
    }

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Arrays have different lengths");
        }

    }
    public static void checkSorted(double[] xValues) {
        for (int i = 1; i < xValues.length; i++) {
            if (xValues[i - 1] >= xValues[i]) {
                throw new ArrayIsNotSortedException("Array xValues is not sorted in ascending order");
            }
        }
    }

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName())
                .append(" size = ")
                .append(this.count)
                .append("\n");

        for (Point point : this) {
            stringBuilder.append(String.format("[%s; %s]%n", point.x, point.y));
        }

        return stringBuilder.toString();
    }
}

