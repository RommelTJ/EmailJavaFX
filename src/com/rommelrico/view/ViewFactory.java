package com.rommelrico.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public Node resolveIcon(String treeItemValue) {
        String lowerCaseTreeItemValue = treeItemValue.toLowerCase();
        ImageView returnIcon;

        try {
            if (lowerCaseTreeItemValue.contains("inbox")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/inbox.png")));
            } else if (lowerCaseTreeItemValue.contains("sent")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/sent.png")));
            } else if (lowerCaseTreeItemValue.contains("spam")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/spam.png")));
            } else if (lowerCaseTreeItemValue.contains("@")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/email.png")));
            } else {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("images/folder.png")));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            returnIcon = new ImageView();
        }

        // Make icons smaller
        returnIcon.setFitWidth(16);
        returnIcon.setFitHeight(16);

        return returnIcon;
    }
}
