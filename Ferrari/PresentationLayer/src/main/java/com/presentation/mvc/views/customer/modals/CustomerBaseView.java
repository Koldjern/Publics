package com.presentation.mvc.views.customer.modals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.model.entities.City;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.views.ViewVBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//magnus

public class CustomerBaseView extends ViewVBox {
    private CustomerModel model;
    private List<Property> properties;
    public CustomerBaseView(CustomerModel model, ObjectProperty<ObservableList<City>> cityList) {
        this.model = model;
        TextField email = new TextField(model.getEmail());
        model.emailProperty().bind(email.textProperty());

        TextField phoneNumber = new TextField(model.getPhoneNumber());
        model.phoneNumberProperty().bind(phoneNumber.textProperty());

        TextField name = new TextField(model.getName());
        model.nameProperty().bind(name.textProperty());

        TextField address = new TextField(model.getAddress());
        model.addressProperty().bind(address.textProperty());

        ComboBox<City> cityBox = new ComboBox<>();
        cityBox.setValue(model.getCity() != null ? model.getCity() : new City());   
        cityBox.itemsProperty().bind(cityList);
        model.cityProperty().bind(cityBox.valueProperty());

        TextField cpr = new TextField(model.getCpr());
        model.CprProperty().bind(cpr.textProperty());

        addProperties(Arrays.asList(email.textProperty(), phoneNumber.textProperty(), name.textProperty(), address.textProperty(), cityBox.itemsProperty(), cityBox.valueProperty(), cpr.textProperty()));
        getChildren().add(
            new VBox(
                new HBox(new Label("PostNr"),cityBox),
                new HBox(new Label("Email: "), email),
                new HBox(new Label("TLF: "), phoneNumber),
                new HBox(new Label("Navn: "), name),
                new HBox(new Label("adresse: "), address),
                new HBox(new Label("cpr: "), cpr)
            )
        );
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }

}