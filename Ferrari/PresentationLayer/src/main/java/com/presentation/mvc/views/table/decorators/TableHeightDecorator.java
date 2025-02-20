package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.views.table.ui.GuiTable;
import com.presentation.tools.ScreenWatcher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Screen;
//anders
//sets the table height to be a percent of screen height
public class TableHeightDecorator<T> implements TableDecorator<T> {
    private GuiTable<T> table;
    public TableHeightDecorator(double decimal, TableDecorator<T> other) {
        this.table = other.getTable();
        this.table.setPrefHeight(ScreenWatcher.getInstance().getScreenHeightWithDecimal(decimal));
        ScreenWatcher.getInstance().getScreenProperty().addListener(new ChangeListener<Screen>() {
            @Override
            public void changed(ObservableValue<? extends Screen> observable, Screen oldValue, Screen newValue) {
                table.setPrefHeight(newValue.getBounds().getHeight() * decimal);
            }
        });
    }
    @Override
    public GuiTable<T> getTable() {
        return table;
    }
}
