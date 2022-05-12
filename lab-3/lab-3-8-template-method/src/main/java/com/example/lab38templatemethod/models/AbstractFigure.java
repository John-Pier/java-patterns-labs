package com.example.lab38templatemethod.models;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Random;

public abstract class AbstractFigure {
    protected Node element;
    protected Pane mainPane;
    protected double height;
    protected double width;
    protected double speedX = 1;
    protected double speedY = 1;

    protected Color color;

    protected AnimationTimer timer;

    public AbstractFigure(Node element, Pane mainPane) {
        this.element = element;
        this.mainPane = mainPane;

        height = mainPane.getHeight();
        width = mainPane.getWidth();

        Random random = new Random();
        color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());

        this.setupElement();
    }


    protected abstract void setupElement();

    public Node getElement() {
        return element;
    }

    public void start() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                next();
                afterNext(now);
            }
        };

        timer.start();
    }

    protected void next() {
        this.correctSpeed();
        this.element.setLayoutX(this.element.getLayoutX() + speedX);
        this.element.setLayoutY(this.element.getLayoutY() + speedY);
    }

    protected void correctSpeed() {
        var bounds = this.element.getBoundsInParent();

        if (bounds.getMinX() <= 0 || bounds.getMaxX() >= width) {
            speedX = -speedX;
        } else if (bounds.getMinY() <= 0 || bounds.getMaxY() >= height) {
            speedY = -speedY;
        }
    }

    protected void afterNext(long now) {
    }

    public void stop() {
        this.element.setVisible(false);
        timer.stop();
    }
}
