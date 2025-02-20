package com.presentation.mvc.views.table.concretes;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.scene.control.TableColumn;
//anders
//mini table to show in employees open agreements, fewer values than the real table
public class MiniAgreementTable extends GuiTable<AgreementModel> implements TableDecorator<AgreementModel> {
private TableColumn<RowModel<AgreementModel>, Customer> customerCol;
private TableColumn<RowModel<AgreementModel>, Employee> employeeCol;
private TableColumn<RowModel<AgreementModel>, Vehicle> vehicleCol;

public MiniAgreementTable() {
    getColumns().add(customerCol        = new TableColumn<RowModel<AgreementModel>, Customer>("Customer"));
    getColumns().add(employeeCol        = new TableColumn<RowModel<AgreementModel>, Employee>("Employee"));
    getColumns().add(vehicleCol         = new TableColumn<RowModel<AgreementModel>, Vehicle>("Vehicle"));

    customerCol.setCellValueFactory((column)        -> column.getValue().getItem().customerProperty());
    employeeCol.setCellValueFactory((column)        -> column.getValue().getItem().employeeProperty());
    vehicleCol.setCellValueFactory((column)         -> column.getValue().getItem().vehicleProperty());

}
@Override
public GuiTable<AgreementModel> getTable() {
    return this;
    }
}

