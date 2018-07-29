package com.rommelrico.controller;

import com.rommelrico.model.EmailMessageBean;
import com.rommelrico.model.SampleData;
import com.rommelrico.model.folder.EmailFolderBean;
import com.rommelrico.model.table.BoldableRowFactory;
import com.rommelrico.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController extends AbstractController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private WebView messageRendererId;

    @FXML
    private TreeView<String> emailFoldersTree;
    private SampleData sampleData = new SampleData();
    private MenuItem showDetails = new MenuItem("show details");

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

    public MainController(ModelAccess modelAccess) {
        super(modelAccess);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailTableView.setRowFactory(e -> new BoldableRowFactory<>());
        ViewFactory viewFactory = ViewFactory.defaultFactory;

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

        EmailFolderBean<String> root = new EmailFolderBean<>("");
        emailFoldersTree.setRoot(root);
        emailFoldersTree.setShowRoot(false);

        EmailFolderBean<String> rommelAccount = new EmailFolderBean<>("me@rommelrico.com");
        root.getChildren().add(rommelAccount);

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
                getModelAccess().setSelectedMessage(message);
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

    @FXML
    void changeReadAction(ActionEvent event) {
        EmailMessageBean messageBean = getModelAccess().getSelectedMessage();
        if (messageBean != null) {
            boolean value = messageBean.isRead();
            messageBean.setRead(!value);
        }
    }

}
