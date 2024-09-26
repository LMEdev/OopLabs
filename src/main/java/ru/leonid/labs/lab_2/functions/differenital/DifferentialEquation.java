package ru.leonid.labs.lab_2.functions.differenital;

import ru.leonid.labs.lab_2.functions.api.MathFunction;

import java.util.List;
import java.util.function.Function;

public class DifferentialEquation implements MathFunction {
    private final Function<Double, Double> g;  //функция g(x)
    private List<Integer> indices;       //cписок индексов переменных

    public DifferentialEquation(List<Integer> indices) {
        this.indices = indices;
        this.g = parseEquation();
    }

    private Function<Double, Double> parseEquation() {
        return x -> (3 * Math.pow(x, 2) + x) / 4;
    }

    @Override
    public double apply(double x) {
        return g.apply(x);
    }
}