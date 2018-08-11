package com.rommelrico.controller.services;

import com.rommelrico.model.EmailAccountBean;
import com.rommelrico.model.EmailConstants;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmailSenderService extends Service<Integer> {

    private int result;
    private EmailAccountBean emailAccountBean;
    private String subject;
    private String recipient;
    private String content;
    private List<File> attachments = new ArrayList<File>();

    public EmailSenderService(EmailAccountBean emailAccountBean, String subject, String recipient, String content, List<File> attachments) {
        this.emailAccountBean = emailAccountBean;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
        this.attachments = attachments;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    // Setup
                    Session session = emailAccountBean.getSession();
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(emailAccountBean.getEmailAddress());
                    message.addRecipients(Message.RecipientType.TO, recipient);
                    message.setSubject(subject);

                    // Set Content
                    Multipart multipart = new MimeMultipart();
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(content, "text/html");
                    multipart.addBodyPart(messageBodyPart);

                    // Attachments
                    if (attachments.size() > 0) {
                        for (File file : attachments) {
                            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
                            DataSource source = new FileDataSource(file.getAbsolutePath());
                            messageBodyPartAttach.setDataHandler(new DataHandler(source));
                            messageBodyPartAttach.setFileName(file.getName());
                            multipart.addBodyPart(messageBodyPartAttach);
                        }
                    }
                    message.setContent(multipart);

                    // Send Message
                    Transport transport = session.getTransport();
                    transport.connect(emailAccountBean.getProperties().getProperty("outgoingHost"),
                            emailAccountBean.getEmailAccountName(),
                            emailAccountBean.getPassword());
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                    result = EmailConstants.MESSAGE_SENT_OK;
                } catch (Exception e) {
                    e.printStackTrace();
                    result = EmailConstants.MESSAGE_SENT_ERROR;
                }
                return result;
            }
        };
    }

}
