package com.johnpier.lab41mvc.models;


import javafx.beans.property.*;

public class Row {
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;

    public Row(double x) {
        setX(x);
    }

    public DoubleProperty xProperty() {
        if (xProperty == null) {
            xProperty = new SimpleDoubleProperty(this, "x");
        }
        return xProperty;
    }

    public DoubleProperty yProperty() {
        if (yProperty == null) {
            yProperty = new SimpleDoubleProperty(this, "y");
        }
        return yProperty;
    }

    public double getX() {
        return xProperty().doubleValue();
    }

    public void setX(double x) {
        xProperty().set(x);
        calculateY();
    }

    public double getY() {
        return yProperty().doubleValue();
    }

    public void setY(double y) {
        yProperty().set(y);
    }

    private void calculateY() {
        setY(CubicFunction.getInstance().calculateY(getX()));
    }
}