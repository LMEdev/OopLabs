package ru.leonid.labs.functions.impl;

import ru.leonid.labs.functions.api.AbstractTabulatedFunction;
import ru.leonid.labs.functions.api.Insertable;
import ru.leonid.labs.functions.api.MathFunction;
import ru.leonid.labs.functions.api.Removable;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private double[] xValues;
    private double[] yValues;
    private int capacity;

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

        this.count = xValues.length;
        this.capacity = count+10; //резервируем доп ячейки
        this.xValues = Arrays.copyOf(xValues, capacity);
        this.yValues = Arrays.copyOf(yValues, capacity);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        super(count);

        if (count < 2) {
            throw new IllegalArgumentException("Number of points must be at least 2");
        }

        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        this.count = count;
        this.capacity = count + 10;
        this.xValues = new double[capacity];
        this.yValues = new double[capacity];

        double step = (xTo - xFrom) / (count - 1);

        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * step;
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public int getCount() {
        return count;
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
        for (int i = 0; i < getCount(); i++) {
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
        if (count > 0) {
            return xValues[0];
        }
        throw new IllegalStateException("No values in the function");
    }

    @Override
    public double rightBound() {
        if (count > 0) {
            return xValues[count - 1];
        }
        throw new IllegalStateException("No values in the function");
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
        if (count == 1) {
            return yValues[0];
        }
        return extrapolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return extrapolate(x, xValues[getCount() - 2], xValues[getCount() - 1], yValues[getCount() - 2], yValues[getCount() - 1]);
    }

    private double extrapolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX);
    }

    @Override
    public void insert(double x, double y) {
        int index = indexOfX(x);

        if (index != -1) { // если x уже в массиве, то обновляем
            yValues[index] = y;
            return;
        }

        if (x < leftBound()) { // если левее, вставляем в начало
            insertAt(0, x, y);
            return;
        }

        if (x > rightBound()) { // если правее, вставляем в конец
            insertAt(count, x, y);
            return;
        }

        for (int i = 0; i < count - 1; ++i) {
            if (xValues[i] < x && xValues[i + 1] > x) {
                insertAt(i + 1, x, y); // вставляем между этими двумя значениями
                return;
            }
        }
    }


    private void insertAt(int index, double x, double y){
        if (count >= capacity){
            capacity *= 2;
            xValues = Arrays.copyOf(xValues, capacity);
            yValues = Arrays.copyOf(yValues, capacity);
        }

        System.arraycopy(xValues, index, xValues, index + 1, count - index);
        System.arraycopy(yValues, index, yValues, index + 1, count - index);

        xValues[index] = x;
        yValues[index] = y;

        count++;
    }

    @Override
    public void remove(int index){
        checkIndex(index);

        for (int i = index; i < count - 1; i++) { //сдвигаем все влево начиная с index'a
            xValues[i] = xValues[i + 1];
            yValues[i] = yValues[i + 1];
        }

        count--;
        xValues[count] = 0;
        yValues[count] = 0;
    }

}
