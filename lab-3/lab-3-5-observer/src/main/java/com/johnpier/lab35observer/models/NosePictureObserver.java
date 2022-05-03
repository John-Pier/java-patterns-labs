package com.johnpier.lab35observer.models;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class NosePictureObserver implements PictureObserver {

    private final Shape noiseElement;

    public NosePictureObserver(Shape noiseElement) {
        this.noiseElement = noiseElement;
    }

    @Override
    public void update(PictureAction action) {
        if (action == PictureAction.NOSE) {
            var color = Color.color(Math.random(), Math.random(), Math.random());
            noiseElement.setFill(color);
        }
    }
}