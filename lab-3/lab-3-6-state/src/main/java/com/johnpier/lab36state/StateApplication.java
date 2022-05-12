package com.johnpier.lab36state;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StateApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StateApplication.class.getResource("state-view.fxml"));
        Parent parent = fxmlLoader.load();
        StateController controller = fxmlLoader.getController();
        Scene scene = new Scene(parent, 500, 500);
        stage.setTitle("State App");
        stage.setScene(scene);
        stage.setOnShown(e -> controller.init());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}