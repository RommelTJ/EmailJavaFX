package com.rommelrico.view;

import com.rommelrico.controller.AbstractController;
import com.rommelrico.controller.EmailDetailsController;
import com.rommelrico.controller.MainController;
import com.rommelrico.controller.ModelAccess;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewFactory {

    private ModelAccess modelAccess = new ModelAccess();
    private MainController mainController;
    private EmailDetailsController emailDetailsController;

    private final String DEFAULT_CSS = "style.css";

    public Scene getMainScene() {
        mainController = new MainController(modelAccess);
        return initializeScene("MainLayout.fxml", mainController);
    }

    public Scene getEmailDetailsScene() {
        emailDetailsController = new EmailDetailsController(modelAccess);
        return initializeScene("EmailDetailsLayout.fxml", emailDetailsController);
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

    private Scene initializeScene(String fxmlPath, AbstractController controller) {
        FXMLLoader loader;
        Parent parent;
        Scene scene;

        try {
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(controller);
            parent = loader.load();
        } catch (Exception e) {
            return null;
        }

        scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_CSS).toExternalForm());
        return scene;
    }

}
