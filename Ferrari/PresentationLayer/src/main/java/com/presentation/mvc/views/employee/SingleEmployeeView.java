package com.presentation.mvc.views.employee;

import java.io.ByteArrayInputStream;
import com.model.entities.Employee;
import com.presentation.mvc.views.ViewVBox;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
//anders
//shows an employee, not editable, could maybe have been used more places if it had more conditions, set editable fx and changeable texts
public class SingleEmployeeView extends ViewVBox {
    public Employee model;
    public TextField email;
    public TextField phoneNumber;
    public TextField name;
    public ImageView image;

    public SingleEmployeeView(Employee model) {
        getStyleClass().add("rightContainer");

        this.model = model;
        email = new TextField(model.getEmail());
        email.setEditable(false);
        phoneNumber = new TextField(model.getPhoneNumber());
        phoneNumber.setEditable(false);

        name = new TextField(model.getName());
        name.setEditable(false);

        image = new ImageView();
        if(model.getImage() != null)
           image.setImage(new Image(new ByteArrayInputStream(model.getImage()), 0, 200, true, true));
        getChildren().addAll(
            new NiceHBox("rightContainer", new Insets(5), new Label("Sælger navn: "), name ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Sælger email: "), email ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Sælger tlf: "), phoneNumber ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Sælger billede: "), image )
        );
    }

    public void setModel(Employee model) {
        this.model = model;
        name.setText(model.getName());
        email.setText(model.getEmail());
        phoneNumber.setText(model.getPhoneNumber());
        if(model.getImage() != null)
           image.setImage(new Image(new ByteArrayInputStream(model.getImage()), 0, 200, true, true));
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().addFirst(new HBox(buttons));
    }
}
