package com.rommelrico.controller.services;

import com.rommelrico.model.EmailMessageBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javax.mail.internet.MimeBodyPart;

public class SaveAttachmentsService extends Service<Void> {

    private String LOCATION_OF_DOWNLOADS = System.getProperty("user.home") + "/Downloads/";
    private EmailMessageBean messageToDownload;
    private ProgressBar progress;
    private Label label;

    public SaveAttachmentsService(ProgressBar progress, Label label) {
        this.progress = progress;
        this.label = label;

        this.setOnRunning(e -> {
            showVisuals(true);
        });

        this.setOnSucceeded(e -> {
            showVisuals(false);
        });
    }

    // Always call before starting.
    public void setMessageToDownload(EmailMessageBean messageToDownload) {
        this.messageToDownload = messageToDownload;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                try {
                    for(MimeBodyPart mbp: messageToDownload.getAttachmentList()){
                        updateProgress(messageToDownload.getAttachmentList().indexOf(mbp),
                                messageToDownload.getAttachmentList().size());
                        mbp.saveFile(LOCATION_OF_DOWNLOADS + mbp.getFileName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    private void showVisuals(boolean show) {
        progress.setVisible(show);
        label.setVisible(show);
    }

}
