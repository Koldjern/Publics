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

// Controller for updating a customer
public class UpdateCustomerController extends ModalController<CustomerModel> {
    private CustomerModel model;
    private Customer customer;
    private CustomerBaseView view;

    // Konstruktor for UpdateCustomerController
    public UpdateCustomerController (Customer customer) {
        this.customer = customer;
        model = new CustomerModel(customer);

        // Buttons for forskellige scenarier
        Button updateButton = new Button("Opdater Kunde");
        updateButton.setOnAction(this::update);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);

        // ObservableList for byer
        ObjectProperty<ObservableList<City>> listProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        view = new CustomerBaseView(model, listProperty);
        view.addButtons(updateButton, cancelButton);

        // query for byer med singletons til alle byer
        Request<Void, List<City>> request = new Request<>(ServiceType.City, CRUDType.ReadAll,
        (cities) -> listProperty.set(FXCollections.observableArrayList(cities)));
        
        ServiceSingleton.getInstance().query(request);     
}

// Metode til at hente view og returnere
@Override
public Pane getView() {
    return view;
}

// Metode til at opdatere kunde
public void update(ActionEvent event) {
    // query for at opdatere kunde med singletons
    ServiceSingleton.getInstance().query(new Request<CustomerModel, Boolean>(ServiceType.Customer, CRUDType.Update,
            model,
            (updated) -> {
            // Hvis kunden ikke er null, så kopier kunden og luk vinduet
                if(updated) {
                    customer.copy(model);
                    Platform.runLater(this::close);
                }
            },
            // validation for at tjekke om der er fejl i opdateringen
            new Validation<>((request) -> Platform.runLater(() -> Alerter.information("Forkerte Oplysninger", request.getValidation().getMessages())))
        )
    );
}
// Metode til at afvise opdatering
public void decline(ActionEvent event) {
    close();
}

}
