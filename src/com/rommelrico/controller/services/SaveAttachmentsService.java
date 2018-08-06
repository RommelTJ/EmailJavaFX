package com.rommelrico.controller.services;

import com.rommelrico.model.EmailMessageBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class SaveAttachmentsService extends Service<Void> {

    private String LOCATION_OF_DOWNLOADS = System.getProperty("user.home") + "/Downloads/";
    private EmailMessageBean message;
    private ProgressBar progress;
    private Label label;

    public SaveAttachmentsService(ProgressBar progress, Label label) {
        this.progress = progress;
        this.label = label;
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
                return null;
            }
        };
    }
}
