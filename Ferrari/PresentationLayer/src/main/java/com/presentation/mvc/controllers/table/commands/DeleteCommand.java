package com.presentation.mvc.controllers.table.commands;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.tools.alert.Alerter;
//anders
// command to Delete table items
public class DeleteCommand<T> implements CellCommand<T>{
    private TableModel<T> table;

    public DeleteCommand(TableModel<T> table) {
        this.table = table;
    }
    @Override
    public void invoke(RowModel<T> caller) {
        if(Alerter.confirmation("Sletning", "Er du sikker på at du vil slette?"))
            ServiceSingleton.getInstance().query(new Request<T, Boolean>(caller.getType(), CRUDType.Delete, caller.getItem(), (wasRemoved) -> {
                if (wasRemoved) {
                    table.removeRow(caller);
                }
            }));
    }
}
