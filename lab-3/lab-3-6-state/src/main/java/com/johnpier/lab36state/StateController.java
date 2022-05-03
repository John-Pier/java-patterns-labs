package com.johnpier.lab36state;

import com.johnpier.lab36state.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;

public class StateController {

    private ImageContext imageContext;

    @FXML
    protected ImageView imageView;

    public void init() {
        imageContext = new ImageContext(imageView);
    }

    @FXML
    protected void onSemesterButtonClick() {
        if (imageContext.getFlag() != ImageFlag.Semester) {
            imageContext.changeImage(ImageFlag.Semester);
        }

        visualize();
    }

    @FXML
    protected void onVacationButtonClick() {
        if (imageContext.getFlag() != ImageFlag.Vacation) {
            imageContext.changeImage(ImageFlag.Vacation);
        }

        visualize();
    }

    @FXML
    protected void onSessionButtonClick() {
        if (imageContext.getFlag() != ImageFlag.Session) {
            imageContext.changeImage(ImageFlag.Session);
        }

        visualize();
    }

    private void visualize() {
        imageContext.visualize();
    }
}