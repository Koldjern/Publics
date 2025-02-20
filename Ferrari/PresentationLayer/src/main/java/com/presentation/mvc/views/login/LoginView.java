package com.presentation.mvc.views.login;

import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.generalgui.NiceButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//anders
//view for logging in(DUH!?), binds the appropiate values to gui
public class LoginView extends VBox {
    private TextField email;
    private PasswordField password;

    public LoginView(EmployeeModel model, EventHandler<ActionEvent> buttonAction) {
        setAlignment(Pos.TOP_CENTER);
        getStyleClass().add("login");
        email = new TextField();
        model.emailProperty().bind(email.textProperty());
        password = new PasswordField();
        model.passwordProperty().bind(password.textProperty());
        HBox texts = new HBox(new Label("Email:"), email, new Label("Password:"), password);
        Button login = new NiceButton("Log ind", "IDLogin", buttonAction);
        getChildren().addAll(
                texts,
                login
        );
        setMargin(login, new Insets(10));
    }
}
