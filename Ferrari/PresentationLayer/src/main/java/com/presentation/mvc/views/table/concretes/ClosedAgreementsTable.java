package com.presentation.mvc.views.table.concretes;

import java.sql.Date;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.table.CurrencyCell;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.rki.rki.Rating;
import javafx.scene.control.TableColumn;
//karl
//ClosedAgreementsTable class that extends GuiTable and implements TableDecorator that displays the columns of the table
public class ClosedAgreementsTable extends GuiTable<AgreementModel> implements TableDecorator<AgreementModel>{
    private TableColumn<RowModel<AgreementModel>, Number> fixedTermsCol;
    private TableColumn<RowModel<AgreementModel>, Number> startValueCol;
    private TableColumn<RowModel<AgreementModel>, Date> startAgreementCol;
    private TableColumn<RowModel<AgreementModel>, Rating> RKiCol;
    private TableColumn<RowModel<AgreementModel>, Customer> customerCol;
    private TableColumn<RowModel<AgreementModel>, Employee> employeeCol;
    private TableColumn<RowModel<AgreementModel>, Vehicle> vehicleCol;
    private TableColumn<RowModel<AgreementModel>, Date> startCol;
    private TableColumn<RowModel<AgreementModel>, Date> endCol;
    private TableColumn<RowModel<AgreementModel>, Number> endpriceCol;
   
    //constructor for table that sets the columns and names
    public ClosedAgreementsTable(){
        getColumns().add(fixedTermsCol      = new TableColumn<>("Terminer"));
        getColumns().add(startValueCol      = new TableColumn<RowModel<AgreementModel>, Number>("Indskud"));
        getColumns().add(startAgreementCol  = new TableColumn<RowModel<AgreementModel>, Date>("Tilbud Givet den"));
        getColumns().add(RKiCol             = new TableColumn<RowModel<AgreementModel>, Rating>("Købers Rki"));
        getColumns().add(customerCol        = new TableColumn<RowModel<AgreementModel>, Customer>("Køber"));
        getColumns().add(employeeCol        = new TableColumn<RowModel<AgreementModel>, Employee>("Sælger"));
        getColumns().add(vehicleCol         = new TableColumn<RowModel<AgreementModel>, Vehicle>("Køretøj"));
        getColumns().add(startCol           = new TableColumn<RowModel<AgreementModel>, Date>("I kraft fra"));
        getColumns().add(endCol             = new TableColumn<RowModel<AgreementModel>, Date>("I Kraft til"));
        getColumns().add(endpriceCol        = new TableColumn<RowModel<AgreementModel>, Number>("Total pris"));
        //here we set the cell value factory for each column and how to get the value
        fixedTermsCol.setCellValueFactory((cellData)          -> cellData.getValue().getItem().fixedTermsProperty());
        startValueCol.setCellValueFactory((cellData)          -> cellData.getValue().getItem().startValueProperty());
        startValueCol.setCellFactory(CurrencyCell.forTableColumn());
        startAgreementCol.setCellValueFactory((cellData)      -> cellData.getValue().getItem().startAgreementProperty());
        RKiCol.setCellValueFactory((cellData)                 -> cellData.getValue().getItem().RKiProperty());
        customerCol.setCellValueFactory((cellData)            -> cellData.getValue().getItem().customerProperty());
        employeeCol.setCellValueFactory((cellData)            -> cellData.getValue().getItem().employeeProperty());
        vehicleCol.setCellValueFactory((cellData)             -> cellData.getValue().getItem().vehicleProperty());
        startCol.setCellValueFactory((cellData)               -> cellData.getValue().getItem().startProperty());
        endCol.setCellValueFactory((cellData)                 -> cellData.getValue().getItem().endProperty());
        endpriceCol.setCellValueFactory((cellData)            -> cellData.getValue().getItem().endPriceProperty());
        endpriceCol.setCellFactory(CurrencyCell.forTableColumn());
    }
    
    @Override
    public GuiTable<AgreementModel> getTable() {
        return this;
    }
}
