package com.johnpier.lab23facade;

import com.johnpier.lab23facade.models.TrafficLights;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class AppController {
    @FXML
    private Button startButton;

    @FXML
    private Node autoElement;

    @FXML
    private Pane drawPane;

    @FXML
    private Circle redLight;

    @FXML
    private Circle greenLight;

    @FXML
    private Circle yellowLight;

    private final TrafficLights trafficLights = new TrafficLights();

    @FXML
    protected void onStartButtonClick() {
        if (trafficLights.isStarted()) {
            this.trafficLights.stop();
            // set auto point
            return;
        }

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1500));
        translateTransition.setNode(autoElement);
        translateTransition.setByX(500);
        translateTransition.setCycleCount(90);
        translateTransition.setAutoReverse(false);
        translateTransition.play();

        var gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(1, Color.rgb(255, 255, 255)));
        trafficLights.setHandler(action -> {
            switch (action) {
                case TO_RED -> {
                    this.redLight.setFill(Color.RED);
                    this.greenLight.setFill(gradient);
                    this.yellowLight.setFill(gradient);
                }
                case TO_GREEN -> {
                    this.greenLight.setFill(Color.GREEN);
                    this.yellowLight.setFill(gradient);
                    this.redLight.setFill(gradient);
                }
                case TO_YELLOW -> {
                    this.greenLight.setFill(gradient);
                    this.yellowLight.setFill(Color.YELLOW);
                    this.redLight.setFill(gradient);
                }
            }
        });
        trafficLights.start();
    }
}