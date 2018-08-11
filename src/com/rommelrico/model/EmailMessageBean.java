package com.rommelrico.model;

import com.rommelrico.model.table.AbstractTableItem;
import com.rommelrico.model.table.FormattableInteger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailMessageBean extends AbstractTableItem {

    private SimpleStringProperty sender;
    private SimpleObjectProperty<Date> date;
    private SimpleStringProperty subject;
    private SimpleObjectProperty<FormattableInteger> size;
    private Message messageReference;

    // Attachment properties
    private List<MimeBodyPart> attachmentList = new ArrayList<MimeBodyPart>();
    private StringBuffer attachmentNames = new StringBuffer();

    public EmailMessageBean(String Subject, String Sender, int size, Date date, Message messageReference, boolean isRead) {
        super(isRead);
        this.subject = new SimpleStringProperty(Subject);
        this.sender = new SimpleStringProperty(Sender);
        this.size = new SimpleObjectProperty<>(new FormattableInteger(size));
        this.date = new SimpleObjectProperty<>(date);
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

    public FormattableInteger getSize() {
        return size.get();
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

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
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

    @Override
    public String toString() {
        return "EmailMessageBean{" +
                "sender=" + sender +
                ", subject=" + subject +
                ", size=" + size +
                '}';
    }
}
