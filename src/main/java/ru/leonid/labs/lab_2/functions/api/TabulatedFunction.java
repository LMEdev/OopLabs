package ru.leonid.labs.lab_2.functions.api;

public interface TabulatedFunction extends MathFunction {
    int getCount();
    double getX(int index);
    double getY(int index);
    void setY(int index, double value);
    int indexOfX(double x);
    int indexOfY(double y);
    double leftBound();
    double rightBound();
}

