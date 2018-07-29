package com.rommelrico.model.folder;

import com.rommelrico.model.EmailMessageBean;
import com.rommelrico.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class EmailFolderBean<T> extends TreeItem<String> {

    private boolean topElement = false;
    private int unreadMessageCount;
    private String name;
    private String completeName;
    private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

    /**
     * Constructor for Top Elements
     * @param value String.
     */
    public EmailFolderBean(String value) {
        super(value, ViewFactory.defaultFactory.resolveIcon(value));
        this.topElement = true;
        this.name = value;
        this.completeName = value;
        this.data = null;
        this.setExpanded(true);
    }

    public EmailFolderBean(String value, String completeName) {
        super(value, ViewFactory.defaultFactory.resolveIcon(value));
        this.name = value;
        this.completeName = completeName;
    }

    private void updateValue() {
        if (unreadMessageCount > 0) {
            this.setValue(name + " (" + unreadMessageCount + ")");
        } else {
            this.setValue(name);
        }
    }

    public void incrementUnreadMessagesCount(int newMessages) {
        unreadMessageCount = unreadMessageCount + newMessages;
        updateValue();
    }

    public void decreaseUnreadMessagesCount() {
        unreadMessageCount--;
        updateValue();
    }

    public void addEmail(EmailMessageBean messageBean) {
        data.add(messageBean);
        if (messageBean.isRead()) {
            incrementUnreadMessagesCount(1);
        }
    }

    public boolean isTopElement() {
        return topElement;
    }

    public ObservableList<EmailMessageBean> getData() {
        return data;
    }

}
