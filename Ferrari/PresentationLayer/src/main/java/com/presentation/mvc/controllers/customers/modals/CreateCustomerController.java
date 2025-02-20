package com.presentation.mvc.controllers.customers.modals;

import java.util.List;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.City;
import com.model.entities.Customer;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.views.customer.modals.CustomerBaseView;
import com.presentation.tools.alert.Alerter;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
//magnus
public class CreateCustomerController extends ModalController<CustomerModel>{
    private CustomerModel model;
    private CustomerBaseView view;

    // Konstruktor for CreateCustomerController
    public CreateCustomerController(){
        model = new CustomerModel();
        Button createButton = new Button("Lav kunde");
        createButton.setOnAction(this::create);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        
        // ObservableList for byer
        ObjectProperty<ObservableList<City>> listProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        view = new CustomerBaseView(model, listProperty);
        view.addButtons(createButton, cancelButton);

        // query med singletons til alle byer
        ServiceSingleton.getInstance().query(new Request<Void, List<City>>(ServiceType.City, CRUDType.ReadAll,
            null,
            (cities) -> {
                ObservableList<City> obs = FXCollections.observableArrayList();
                obs.addAll(cities);
                // setter listen af byer til observable list
                listProperty.set(obs);
            })
        );    

        
    }

    // Metode til at hente view og retunere review
    @Override
    public Pane getView() {
        return view;
    }

    // Metode til at oprette kunde
    public void create(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request<CustomerModel, CustomerModel>(ServiceType.Customer, CRUDType.Create,
                model,
                (newCustomer) -> {
                    // hvis kunden er returneret, så setter resultatet, unbinder og lukker vinduet 
                    if(newCustomer != null) {
                        setResult(newCustomer);
                        newCustomer.unbindAll();
                        Platform.runLater(this::close);
                    }
                },
                // Validation for at vise fejlbeskeder
                new Validation<>(
                    (request) -> {
                        Platform.runLater(
                            () -> Alerter.information("Forkerte data", request.getValidation().getMessages())
                        );
                    }
                )
            )
        );
    }
    // Metode til at afvise oprettelse 
    public void decline(ActionEvent event) {
        close();
    }
    
}
