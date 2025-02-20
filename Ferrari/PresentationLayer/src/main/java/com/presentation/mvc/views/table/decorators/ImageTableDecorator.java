package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.factories.ImageFactory;
import com.presentation.mvc.views.table.ui.GuiTable;
//anders
//sets images in the column, factory to help since there could be more than 1 image column
public class ImageTableDecorator<T> implements TableDecorator<T> {
    private GuiTable<T> table;
    public ImageTableDecorator(TableDecorator<T> other, ImageFactory<T> factory) {
        table = other.getTable();
        ColumnController<T> controller = new ColumnController<>(factory, "Billede");
        table.getColumns().add(controller.getView());
    }
    @Override
    public GuiTable<T> getTable() {
        return table;
    }
}
