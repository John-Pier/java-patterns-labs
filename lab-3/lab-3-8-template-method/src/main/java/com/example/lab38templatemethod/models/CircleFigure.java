package com.example.lab38templatemethod.models;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.Random;

public class CircleFigure extends AbstractFigure {

    private AnimationTimer timer;

    public CircleFigure(Pane mainPane) {
        super(new Circle(12), mainPane);
        var random = new Random();
        ((Shape) element).setFill(color);
        element.setLayoutX(random.nextInt(60) + 15);
        element.setLayoutY(random.nextInt(50) + 15);
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

                if ((now / 17_000_000_000L) % 5 == 0) {
                    ((Circle) element).setFill(color.darker());
                }

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
