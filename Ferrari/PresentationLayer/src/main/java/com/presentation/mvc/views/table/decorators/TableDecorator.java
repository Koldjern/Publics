package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.views.table.ui.GuiTable;
//anders
//interface for decorators and concretes
public interface TableDecorator <T>{
    public GuiTable<T> getTable();
}
