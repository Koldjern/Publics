package com.presentation.mvc.views.employee.modals;

import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.generalgui.NiceHBox;

import javafx.geometry.Insets;
import javafx.scene.control.*;
//anders
public class PasswordEmployeeView extends EmployeeBaseView {
    public PasswordEmployeeView(EmployeeModel model) {
        super(model);
        PasswordField password = new PasswordField();
        if(model.getPassword() != "")
            password.setText(model.getPassword());
        model.passwordProperty().bind(password.textProperty());
        getChildren().addFirst(new NiceHBox("rightContainer", new Insets(5), new Label("Password:"), password));
    }
}
