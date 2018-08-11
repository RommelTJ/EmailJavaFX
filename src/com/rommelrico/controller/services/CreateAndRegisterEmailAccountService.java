package com.rommelrico.controller.services;

import com.rommelrico.controller.ModelAccess;
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
    private ModelAccess modelAccess;

    public CreateAndRegisterEmailAccountService(String emailAddress,
                                                String emailAccountName,
                                                String emailAccountPassword,
                                                EmailFolderBean<String> folderRoot,
                                                ModelAccess modelAccess) {
        this.emailAddress = emailAddress;
        this.emailAccountName = emailAccountName;
        this.emailAccountPassword = emailAccountPassword;
        this.folderRoot = folderRoot;
        this.modelAccess = modelAccess;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                EmailAccountBean emailAccountBean = new EmailAccountBean(emailAddress, emailAccountName, emailAccountPassword);
                if (emailAccountBean.getLoginState() == EmailConstants.LOGIN_STATE_SUCCEEDED) {
                    modelAccess.addAccount(emailAccountBean);
                    EmailFolderBean<String> emailFolderBean = new EmailFolderBean<>(emailAddress);
                    folderRoot.getChildren().add(emailFolderBean);
                    FetchFoldersService fetchFoldersService = new FetchFoldersService(emailFolderBean, emailAccountBean, modelAccess);
                    fetchFoldersService.start();
                }
                return emailAccountBean.getLoginState();
            }
        };
    }
}
