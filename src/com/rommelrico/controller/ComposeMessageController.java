package com.rommelrico.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ComposeMessageController extends AbstractController implements Initializable {

    @FXML
    private Label attachmentsLabel;

    @FXML
    private ChoiceBox<?> senderChoice;

    @FXML
    private TextField recepientField;

    @FXML
    private TextField subjectField;

    @FXML
    private Label errorLabel;

    @FXML
    void attchBtnAction() {

    }

    @FXML
    void sendBtnAction() {

    }

    public ComposeMessageController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
