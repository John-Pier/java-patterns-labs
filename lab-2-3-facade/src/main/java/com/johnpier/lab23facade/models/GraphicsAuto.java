package com.johnpier.lab23facade.models;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class GraphicsAuto {
    private final Node autoElement;

    private boolean availableToGo = false;
    private boolean isWait = false;

    private final TranslateTransition startTransition = new TranslateTransition();
    private final TranslateTransition finishTransition = new TranslateTransition();

    public GraphicsAuto(Node autoElement) {
        this.autoElement = autoElement;
        setupTransitions();
    }

    private void setupTransitions() {
        startTransition.setDuration(Duration.millis(1000));
        finishTransition.setDuration(Duration.millis(1500));

        startTransition.setByX(200);
        finishTransition.setByX(500);

        startTransition.setCycleCount(1);
        finishTransition.setCycleCount(1);

        startTransition.setAutoReverse(false);
        finishTransition.setAutoReverse(false);

        startTransition.setOnFinished(actionEvent -> {
            if (this.availableToGo) {
                this.finishTransition.play();
                isWait = false;
            } else {
                isWait = true;
            }
        });

        finishTransition.setOnFinished(actionEvent -> {
            this.autoElement.setTranslateX(0);
            this.startTransition.play();
        });

        startTransition.setNode(autoElement);
        finishTransition.setNode(autoElement);
    }

    public void start() {
        this.startTransition.play();
    }

    public void resume() {
        if (isWait) {
            this.isWait = false;
            this.finishTransition.play();
        }
    }

    public void stop() {
        startTransition.stop();
        finishTransition.stop();
        startTransition.jumpTo(Duration.millis(0));
        finishTransition.jumpTo(Duration.millis(0));

        this.autoElement.setTranslateX(0);
    }

    public void setCanGo() {
        availableToGo = true;
    }

    public void setCanNotGo() {
        availableToGo = false;
    }

    public void setIsWait(boolean isWait) {
        this.isWait = isWait;
    }

    public boolean IsWait() {
        return this.isWait;
    }
}
