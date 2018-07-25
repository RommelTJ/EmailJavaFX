package com.rommelrico.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewFactory {

    public Scene getMainScene() {
        Pane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
        } catch (IOException e) {
            pane = new Pane();
        }

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return scene;
    }

    public Scene getEmailDetailsScene() {
        Pane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("EmailDetailsLayout.fxml"));
        } catch (IOException e) {
            pane = new Pane();
        }

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return scene;
    }
}
