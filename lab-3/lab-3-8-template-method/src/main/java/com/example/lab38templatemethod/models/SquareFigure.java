package com.example.lab38templatemethod.models;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class SquareFigure extends AbstractFigure {
    private AnimationTimer timer;

    public SquareFigure(Pane mainPane) {
        super(new Rectangle(24, 24), mainPane);
        var random = new Random();
        ((Rectangle) element).setFill(color);
        this.element.setLayoutX(random.nextInt(20) + 5);
        this.element.setLayoutY(random.nextInt(15) + 5);
    }

    @Override
    public void start() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                next(); // TODO: нет контроля за скоростью
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
