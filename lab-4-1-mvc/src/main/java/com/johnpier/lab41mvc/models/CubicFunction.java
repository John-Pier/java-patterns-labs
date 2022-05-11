package com.johnpier.lab41mvc.models;

public class CubicFunction implements CalculatedFunction {

    private static final CalculatedFunction function = new CubicFunction();

    private CubicFunction() {
    }

    @Override
    public Double calculateY(double x) {
        return (4.0 * Math.pow(x, 3) - 2 * Math.pow(x, 2) + 5 * x - 1) / (3.0 - x);
    }

    public static CalculatedFunction getInstance() {
        return function;
    }
}
