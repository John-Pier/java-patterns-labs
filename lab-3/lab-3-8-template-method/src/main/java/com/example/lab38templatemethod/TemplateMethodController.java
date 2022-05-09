package com.example.lab38templatemethod;

import com.example.lab38templatemethod.models.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TemplateMethodController {
    @FXML
    private Circle circle;
    @FXML
    private Rectangle square;
    @FXML
    private Pane polygon;
    @FXML
    private Pane mainPane;

    private final ExecutorService executorService = Executors.newFixedThreadPool(15);

    private final ArrayList<AbstractFigure> figures = new ArrayList<>();

    @FXML
    protected void onStartButtonClick() {
        var figure = new SquareFigure(mainPane);
        mainPane.getChildren().add(figure.getElement());
        figures.add(figure);
        executorService.execute(figure::start);
    }

    @FXML
    protected void onStopButtonClick() {
        figures.forEach(figure -> {
            figure.stop();
            this.mainPane.getChildren().remove(figure.getElement());
        });
        figures.clear();
    }


//    public void run() {
//        timer = new Thread(() -> {
//            while (!isStopped || !timer.isInterrupted()) {
//                move();
//                try {
//                    Thread.sleep(25);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    this.isStopped = true;
//                }
//            }
//        });
//        timer.start();
//    }

//    private void move() {
//        this.figures.forEach(AbstractFigure::next);
//    }

    public void onClose() {
        this.onStopButtonClick();
//        timer.interrupt();
    }
}