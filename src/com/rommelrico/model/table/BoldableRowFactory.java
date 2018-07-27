package com.rommelrico.model.table;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableRow;

public class BoldableRowFactory<T extends AbstractTableItem> extends TableRow<T> {

    private final SimpleBooleanProperty bold = new SimpleBooleanProperty();
    private T currentItem = null;

    public BoldableRowFactory() {
        super();
        bold.addListener((ObservableValue<? extends  Boolean> observableValue, Boolean oldValue, Boolean newValue) -> {
            if (currentItem != null && currentItem == getItem()) {
                updateItem(getItem(), isEmpty());
            }
        });
        itemProperty().addListener((ObservableValue<? extends  T> observableValue, T oldValue, T newValue) -> {
            bold.unbind();
            if (newValue != null) {
                bold.bind(newValue.readProperty());
                currentItem = newValue;
            }
        });
    }

    @Override
    final protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !item.isRead()) {
            setStyle("-fx-font-weight: bold");
        } else {
            setStyle("");
        }
    }

}
