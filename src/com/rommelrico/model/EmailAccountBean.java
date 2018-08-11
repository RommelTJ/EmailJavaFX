package com.rommelrico.model;

import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.Properties;

public class EmailAccountBean {

    private String emailAccountName;
    private String emailAddress;
    private String password;
    private Properties properties;

    private Store store;
    private Session session;
    private int loginState = EmailConstants.LOGIN_STATE_NOT_READY;

    // Constructor
    public EmailAccountBean(String emailAddress, String emailAccountName, String password) {
        this.emailAddress = emailAddress;
        this.emailAccountName = emailAccountName;
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
            store.connect(properties.getProperty("incomingHost"), emailAccountName, password);
            loginState = EmailConstants.LOGIN_STATE_SUCCEEDED;
        } catch (Exception e) {
            e.printStackTrace();
            loginState = EmailConstants.LOGIN_STATE_FAILED_BY_CREDENTIALS;
        }
    } // end EmailAccountBean constructor.

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmailAccountName() {
        return emailAccountName;
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

    public String getPassword() {
        return password;
    }

} // end EmailAccountBean
