package com.presentation.mvc.views.table.concretes;
import com.model.entities.City;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
//magnus
public class CustomerTable extends GuiTable<CustomerModel> implements TableDecorator<CustomerModel> {
    private TableColumn<RowModel<CustomerModel>, String> nameCol;
    private TableColumn<RowModel<CustomerModel>, String> phoneCol;
    private TableColumn<RowModel<CustomerModel>, String> emailCol;
    private TableColumn<RowModel<CustomerModel>, String> addressCol;
    private TableColumn<RowModel<CustomerModel>, City> cityZipCol;
    private TableColumn<RowModel<CustomerModel>, String> cprCol;

public CustomerTable() {
    getColumns().add(nameCol = new TableColumn<>("Navn"));
    getColumns().add(phoneCol = new TableColumn<>("Tlf Nr"));
    getColumns().add(emailCol = new TableColumn<>("Email"));
    getColumns().add(addressCol = new TableColumn<>("Adresse"));
    getColumns().add(cityZipCol = new TableColumn<>("Postnr"));
    getColumns().add(cprCol = new TableColumn<>("Cpr Nr"));

    nameCol.setCellValueFactory(cellData -> cellData.getValue().getItem().nameProperty());
    nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
    phoneCol.setCellValueFactory(cellData -> cellData.getValue().getItem().phoneNumberProperty());
    emailCol.setCellValueFactory(cellData -> cellData.getValue().getItem().emailProperty());
    addressCol.setCellValueFactory(cellData -> cellData.getValue().getItem().addressProperty());
    cityZipCol.setCellValueFactory(cellData -> cellData.getValue().getItem().cityProperty());
    cprCol.setCellValueFactory(cellData -> cellData.getValue().getItem().CprProperty());
}
    @Override
    public GuiTable<CustomerModel> getTable() {
        return this;
    }
   }