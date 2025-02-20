package com.presentation.mvc.controllers.agreement;

import java.util.List;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.agreement.AgreementView;
import com.presentation.mvc.views.table.concretes.OpenAgreementTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
//karl

//table and controller for open agreements 
public class OpenAgreementsController extends Controller{
    private  TableModel<AgreementModel> model;
    private AgreementView view;
    public OpenAgreementsController() {
        view = new AgreementView();
        Request<Void, List<Agreement>> request = new Request<>(ServiceType.AgreementOpen, CRUDType.ReadAll, (agreements) -> {
            Platform.runLater( () -> {
                TableDecorator<AgreementModel> table = new OpenAgreementTable();
                model = new TableModel<>(ServiceType.AgreementOpen, AgreementModel.makeModels(agreements));
                table = new ParentTableDecorator<>(model, table);
                table = new TableHeightDecorator<>(0.8, table);
                table = new TableWidthDecorator<>(0.9, table);
                table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("optionButton"), "Opdater aftale", new SelectCommand<>((rowmodel)->{
                    AgreementModel agreement = rowmodel.getItem();
                    Facade.getInstance().setCenter(new AgreementController(agreement, true, false));
                }), "opdater"), table);
                table = new ButtonColumnDecorator<>(new ColumnController<>(new ButtonFactory<>("declineButton"), "Slet aftale", new DeleteCommand<>(model), "slet"), table);
                table.getTable().setup(view);
            });
        });
        ServiceSingleton.getInstance().query(request);
        setView(view);
        //button for new agreement for making a new agreement
        Button makeAgreement = new Button("Ny Aftale");
        makeAgreement.setOnAction(this::newAgreement);
        view.addButtons(makeAgreement);
        
    }
    
    public void newAgreement(ActionEvent event) {
        AgreementModel agreement = new AgreementModel();
        Facade.getInstance().setCenter(new AgreementController(agreement, true, false));
    }
    @Override
    public AgreementView getView() {
        return view;
    }   
}
