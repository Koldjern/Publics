package com.presentation.mvc.controllers.vehicle;

import java.util.List;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Vehicle;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.controllers.vehicle.modals.CreateVehicleController;
import com.presentation.mvc.controllers.vehicle.modals.UpdateVehicleController;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.table.concretes.VehicleTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.CheckboxColumnDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.mvc.views.vehicle.VehiclesView;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
//jakob
public class AllVehiclesController extends Controller {
    private TableModel<VehicleModel> model;
    private VehiclesView view;
    private TableDecorator<VehicleModel> table;
    public AllVehiclesController() {

        Button insertButton = new Button("Ny bil");
        insertButton.setOnAction(this::addVehicle);

        view = new VehiclesView(insertButton);
        setView(view);
        table = new VehicleTable();
        Request<Void, List<Vehicle>> request = new Request<>(ServiceType.Vehicle, CRUDType.ReadAll, (vehicles) -> {
            //tillader ui at køre
            Platform.runLater( () -> {
                model = new TableModel<VehicleModel>(ServiceType.Employee, VehicleModel.makeModels(vehicles));
                model.getRows().iterator().forEachRemaining((row) -> {
                    row.getImages().put("vehicle", row.getItem().imageProperty());
                });
                table = new TableHeightDecorator<>(0.8, table);
                table = new TableWidthDecorator<>(0.9, table);
                if(Facade.getInstance().getLoggedIn().getOccupation() == Occupation.Manager) {
                    table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("optionButton"), "Opdater andre", new UpdateCommand<>(
                        (row) -> new UpdateVehicleController((Vehicle)row.getItem())
                        ), "opdater"), table);
                    table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("declineButton"), "Slet andre", new DeleteCommand<>(model), "slet"), table);
                    table = new CheckboxColumnDecorator<>(new DeleteCommand<>(model), "Slet", "Slet", "Slet Alle", table);
                }
                table = new ParentTableDecorator<>(model, table);
                table.getTable().setup(view);
            });

        });
        ServiceSingleton.getInstance().query(request);
    }

    public void addVehicle(ActionEvent event) {
// Tilføjer en vehicle til modellen og opdatere
        VehicleModel vehicle = Facade.getInstance().openModalResult(new CreateVehicleController());
        if (vehicle != null) {
            RowModel<VehicleModel> newVehicle = new RowModel<>(vehicle, ServiceType.Vehicle);
            newVehicle.getImages().put("vehicle", newVehicle.getItem().imageProperty());
            model.addRow(newVehicle);            
        }

    }

}
