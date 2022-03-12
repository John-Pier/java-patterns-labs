package com.johnpier.lab23facade.facade;

import com.johnpier.lab23facade.models.GraphicsAuto;
import com.johnpier.lab23facade.models.TrafficLights;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MainAppFacade implements AppFacade {
    private final TrafficLights trafficLights = new TrafficLights();
    private final GraphicsAuto graphicsAuto;

    public MainAppFacade(Node autoElement, Circle redLight, Circle greenLight, Circle yellowLight) {
        graphicsAuto = new GraphicsAuto(autoElement);

        trafficLights.setHandler(action -> {
            switch (action) {
                case TO_RED -> {
                    redLight.setFill(Color.RED);
                    greenLight.setFill(Color.BLACK);
                    yellowLight.setFill(Color.BLACK);

                    this.graphicsAuto.setCanNotGo();
                }
                case TO_GREEN -> {
                    greenLight.setFill(Color.GREEN);
                    yellowLight.setFill(Color.BLACK);
                    redLight.setFill(Color.BLACK);

                    this.graphicsAuto.setCanGo();
                    if (isStarted() && this.graphicsAuto.IsWait()) {
                        graphicsAuto.resume();
                    }
                }
                case TO_YELLOW -> {
                    greenLight.setFill(Color.BLACK);
                    yellowLight.setFill(Color.YELLOW);
                    redLight.setFill(Color.BLACK);

                    this.graphicsAuto.setCanNotGo();
                }
            }
        });
    }

    @Override
    public void startEmulation() {
        trafficLights.start();
        graphicsAuto.start();
    }

    @Override
    public void stopEmulation() {
        if (isStarted()) {
            this.trafficLights.stop();
            this.graphicsAuto.stop();
        }
    }

    @Override
    public boolean isStarted() {
        return trafficLights.isStarted();
    }
}
