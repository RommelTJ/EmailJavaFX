package com.rommelrico.controller.services;

import com.rommelrico.controller.ModelAccess;
import com.rommelrico.model.EmailAccountBean;
import com.rommelrico.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;

public class FetchFoldersService extends Service<Void> {

    private EmailFolderBean<String> foldersRoot;
    private EmailAccountBean emailAccountBean;
    private ModelAccess modelAccess;
    private static int NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE = 0;

    public FetchFoldersService(EmailFolderBean<String> foldersRoot,
                               EmailAccountBean emailAccountBean,
                               ModelAccess modelAccess) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
        this.modelAccess = modelAccess;

        this.setOnSucceeded(e -> {
            NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE--;
        });
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE++;
                if (emailAccountBean != null) {
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();
                    for (Folder folder : folders) {
                        modelAccess.addFolder(folder);
                        EmailFolderBean<String> item = new EmailFolderBean<>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);
                        item.setExpanded(true);
                        addMessageListenerToFolder(folder, item);

                        FetchMessagesOnFolderService fetchMessagesOnFolderService =
                                new FetchMessagesOnFolderService(item, folder);
                        fetchMessagesOnFolderService.start();

                        Folder[] subFolders = folder.list();
                        for (Folder subFolder : subFolders) {
                            modelAccess.addFolder(subFolder);
                            EmailFolderBean<String> subItem = new EmailFolderBean<>(subFolder.getName(), subFolder.getFullName());
                            item.getChildren().add(subItem);
                            addMessageListenerToFolder(subFolder, subItem);

                            FetchMessagesOnFolderService fetchMessagesOnSubfolderService =
                                    new FetchMessagesOnFolderService(subItem, subFolder);
                            fetchMessagesOnSubfolderService.start();
                        }
                    }
                }
                return null;
            }
        };
    }

    private void addMessageListenerToFolder(Folder folder, EmailFolderBean<String> item) {
        folder.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                super.messagesAdded(e);
                for (int i = 0; i < e.getMessages().length; i++) {
                    Message currentMessage = null;
                    try {
                        currentMessage = folder.getMessage(folder.getMessageCount() - i);
                        item.addEmail(0, currentMessage);
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public static boolean noServicesActive() {
        return NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE == 0;
    }

}
