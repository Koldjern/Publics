package com.presentation.mvc.controllers.table.commands;

import com.presentation.mvc.controllers.modals.ModalCreator;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.tools.facade.Facade;
//anders
// command to update table items
public class UpdateCommand<T> implements CellCommand<T> {
    private ModalCreator<T> factory;
    public UpdateCommand(ModalCreator<T> factory) {
        this.factory = factory;
    }
    @Override
    public void invoke(RowModel<T> caller) {
        Facade.getInstance().openModal(factory.makeController(caller));
    }
}
