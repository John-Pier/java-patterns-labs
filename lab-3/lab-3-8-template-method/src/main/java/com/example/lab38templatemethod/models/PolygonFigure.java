package com.example.lab38templatemethod.models;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.Random;

public class PolygonFigure extends AbstractFigure {
    private AnimationTimer timer;

    public PolygonFigure(Pane mainPane) {
        super(new Polygon(12, 15, 16, 32, 6, 50, 54, 30), mainPane);
        var random = new Random();
        ((Shape) element).setFill(color);
        this.element.setLayoutX(random.nextInt(60) + 15);
        this.element.setLayoutY(random.nextInt(50) + 15);
        if (random.nextDouble() > 0.5) {
            speedX = -speedX;
        }
    }

    @Override
    public void start() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                next();
//                element.setRotate((element.getRotate() + 1) % 360);
            }
        };

        timer.start();
    }

    @Override
    public void stop() {
        this.element.setVisible(false);
        timer.stop();
    }
}
