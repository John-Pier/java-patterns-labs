package com.johnpier.lab41mvc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MvcController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}