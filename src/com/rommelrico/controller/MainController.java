package com.rommelrico.controller;

import com.rommelrico.controller.services.CreateAndRegisterEmailAccountService;
import com.rommelrico.model.EmailAccountBean;
import com.rommelrico.model.EmailMessageBean;
import com.rommelrico.model.folder.EmailFolderBean;
import com.rommelrico.model.table.BoldableRowFactory;
import com.rommelrico.view.ViewFactory;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    void button1Action(ActionEvent event) { }

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

        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService1 =
                new CreateAndRegisterEmailAccountService("me@rommelrico.com", "myemail", "REDACTED", root);
        createAndRegisterEmailAccountService1.start();

        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService2 =
                new CreateAndRegisterEmailAccountService("rommel@romzalabs.com", "romzalabs", "REDACTED", root);
        createAndRegisterEmailAccountService2.start();

        emailTableView.setContextMenu(new ContextMenu(showDetails));

        emailFoldersTree.setOnMouseClicked(e -> {
            EmailFolderBean<String> item = (EmailFolderBean<String>) emailFoldersTree.getSelectionModel().getSelectedItem();
            if (item != null && !item.isTopElement()) {
                emailTableView.setItems(item.getData());
                getModelAccess().setSelectedFolder(item);

                // Clear the selected message
                getModelAccess().setSelectedMessage(null);
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
            EmailFolderBean<String> selectedFolder = getModelAccess().getSelectedFolder();
            if (selectedFolder != null) {
                if (value) {
                    selectedFolder.incrementUnreadMessagesCount(1);
                } else {
                    selectedFolder.decreaseUnreadMessagesCount();
                }
            }
        }
    }

}
