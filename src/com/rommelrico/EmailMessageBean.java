package com.rommelrico;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class EmailMessageBean {

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;

    public Map<String, Integer> formattedValues = new HashMap<String, Integer>();

    public EmailMessageBean(String subject, String sender, int size) {
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(formatSize(size));
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

    private String formatSize(int size) {
        String returnValue;

        if (size <= 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + "B";
        } else if (size < 1048576) {
            returnValue = size/1024 + " kB";
        } else {
            returnValue = size/1048576 + " MB";
        }

        formattedValues.put(returnValue, size);

        return returnValue;
    }
}
