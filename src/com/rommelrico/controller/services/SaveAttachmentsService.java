package com.rommelrico.controller.services;

import com.rommelrico.model.EmailMessageBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javax.mail.internet.MimeBodyPart;

public class SaveAttachmentsService extends Service<Void> {

    private String LOCATION_OF_DOWNLOADS = System.getProperty("user.home") + "/Downloads/";
    private EmailMessageBean message;
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
    public void setMessage(EmailMessageBean message) {
        this.message = message;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (MimeBodyPart mbp : message.getAttachmentList()) {
                    updateProgress(message.getAttachmentList().indexOf(mbp), message.getAttachmentList().size());
                    mbp.saveFile(LOCATION_OF_DOWNLOADS + mbp.getFileName());
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
