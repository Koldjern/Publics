package com.presentation.mvc.controllers.customers;

import com.model.entities.Customer;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.views.customer.SingleCustomerView;
import javafx.scene.control.Button;
//magnus

// Controller for single customer
public class SingleCustomerController extends Controller{
    private Customer model;
    private SingleCustomerView view;

    // Konstruktor for SingleCustomerController
    public SingleCustomerController(Customer model) {
        this.model = model;
        view = new SingleCustomerView(model);
        setView(view);
    }
    // Set model for view
    public void setModel(Customer model) {
        view.setModel(model);
    }

    // Metode til at tilføjer buttons til view
    public void addButtons(Button... buttons) {
        view.addButtons(buttons);
    }
}
