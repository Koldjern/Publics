package com.presentation.mvc.controllers.employee.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.ViewVBox;
import com.presentation.mvc.views.employee.EmployeeImageView;
import com.presentation.mvc.views.employee.modals.PasswordEmployeeView;
import com.presentation.mvc.views.generalgui.NiceButton;
import com.presentation.tools.FileMethods;
import com.presentation.tools.alert.Alerter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//anders
//modal controller creates an employee if wanted
public class CreateEmployeeController extends ModalController<EmployeeModel> {
    private EmployeeModel model;
    private HBox view;
    private ViewVBox viewRight;
    private ViewVBox viewLeft;

    private double orgWidth;

    public CreateEmployeeController() {
        model = new EmployeeModel();
        Button createButton = new NiceButton("Lav Bruger", "IDacceptButton", this::create);
        Button cancelButton = new NiceButton("Fortryd", "IDdeclineButton", this::decline);
        Button imageButton = new NiceButton("Vælg Billede", "IDoptionButton", this::findImage);
        viewRight = new PasswordEmployeeView(model);
        viewRight.addButtons(createButton, cancelButton);
        viewLeft = new EmployeeImageView(model);
        viewLeft.addButtons(imageButton);
        view = new HBox(viewLeft, viewRight);
    }
    //get image
    public void findImage(ActionEvent event) {
        byte[] image = FileMethods.findImage((Stage)view.getScene().getWindow());
        if (image != null) 
        model.setImage(image);
        if(orgWidth == 0d)
            orgWidth = view.getWidth();
        getStage().setWidth(orgWidth + 450);
    }
    @Override
    public Pane getView() {
        return view;
    }
    // makes user
    public void create(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request<EmployeeModel,Employee>(ServiceType.Employee, CRUDType.Create,
                model,
                (newEmployee) -> {
                    if(newEmployee != null) {
                        setResult(new EmployeeModel(newEmployee));
                        //bindings can persevere
                        viewLeft.unbindAll();
                        viewRight.unbindAll();
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
