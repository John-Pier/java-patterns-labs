package com.example.lab38templatemethod.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.Random;

public class PolygonFigure extends AbstractFigure {
    public PolygonFigure(Pane mainPane) {
        super(new Polygon(12, 15, 16, 32, 6, 50, 54, 30), mainPane);
    }

    @Override
    protected void setupElement() {
        var random = new Random();
        ((Shape) element).setFill(color);
        this.element.setLayoutX(random.nextInt(60) + 15);
        this.element.setLayoutY(random.nextInt(50) + 15);
        if (random.nextDouble() > 0.5) {
            speedX = -speedX;
        }
    }
}
