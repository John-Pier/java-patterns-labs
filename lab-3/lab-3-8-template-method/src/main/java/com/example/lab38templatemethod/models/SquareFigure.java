package com.example.lab38templatemethod.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class SquareFigure extends AbstractFigure {
    public SquareFigure(Pane mainPane) {
        super(new Rectangle(24, 24), mainPane);
    }

    @Override
    protected void setupElement() {
        var random = new Random();
        ((Rectangle) element).setFill(color);
        this.element.setLayoutX(random.nextInt(20) + 5);
        this.element.setLayoutY(random.nextInt(15) + 5);
    }
}
