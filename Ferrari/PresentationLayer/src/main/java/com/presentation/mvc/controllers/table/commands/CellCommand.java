package com.presentation.mvc.controllers.table.commands;

import com.presentation.mvc.models.table.RowModel;
//anders
@FunctionalInterface
public interface CellCommand<T> {
    public void invoke(RowModel<T> caller);
}
