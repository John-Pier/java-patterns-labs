package com.johnpier.lab41mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MvcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MvcApplication.class.getResource("app-view.fxml"));
        Parent root = fxmlLoader.load();
        MvcController controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 500, 450);
        stage.setTitle("MvcApplication");
        stage.setScene(scene);
        stage.show();
        controller.init();
    }

    public static void main(String[] args) {
        launch();
    }
}