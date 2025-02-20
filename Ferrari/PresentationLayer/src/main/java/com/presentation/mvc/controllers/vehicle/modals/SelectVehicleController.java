package com.presentation.mvc.controllers.vehicle.modals;

import java.util.List;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.table.concretes.VehicleTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.mvc.views.vehicle.VehiclesView;
import com.presentation.tools.ScreenWatcher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
//jakob
public class SelectVehicleController extends ModalController<Vehicle> {
    private TableModel<VehicleModel> model;
    private VehiclesView view;
    private TableDecorator<VehicleModel> table;
    public SelectVehicleController() {
        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        cancelButton.getStyleClass().add("declineButton");
        table = new VehicleTable();
        view = new VehiclesView(cancelButton);
// Sender forespørgsel på alle vehicles
        Request<Void, List<Vehicle>> request = new Request<>(ServiceType.Vehicle, CRUDType.ReadAll, (vehicles) -> {
            //to allow ui to be run
            Platform.runLater( () -> {
// Opdatere tabel for vehicles
                model = new TableModel<VehicleModel>(ServiceType.Employee, VehicleModel.makeModels(vehicles));
                model.getRows().iterator().forEachRemaining((row) -> {
                    row.getImages().put("vehicle", (row.getItem()).imageProperty());
                });
                table = new ParentTableDecorator<>(model, table);
                table = new TableHeightDecorator<>(0.6, table);
                table = new TableWidthDecorator<>(0.8, table);
                table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("acceptButton"), "Vælg bil", new SelectCommand<>( 
                    (rowModel) -> {
                        setResult(rowModel.getItem()); 
                        close();    
                    }),
                        "Vælg"), table);
// Opsætter tabel i ui
                table.getTable().setup(view);
            });
        });
        view.setPrefHeight(ScreenWatcher.getInstance().getScreenHeightWithDecimal(0.6));
        view.setPrefWidth(ScreenWatcher.getInstance().getScreenWidthWithDecimal(0.8));
        ServiceSingleton.getInstance().query(request);
    }

    @Override
    public Pane getView() {
        return view;
    }
    
    public void decline(ActionEvent event) {
        close();
    }
}