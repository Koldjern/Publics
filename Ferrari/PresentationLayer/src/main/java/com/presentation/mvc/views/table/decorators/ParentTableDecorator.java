package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.table.ui.GuiTable;
// anders
//parenttable sets the style as such and gets the objects from tablemodel 
public class ParentTableDecorator <T> implements TableDecorator <T> {
    private GuiTable<T> table;
    public ParentTableDecorator(TableModel<T> model, TableDecorator<T> other) {
        table = other.getTable();
        table.getStyleClass().add("bigTable");
        table.setItems(model.getRows());
    }
    @Override
    public GuiTable<T> getTable() {
        return table;
    }
}