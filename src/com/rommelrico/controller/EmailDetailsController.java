package com.rommelrico.controller;

import com.rommelrico.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.naming.OperationNotSupportedException;
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
        subjectLabel.setText("Subject: " + getModelAccess().getSelectedMessage().getSubject());
        senderLabel.setText("Sender: " + getModelAccess().getSelectedMessage().getSender());
        webView.getEngine().loadContent(getModelAccess().getSelectedMessage().getContent());
    }

    @FXML
    void illegalOperationAction(ActionEvent event) throws OperationNotSupportedException {
        ViewFactory viewFactory = new ViewFactory();
        Scene mainScene = viewFactory.getMainScene();
        Stage stage = new Stage();
        stage.setScene(mainScene);
        stage.show();
    }

}
