package com.johnpier.lab35observer.models;

import javafx.scene.Node;

public class LipsPictureObserver implements PictureObserver {

    private final Node lips;
    private final double rotate = 180;

    public LipsPictureObserver(Node lips) {
        this.lips = lips;
    }

    @Override
    public void update(PictureAction action) {
        if (action == PictureAction.LIPS) {
            if (this.lips.getRotate() != rotate) {
                this.lips.setRotate(rotate);
            } else {
                this.lips.setRotate(0);
            }
        }
    }
}
