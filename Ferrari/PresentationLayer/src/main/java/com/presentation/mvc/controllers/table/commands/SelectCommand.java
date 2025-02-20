package com.presentation.mvc.controllers.table.commands;

import com.model.threads.ActionParameter;
import com.presentation.mvc.models.table.RowModel;
//anders
// command to Select table items
//does this with interface method
public class SelectCommand<T> implements CellCommand<T> {
    private ActionParameter<RowModel<T>> action;
    public SelectCommand(ActionParameter<RowModel<T>> action) {
        this.action = action;
    }
    @Override
    public void invoke(RowModel<T> caller) {
        //not a sql action rather a gui action, such as i want this object to be the focus in a view or such
        action.action(caller);

    }
}
