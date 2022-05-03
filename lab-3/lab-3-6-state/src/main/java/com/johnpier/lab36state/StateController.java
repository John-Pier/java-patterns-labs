package com.johnpier.lab36state;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;

public class StateController {

    private final Image semesterImage = new Image("D:\\Projects\\Repos\\java-patterns-labs\\lab-3\\lab-3-6-state\\target\\classes\\com\\johnpier\\lab36state\\img\\sem.png");
    private final Image vacationImage = new Image("D:\\Projects\\Repos\\java-patterns-labs\\lab-3\\lab-3-6-state\\target\\classes\\com\\johnpier\\lab36state\\img\\vac.png");
    private final Image sessionImage = new Image("D:\\Projects\\Repos\\java-patterns-labs\\lab-3\\lab-3-6-state\\src\\main\\resources\\com\\johnpier\\lab36state\\img\\ses.png");

    @FXML
    protected ImageView imageView;


    @FXML
    protected void onSemesterButtonClick() {
        imageView.setImage(semesterImage);
    }

    @FXML
    protected void onVacationButtonClick() {
        imageView.setImage(vacationImage);
    }

    @FXML
    protected void onSessionButtonClick() {
        imageView.setImage(sessionImage);
    }
}