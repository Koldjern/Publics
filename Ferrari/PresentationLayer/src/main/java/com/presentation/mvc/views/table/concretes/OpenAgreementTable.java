package com.presentation.mvc.views.table.concretes;
import java.sql.Date;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.rki.rki.Rating;

import javafx.scene.control.TableColumn;
//karl 
//OpenAgreementTable class that extends GuiTable and implements TableDecorator that displays the columns of the table
public class OpenAgreementTable extends GuiTable<AgreementModel> implements TableDecorator<AgreementModel> {
private TableColumn<RowModel<AgreementModel>, Number> fixedTermsCol;
private TableColumn<RowModel<AgreementModel>, Number> startValueCol;
private TableColumn<RowModel<AgreementModel>, Date> startAgreementCol;
private TableColumn<RowModel<AgreementModel>, Rating> RKiCol;
private TableColumn<RowModel<AgreementModel>, Customer> customerCol;
private TableColumn<RowModel<AgreementModel>, Employee> employeeCol;
private TableColumn<RowModel<AgreementModel>, Vehicle> vehicleCol;
//constructor for table that sets the columns and names
public OpenAgreementTable() {
    getColumns().add(fixedTermsCol      = new TableColumn<RowModel<AgreementModel>, Number>("Fixed Terms"));
    getColumns().add(startValueCol      = new TableColumn<RowModel<AgreementModel>, Number>("Start Value"));
    getColumns().add(startAgreementCol  = new TableColumn<RowModel<AgreementModel>, Date>("Start Agreement"));
    getColumns().add(RKiCol             = new TableColumn<RowModel<AgreementModel>, Rating>("RKi"));
    getColumns().add(customerCol        = new TableColumn<RowModel<AgreementModel>, Customer>("Customer"));
    getColumns().add(employeeCol        = new TableColumn<RowModel<AgreementModel>, Employee>("Employee"));
    getColumns().add(vehicleCol         = new TableColumn<RowModel<AgreementModel>, Vehicle>("Vehicle"));
    //here we set the cell value factory for each column and how to get the value
    fixedTermsCol.setCellValueFactory((column)      -> column.getValue().getItem().fixedTermsProperty());
    startValueCol.setCellValueFactory((column)      -> column.getValue().getItem().startValueProperty());
    startAgreementCol.setCellValueFactory((column)  -> column.getValue().getItem().startAgreementProperty());
    RKiCol.setCellValueFactory((column)             -> column.getValue().getItem().RKiProperty());
    customerCol.setCellValueFactory((column)        -> column.getValue().getItem().customerProperty());
    employeeCol.setCellValueFactory((column)        -> column.getValue().getItem().employeeProperty());
    vehicleCol.setCellValueFactory((column)         -> column.getValue().getItem().vehicleProperty());

        }
@Override
public GuiTable<AgreementModel> getTable() {
    return this;
    }
}

