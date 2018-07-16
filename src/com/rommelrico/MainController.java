package com.rommelrico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private Button button1;

    @FXML
    private WebView messageRendererId;

    @FXML
    private TableColumn<?, ?> subjectCol;

    @FXML
    private TableColumn<?, ?> senderCol;

    @FXML
    private TableColumn<?, ?> sizeCol;


    @FXML
    void button1Action(ActionEvent event) {
        System.out.println("Pushed button1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageRendererId.getEngine().loadContent("<html>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.</html>");
    }
}
