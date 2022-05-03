package com.johnpier.lab35observer.models;

import javafx.scene.Node;

public class LipsPictureObserver implements PictureObserver {

    private final Node lips;

    public LipsPictureObserver(Node lips) {
        this.lips = lips;
    }

    @Override
    public void update(PictureAction action) {
        if (action == PictureAction.LIPS) {
            this.lips.setRotate(180);
        }
    }
}
