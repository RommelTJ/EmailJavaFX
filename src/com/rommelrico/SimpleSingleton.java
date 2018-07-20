package com.rommelrico;

public class SimpleSingleton {
    private static SimpleSingleton ourInstance = new SimpleSingleton();

    public static SimpleSingleton getInstance() {
        return ourInstance;
    }

    private SimpleSingleton() { }

    private EmailMessageBean messageBean;

    public EmailMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(EmailMessageBean messageBean) {
        this.messageBean = messageBean;
    }

}
