package com.johnpier.lab36state.models;

import javafx.scene.image.*;

public class VacationImageState implements ImageState {

    private final Image vacationImage = new Image("D:\\Projects\\Repos\\java-patterns-labs\\lab-3\\lab-3-6-state\\target\\classes\\com\\johnpier\\lab36state\\img\\vac.png");

    private final ImageView view;

    public VacationImageState(ImageView view) {
        this.view = view;
    }

    @Override
    public void visualize() {
        view.setImage(vacationImage);
    }
}
