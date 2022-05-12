package com.example.lab38templatemethod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TemplateMethodApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TemplateMethodApplication.class.getResource("template-method-view.fxml"));
        Parent root = fxmlLoader.load();
        TemplateMethodController controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 500, 450);
        stage.setTitle("Template Method App");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> controller.onClose());
    }

    public static void main(String[] args) {
        launch();
    }
}