package com.johnpier.lab35observer.models;

public class EyePictureObserver implements PictureObserver {

    private final PictureAction currentAction;

    public EyePictureObserver(PictureAction currentAction) {
        this.currentAction = currentAction;
    }

    @Override
    public void update(PictureAction action) {
        if (action == currentAction) {

        }
    }
}