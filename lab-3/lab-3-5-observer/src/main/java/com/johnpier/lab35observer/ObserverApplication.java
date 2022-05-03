package com.johnpier.lab35observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ObserverApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ObserverApplication.class.getResource("observer-view.fxml"));
        Parent parent = fxmlLoader.load();
        ObserverController controller = fxmlLoader.getController();

        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("ObserverApp");
        stage.setScene(scene);
        stage.setOnShown(e -> controller.initObservers());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}