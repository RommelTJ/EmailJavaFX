package com.rommelrico.controller;

import com.rommelrico.model.EmailAccountBean;
import com.rommelrico.model.EmailMessageBean;
import com.rommelrico.model.folder.EmailFolderBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.Folder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelAccess {

    private Map<String, EmailAccountBean> emailAccounts = new HashMap<>();
    private ObservableList<String> emailAccountsNames = FXCollections.observableArrayList();

    private EmailMessageBean selectedMessage;
    private EmailFolderBean<String> selectedFolder;
    private List<Folder> folderList = new ArrayList<>();

    public EmailMessageBean getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(EmailMessageBean selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public EmailFolderBean<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailFolderBean<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public void addFolder(Folder folder) {
        folderList.add(folder);
    }

    public ObservableList<String> getEmailAccountsNames() {
        return emailAccountsNames;
    }

    public EmailAccountBean getEmailAccountByName(String name) {
        return emailAccounts.get(name);
    }

    public void addAccount(EmailAccountBean account) {
        emailAccounts.put(account.getEmailAddress(), account);
        emailAccountsNames.add(account.getEmailAddress());
    }
    
}
