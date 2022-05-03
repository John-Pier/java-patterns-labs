package com.johnpier.lab36state.models;

import javafx.scene.image.*;

public class SessionImageState implements ImageState {

    private final Image sessionImage = new Image("D:\\Projects\\Repos\\java-patterns-labs\\lab-3\\lab-3-6-state\\src\\main\\resources\\com\\johnpier\\lab36state\\img\\ses.png");

    private final ImageView view;

    public SessionImageState(ImageView view) {
        this.view = view;
    }

    @Override
    public void visualize() {
        this.view.setImage(sessionImage);
    }
}
