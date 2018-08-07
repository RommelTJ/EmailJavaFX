package com.rommelrico.model;

import com.rommelrico.model.table.AbstractTableItem;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailMessageBean extends AbstractTableItem {

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;
    private Message messageReference;

    // Attachment properties
    private List<MimeBodyPart> attachmentList = new ArrayList<MimeBodyPart>();
    private StringBuffer attachmentNames = new StringBuffer();

    public static Map<String, Integer> formattedValues = new HashMap<String, Integer>();

    public EmailMessageBean(String Subject, String Sender, int size, Message messageReference, boolean isRead) {
        super(isRead);
        this.subject = new SimpleStringProperty(Subject);
        this.sender = new SimpleStringProperty(Sender);
        this.size = new SimpleStringProperty(formatSize(size));
        this.messageReference = messageReference;
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

    public Message getMessageReference() {
        return messageReference;
    }

    public List<MimeBodyPart> getAttachmentList() {
        return attachmentList;
    }

    public String getAttachmentNames() {
        return attachmentNames.toString();
    }

    public void addAttachment(MimeBodyPart mbp) {
        attachmentList.add(mbp);
        try {
            attachmentNames.append(mbp.getFileName() + "; ");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean hasAttachments() {
        return attachmentList.size() > 0;
    }

    public void clearAttachments() {
        attachmentList.clear();
        attachmentNames.setLength(0);
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

    @Override
    public String toString() {
        return "EmailMessageBean{" +
                "sender=" + sender +
                ", subject=" + subject +
                ", size=" + size +
                '}';
    }
}
