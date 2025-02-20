package com.presentation.mvc.controllers.employee.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.employee.modals.EmployeeBaseView;
import com.presentation.tools.alert.Alerter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
// anders

public class UpdateEmployeeController extends ModalController<EmployeeModel> {
    private EmployeeModel model;
    private Employee employee;
    private EmployeeBaseView view;
    public UpdateEmployeeController(Employee employee) {
        this.employee = employee;
        model = new EmployeeModel(employee);
        // needs a actionevent which is a funtional interface type
        view = new EmployeeBaseView(model);
        Button updateButton = new Button("Opdater Bruger");
        updateButton.setOnAction(this::update);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);

        view.addButtons(updateButton, cancelButton);
    }

    @Override
    public Pane getView() {
        return view;
    }
    public void update(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request<Employee, Boolean>(ServiceType.Employee, CRUDType.Update,
                model,
                (updated) -> {
                    if(updated) {
                        employee.copy(model);
                        Platform.runLater(this::close);
                    }
                },
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
    public void decline(ActionEvent event) {
        close();
    }


}
