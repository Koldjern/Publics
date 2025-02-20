package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.views.table.ui.GuiTable;
//anders
//sets columns view as gui and that is gonna be a child table, in each row with a item
public class ChildTableDecorator<T> implements TableDecorator<T> {
    private GuiTable<T> table;
    public ChildTableDecorator(ColumnController<T> column, TableDecorator<T> other) {
        table = other.getTable();
        table.getColumns().add(column.getView());
    }
    @Override
    public GuiTable<T> getTable() {
        return table;
    }
}