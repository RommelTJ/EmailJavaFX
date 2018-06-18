package com.rommelrico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private Button button1;

    @FXML
    private WebView messageRendererId;


    @FXML
    void button1Action(ActionEvent event) {
        System.out.println("Pushed button1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Document loaded.");
    }
}
