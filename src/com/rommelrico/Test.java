package com.rommelrico;

import com.rommelrico.model.EmailAccountBean;
import com.rommelrico.model.EmailMessageBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Test {

    public static void main(String[] args) {
        EmailAccountBean emailAccountBean = new EmailAccountBean("myemail", "REDACTED");

        ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();
        emailAccountBean.addEmailsToData(data);
    }

}
