package com.presentation.mvc.controllers.vehicle.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Vehicle;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.vehicle.modals.VehicleView;
import com.presentation.tools.FileMethods;
import com.presentation.tools.alert.Alerter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//jakob
public class UpdateVehicleController extends ModalController<VehicleModel>{

    private VehicleModel model;
    private Vehicle vehicle;
    private VehicleView view;
    public UpdateVehicleController(Vehicle vehicle) {
// Klassen gør det muligt at opdatere informationer på vehicle
        this.vehicle = vehicle;
        model = new VehicleModel(vehicle);
        view = new VehicleView(model);
        Button updateButton = new Button("Opdater Bil");
        updateButton.setOnAction(this::update);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        Button imageButton = new Button("Vælg Billede");
        imageButton.setOnAction(this::findImage);
        view.addButtons(updateButton, imageButton, cancelButton);
    }
    public void findImage(ActionEvent event) {
// Opdater billede af bil
        byte[] image = FileMethods.findImage((Stage)view.getScene().getWindow());
        if (image != null) 
            model.setImage(image);
    }
    @Override
    public Pane getView() {
        return view;
    }
    public void update(ActionEvent event) {
// Opdatere vehicle hvis der er sket en ændring
        ServiceSingleton.getInstance().query(new Request<Vehicle, Boolean>(ServiceType.Vehicle, CRUDType.Update,
        model,
        (updated) -> {
            if(updated) 
                Platform.runLater(this::close);
        },
        new Validation<>(
// Validere om der er fejl i forspørgslen
            (request) -> Platform.runLater(() -> Alerter.information("Forkert data", request.getValidation().getMessages()))
        )));
    }
    public void decline(ActionEvent event) {
        close();
    }
}
