package com.example.lab38templatemethod.models;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class SquareFigure extends AbstractFigure {
    private boolean isStopped = false;

    public SquareFigure(Pane mainPane) {
        super(new Rectangle(24, 24), mainPane);
        ((Rectangle) element).setFill(color);
        this.element.setLayoutX(10);
        this.element.setLayoutY(20);
    }

    @Override
    public void start() {
        try {
            while (!isStopped || !Thread.currentThread().isInterrupted()) {
                Thread.sleep(25);
                this.next();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            this.isStopped = true;
        }
    }

    @Override
    public void stop() {
        this.isStopped = true;
        this.element.setVisible(false);
    }
}
