package ru.leonid.labs.lab_2.functions.api;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    private final int count;

    public AbstractTabulatedFunction(int count) {
        this.count = count;
    }

    @Override
    public abstract double getX(int index);
    @Override
    public abstract double getY(int index);

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

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    public double interpolate(double x, int floorIndex) {
        double leftX = getX(floorIndex);
        double rightX = getX(floorIndex + 1);
        double leftY = getY(floorIndex);
        double rightY = getY(floorIndex + 1);
        return interpolate(x, leftX, rightX, leftY, rightY);
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }
}

