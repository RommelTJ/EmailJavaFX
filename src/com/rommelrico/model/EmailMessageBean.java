package com.rommelrico.model;

import com.rommelrico.model.table.AbstractTableItem;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class EmailMessageBean extends AbstractTableItem {

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;
    private String content;

    public static Map<String, Integer> formattedValues = new HashMap<String, Integer>();

    public EmailMessageBean(String Subject, String Sender, int size, String Content, boolean isRead) {
        super(isRead);
        this.subject = new SimpleStringProperty(Subject);
        this.sender = new SimpleStringProperty(Sender);
        this.size = new SimpleStringProperty(formatSize(size));
        this.content = Content;
    }

    public String getSender() {
        return sender.get();
    }

    public SimpleStringProperty senderProperty() {
        return sender;
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public String getContent(){
        return content;
    }

    private String formatSize(int size) {
        String returnValue;

        if (size <= 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
        } else if (size < 1048576) {
            returnValue = size/1024 + " kB";
        } else {
            returnValue = size/1048576 + " MB";
        }

        formattedValues.put(returnValue, size);

        return returnValue;
    }
}
