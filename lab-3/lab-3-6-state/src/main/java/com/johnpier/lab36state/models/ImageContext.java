package com.johnpier.lab36state.models;

import javafx.scene.image.*;

public class ImageContext implements ImageState {
    private ImageState state;

    private ImageView view;

    private ImageFlag flag;

    public ImageContext(ImageView view) {
        this.view = view;
    }

    public ImageFlag getFlag() {
        return flag;
    }

    public void changeImage(ImageFlag flag) {
        switch (flag) {
            case Session -> this.state = new SessionImageState(this.view);
            case Semester -> this.state = new SemesterImageState(this.view);
            case Vacation -> this.state = new VacationImageState(this.view);
        }
    }


    @Override
    public void visualize() {
        state.visualize();
    }
}
