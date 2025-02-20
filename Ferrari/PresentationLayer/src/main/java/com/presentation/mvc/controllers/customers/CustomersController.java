package com.presentation.mvc.controllers.customers;

import java.util.List;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Customer;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.customers.modals.CreateCustomerController;
import com.presentation.mvc.controllers.customers.modals.UpdateCustomerController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.customer.CustomersView;
import com.presentation.mvc.views.table.concretes.CustomerTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

//magnus
public class CustomersController extends Controller{
    // Model for table og view
    private TableModel<CustomerModel> model;
    private CustomersView view;

    // Konstruktor for CustomersController
    public CustomersController() {
        Button newCustomer = new Button("Ny Bruger");
        newCustomer.setOnAction(this::newCustomer);
        view = new CustomersView(newCustomer);
        Request<Void, List<Customer>> request = new Request<>(ServiceType.Customer, CRUDType.ReadAll, (customers) -> {
        
            //to allow ui to be run
            Platform.runLater( () -> {
                // create table and model for customers
                TableDecorator<CustomerModel> table = new CustomerTable();
                model = new TableModel<>(ServiceType.Customer, CustomerModel.MakeModel(customers));
                table = new ParentTableDecorator<>(model, table);
                table = new TableHeightDecorator<>(0.8, table);
                table = new TableWidthDecorator<>(0.9, table);
                table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("optionButton"), "Opdater kunde", new UpdateCommand<>((row) -> new UpdateCustomerController(row.getItem())), "opdater"), table);
                table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("declineButton"), "Slet kunde", new DeleteCommand<>(model), "slet"), table);
                view.setTable(table.getTable());
            });
        });
        // query for customers med singletons 
        ServiceSingleton.getInstance().query(request);
        setView(view);
    }

    // Metode til at oprette ny kunde
    public void newCustomer(ActionEvent event) {
        CustomerModel customer = Facade.getInstance().openModalResult(new CreateCustomerController());
        // Hvis kunden ikke er null, så tilføj kunden til tabellen
        if(customer != null)
            model.addRow(new RowModel<>(customer, ServiceType.Customer));
    }

    // Metode til get view
    @Override
    public CustomersView getView() {
        return view;
    }
}
