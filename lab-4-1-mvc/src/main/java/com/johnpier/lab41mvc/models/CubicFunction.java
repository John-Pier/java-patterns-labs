package com.johnpier.lab41mvc.models;

public class CubicFunction implements CalculatedFunction {

    private static final CalculatedFunction function = new CubicFunction();

    private CubicFunction() {
    }

    @Override
    public Double calculateY(double x) {
        return (4.0 * Math.pow(x, 3) - 6 * Math.pow(x, 2) + x - 3) * (Math.sqrt(Math.abs(x + 5))) / (2.0 - x);
    }

    public static CalculatedFunction getInstance() {
        return function;
    }
}
