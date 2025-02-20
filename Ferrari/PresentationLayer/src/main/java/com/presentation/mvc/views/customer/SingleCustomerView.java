package com.presentation.mvc.views.customer;

import com.model.entities.Customer;
import com.presentation.mvc.views.ViewVBox;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
//magnus
public class SingleCustomerView extends ViewVBox {
    public Customer model;
    public TextField email;
    public TextField phoneNumber;
    public TextField name;
    public TextField cpr;

    public SingleCustomerView(Customer model) {
        getStyleClass().add("rightContainer");
        this.model = model;
        email = new TextField(model.getEmail());
        email.setEditable(false);
        phoneNumber = new TextField(model.getPhoneNumber());
        phoneNumber.setEditable(false);

        name = new TextField(model.getName());
        name.setEditable(false);

        cpr = new TextField(model.getCpr());
        cpr.setEditable(false);
        getChildren().addAll(
            new NiceHBox("rightContainer", new Insets(5), new Label("Kunde navn: "), name ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Kunde email: "), email ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Kunde tlf: "), phoneNumber ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Kunde cpr: "), cpr )
        );
    }

    public void setModel(Customer model) {
        this.model = model;
        name.setText(model.getName());
        email.setText(model.getEmail());
        phoneNumber.setText(model.getPhoneNumber());
        cpr.setText(model.getCpr());
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().addFirst(new HBox(buttons));
    }
    
}
