package com.example.lab38templatemethod;

import com.example.lab38templatemethod.models.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class TemplateMethodController {
    @FXML
    private Pane mainPane;
    private final ArrayList<AbstractFigure> figures = new ArrayList<>();

    @FXML
    protected void onStartButtonClick() {
        AbstractFigure figure;
        var rand = Math.random();
        if (rand <= 0.33) {
            figure = new CircleFigure(mainPane);
        } else if (rand <= 0.66) {
            figure = new SquareFigure(mainPane);
        } else {
            figure = new PolygonFigure(mainPane);
        }

        mainPane.getChildren().add(figure.getElement());
        figures.add(figure);
        figure.start();
    }

    @FXML
    protected void onStopButtonClick() {
        figures.forEach(figure -> {
            figure.stop();
            this.mainPane.getChildren().remove(figure.getElement());
        });
        figures.clear();
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    public void onClose() {
        this.onStopButtonClick();
    }
}