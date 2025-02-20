package com.presentation.mvc.views.employee;

import com.presentation.mvc.views.table.ui.GuiTable;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
//anders
public class EmployeesView extends VBox {
    public EmployeesView(Button... buttons) {
        getStyleClass().add("employees");
        getChildren().add(new NiceHBox("leftContainer", buttons));
    }

}
