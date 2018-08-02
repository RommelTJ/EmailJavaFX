package com.rommelrico.controller.services;

import com.rommelrico.model.EmailAccountBean;
import com.rommelrico.model.EmailConstants;
import com.rommelrico.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CreateAndRegisterEmailAccountService extends Service<Integer> {

    private String emailAddress;
    private String emailAccountName;
    private String emailAccountPassword;
    private EmailFolderBean<String> folderRoot;

    public CreateAndRegisterEmailAccountService(String emailAddress, String emailAccountName, String emailAccountPassword, EmailFolderBean<String> folderRoot) {
        this.emailAddress = emailAddress;
        this.emailAccountName = emailAccountName;
        this.emailAccountPassword = emailAccountPassword;
        this.folderRoot = folderRoot;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                EmailAccountBean emailAccountBean = new EmailAccountBean(emailAccountName, emailAccountPassword);
                if (emailAccountBean.getLoginState() == EmailConstants.LOGIN_STATE_SUCCEEDED) {
                    EmailFolderBean<String> emailFolderBean = new EmailFolderBean<>(emailAddress);
                    folderRoot.getChildren().add(emailFolderBean);
                    FetchFoldersService fetchFoldersService = new FetchFoldersService(emailFolderBean, emailAccountBean);
                    fetchFoldersService.start();
                }
                return emailAccountBean.getLoginState();
            }
        };
    }
}
