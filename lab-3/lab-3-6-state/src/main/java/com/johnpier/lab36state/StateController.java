package com.johnpier.lab36state;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StateController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}