package com.rommelrico.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ComposeMessageController extends AbstractController implements Initializable {

    private List<File> attachments = new ArrayList<>();


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
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            attachments.add(selectedFile);
            attachmentsLabel.setText(attachmentsLabel.getText() + selectedFile.getName() + "; ");
        }

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
