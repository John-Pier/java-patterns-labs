package com.johnpier.lab36state.models;

import javafx.scene.image.*;

public class SemesterImageState implements ImageState {

    private final Image semesterImage = new Image("D:\\Projects\\Repos\\java-patterns-labs\\lab-3\\lab-3-6-state\\target\\classes\\com\\johnpier\\lab36state\\img\\sem.png");

    private final ImageView view;

    public SemesterImageState(ImageView view) {
        this.view = view;
    }

    @Override
    public void visualize() {
        this.view.setImage(semesterImage);
    }
}
