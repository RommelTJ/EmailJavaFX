package com.rommelrico.controller;

import com.rommelrico.model.EmailMessageBean;
import com.rommelrico.model.folder.EmailFolderBean;

public class ModelAccess {

    private EmailMessageBean selectedMessage;
    private EmailFolderBean<String> selectedFolder;

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

}
