package com.rommelrico.controller;

import com.rommelrico.model.EmailMessageBean;
import com.rommelrico.model.SampleData;
import com.rommelrico.model.SimpleSingleton;
import com.rommelrico.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private WebView messageRendererId;

    @FXML
    private TreeView<String> emailFoldersTree;
    private TreeItem<String> root = new TreeItem<String>();
    private SampleData sampleData = new SampleData();
    private MenuItem showDetails = new MenuItem("show details");
    private SimpleSingleton simpleSingleton;

    @FXML
    private TableView<EmailMessageBean> emailTableView;

    @FXML
    private TableColumn<EmailMessageBean, String> subjectCol;

    @FXML
    private TableColumn<EmailMessageBean, String> senderCol;

    @FXML
    private TableColumn<EmailMessageBean, String> sizeCol;

    @FXML
    void button1Action(ActionEvent event) {
        System.out.println("Pushed button1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ViewFactory viewFactory = new ViewFactory();
        simpleSingleton = SimpleSingleton.getInstance();

        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean, String>("size"));

        sizeCol.setComparator(new Comparator<String>() {

            Integer int1;
            Integer int2;

            @Override
            public int compare(String o1, String o2) {
                int1 = EmailMessageBean.formattedValues.get(o1);
                int2 = EmailMessageBean.formattedValues.get(o2);
                return int1.compareTo(int2);
            }
        });

        emailFoldersTree.setRoot(root);
        root.setValue("example@rommelrico.com");
        root.setGraphic(viewFactory.resolveIcon(root.getValue()));

        TreeItem<String> inbox = new TreeItem<String>("Inbox", viewFactory.resolveIcon("Inbox"));
        TreeItem<String> sent = new TreeItem<String>("Sent", viewFactory.resolveIcon("Sent"));
        TreeItem<String> subItem1 = new TreeItem<String>("SubItem1", viewFactory.resolveIcon("SubItem1"));
        TreeItem<String> subItem2 = new TreeItem<String>("SubItem2", viewFactory.resolveIcon("SubItem2"));
        sent.getChildren().addAll(subItem1, subItem2);
        TreeItem<String> spam = new TreeItem<String>("Spam", viewFactory.resolveIcon("Spam"));
        TreeItem<String> trash = new TreeItem<String>("Trash", viewFactory.resolveIcon("Trash"));

        root.getChildren().addAll(inbox, sent, spam, trash);
        root.setExpanded(true);

        emailTableView.setContextMenu(new ContextMenu(showDetails));

        emailFoldersTree.setOnMouseClicked(e -> {
            TreeItem<String> item = emailFoldersTree.getSelectionModel().getSelectedItem();
            if (item != null) {
                emailTableView.setItems(sampleData.emailFolders.get(item.getValue()));
            }
        });

        emailTableView.setOnMouseClicked(e -> {
            EmailMessageBean message = emailTableView.getSelectionModel().getSelectedItem();
            if (message != null) {
                simpleSingleton.setMessageBean(message);
                messageRendererId.getEngine().loadContent(message.getContent());
            }
        });

        showDetails.setOnAction(e -> {
            Scene scene = viewFactory.getEmailDetailsScene();

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

    }

}
