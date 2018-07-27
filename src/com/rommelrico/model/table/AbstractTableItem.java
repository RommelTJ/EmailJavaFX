package com.rommelrico.model.table;

import javafx.beans.property.SimpleBooleanProperty;

public abstract class AbstractTableItem {

    private final SimpleBooleanProperty read = new SimpleBooleanProperty();

    public boolean isRead() {
        return read.get();
    }

    public SimpleBooleanProperty readProperty() {
        return read;
    }

    public void setRead(boolean read) {
        this.read.set(read);
    }

}
