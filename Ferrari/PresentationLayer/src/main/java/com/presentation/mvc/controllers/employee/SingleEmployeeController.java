package com.presentation.mvc.controllers.employee;

import com.model.entities.Employee;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.views.employee.SingleEmployeeView;
import javafx.scene.control.Button;
//anders
//simple little controller dosent do much on its own
public class SingleEmployeeController extends Controller {
    private SingleEmployeeView view;
    public SingleEmployeeController(Employee model) {
        view = new SingleEmployeeView(model);
        setView(view);
    }
    public void setModel(Employee model) {
        view.setModel(model);
    }
    public void addButtons(Button... buttons) {
        view.addButtons(buttons);
    }
    
}
