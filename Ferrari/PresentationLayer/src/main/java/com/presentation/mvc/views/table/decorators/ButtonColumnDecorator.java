package com.presentation.mvc.views.table.decorators;


import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.views.table.ui.GuiTable;
//anders 
//adds buttons to column cells, they dont need anything
public class ButtonColumnDecorator<T> implements TableDecorator<T> {
    private GuiTable<T> table;
    public ButtonColumnDecorator(ColumnController<T> column, TableDecorator<T> other) {
        table = other.getTable();
        table.getColumns().add(column.getView());
    }
    @Override
    public GuiTable<T> getTable() {
        return table;
    }
}
