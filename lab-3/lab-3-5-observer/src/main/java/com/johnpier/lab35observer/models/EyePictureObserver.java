package com.johnpier.lab35observer.models;

import javafx.scene.Node;

public class EyePictureObserver implements PictureObserver {

    private final PictureAction currentAction;
    private final Node eye;
    private final double eyeScaleY = 0.15;

    public EyePictureObserver(Node eye, PictureAction currentAction) {
        this.currentAction = currentAction;
        this.eye = eye;
    }

    @Override
    public void update(PictureAction action) {
        if (action == currentAction) {
            if (eye.getScaleY() == eyeScaleY) {
                eye.setScaleY(1);
            } else {
                eye.setScaleY(eyeScaleY);
            }
        }
    }
}