package com.rommelrico.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsController extends AbstractController implements Initializable {

    @FXML
    private WebView webView;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;

    public EmailDetailsController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simpleSingleton = SimpleSingleton.getInstance();

        subjectLabel.setText("Subject: " + simpleSingleton.getMessageBean().getSubject());
        senderLabel.setText("Sender: " + simpleSingleton.getMessageBean().getSender());

        webView.getEngine().loadContent(simpleSingleton.getMessageBean().getContent());
    }

}
