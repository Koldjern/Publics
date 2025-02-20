package com.presentation.mvc.controllers.vehicle.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
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
public class CreateVehicleController extends ModalController<VehicleModel>{
    private double orgWidth;
    private VehicleModel model;
    private VehicleView view;
    public CreateVehicleController() {
// Håndtere model og visning
        model = new VehicleModel();
        view = new VehicleView(model);
// Opretter knapper til scenen
        Button createButton = new Button("Opret bil");
        createButton.getStyleClass().add("acceptButton");
        createButton.setOnAction(this::create);

        Button cancelButton = new Button("Fortryd");
        cancelButton.getStyleClass().add("declineButton");
        cancelButton.setOnAction(this::decline);

        Button imageButton = new Button("Vælg Billede");
        imageButton.setOnAction(this::findImage);
        createButton.getStyleClass().add("optionButton");
// Tilføjer knapper til scenen
        view.addButtons(createButton, imageButton, cancelButton);
    }

    @Override
    public Pane getView() {
        return view;
    }
    public void findImage(ActionEvent event) {
// Til at implementere billeder
        byte[] image = FileMethods.findImage((Stage)view.getScene().getWindow());
        if (image != null) 
            model.setImage(image);
        if(orgWidth == 0d)
            orgWidth = view.getWidth();
        getStage().setWidth(orgWidth + 450);
    }
    public void create(ActionEvent event) {
// Metode til at oprette en vehicle
        ServiceSingleton.getInstance().query(new Request<VehicleModel, VehicleModel>(ServiceType.Vehicle, CRUDType.Create,
        model,
        (newVehicle) -> {
            if(newVehicle != null) {
                setResult(newVehicle);
                newVehicle.unbindAll();
                Platform.runLater(this::close);
            }
        },
        new Validation<>(
// validering til callback funktion hvis der er fejl
            (request) -> Platform.runLater(() -> Alerter.information("Forkert data", request.getValidation().getMessages()))
        )));
    }
    public void decline(ActionEvent event) {
        close();
    }
}
