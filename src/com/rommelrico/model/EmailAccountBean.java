package com.rommelrico.model;

import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.Properties;

public class EmailAccountBean {

    private String emailAddress;
    private String password;
    private Properties properties;

    private Store store;
    private Session session;
    private int loginState = EmailConstants.LOGIN_STATE_NOT_READY;

    // Constructor
    public EmailAccountBean(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;

        properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.webfaction.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("incomingHost", "mail.webfaction.com");
        properties.put("outgoingHost", "smtp.webfaction.com");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        };

        // Connecting
        session = Session.getInstance(properties, auth);
        try {
            this.store = session.getStore();
            store.connect(properties.getProperty("incomingHost"), emailAddress, password);
            System.out.println("EmailAccountBean constructed successfully!");
            loginState = EmailConstants.LOGIN_STATE_SUCCEEDED;
        } catch (Exception e) {
            e.printStackTrace();
            loginState = EmailConstants.LOGIN_STATE_FAILED_BY_CREDENTIALS;
        }
    } // end EmailAccountBean constructor.

    // Testing
    public void addEmailsToData(ObservableList<EmailMessageBean> data) {
        try {
            Folder folder = store.getFolder("Home");
            folder.open(Folder.READ_ONLY);
            for (int i = folder.getMessageCount(); i > 0; i--) {
                Message message = folder.getMessage(i);
                EmailMessageBean email = new EmailMessageBean(
                        message.getSubject(),
                        message.getFrom()[0].toString(),
                        message.getSize(),
                        "",
                        message.getFlags().contains(Flags.Flag.SEEN));
                System.out.println("Got: " + email);
                data.add(email);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Properties getProperties() {
        return properties;
    }

    public Store getStore() {
        return store;
    }

    public Session getSession() {
        return session;
    }

    public int getLoginState() {
        return loginState;
    }

} // end EmailAccountBean
