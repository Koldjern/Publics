package com.presentation.mvc.controllers.agreement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.math.LoanCalculator;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.customers.SingleCustomerController;
import com.presentation.mvc.controllers.customers.modals.SelectCustomersController;
import com.presentation.mvc.controllers.employee.SingleEmployeeController;
import com.presentation.mvc.controllers.employee.modals.SelectEmployeeController;
import com.presentation.mvc.controllers.invoice.InvoicesInAgreementController;
import com.presentation.mvc.controllers.vehicle.SingleVehicleController;
import com.presentation.mvc.controllers.vehicle.modals.SelectVehicleController;
import com.presentation.mvc.models.Invoice.InvoiceModel;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.agreements.AgreementValidationModel;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.agreement.OpenAgreementView;
import com.presentation.mvc.views.generalgui.NiceButton;
import com.presentation.tools.FileMethods;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import com.rki.rki.Rating;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AgreementController extends Controller implements Consumer<AgreementValidationModel> {
    private OpenAgreementView view;
    private AgreementValidationModel model;
    private TableModel<InvoiceModel> tableModel;
    private int signum;
    private SingleCustomerController customerController;
    private SingleEmployeeController employeeController;
    private SingleVehicleController vehicleController;
    private InvoicesInAgreementController invoicesController;
    private Button ready;
    private Button csvButton;
    //karl
    //agreement controller for open agreements
    public AgreementController(AgreementModel modelParam, boolean open, boolean customerChosen) {
        signum = -1;
        if(modelParam.getStartAgreement() == null)
            modelParam.setStartAgreement(new Date(System.currentTimeMillis()));
        if(modelParam.getStart() == null)
            modelParam.setStart(new Date(System.currentTimeMillis()));
        if(modelParam.getEnd() == null)
            modelParam.setEnd(new Date(System.currentTimeMillis()));
        if(open)
            modelParam.setEmployee(Facade.getInstance().getLoggedIn());

        this.model = new AgreementValidationModel(modelParam, this);
        customerController = new SingleCustomerController(model.getCustomer());
        employeeController = new SingleEmployeeController(model.getEmployee());
        vehicleController = new SingleVehicleController(model.getVehicle());
        //button that makes a csv file of invoice look csv method
        csvButton = new NiceButton("Udskriv CSV-Fil","IDoptionButton", this::csv);
        //view for the agreement
        view = new OpenAgreementView(employeeController.getView(), customerController.getView(), vehicleController.getView(), model, open);
        setView(view);
        //if the agreement is open, the buttons will be added, and the table will be created
        if(open) {
            if(!customerChosen) {
                NiceButton selectCustomer = new NiceButton("Vælg Kunde", "IDoptionButton", this::chooseCustomer);            
                view.addButtons(selectCustomer);
            }
            Button selectVehicle = new NiceButton("Vælg bil", "IDoptionButton", this::chooseVehicle);
            Button save = new NiceButton("Gem aftale", "IDoptionButton", this::save);
            ready = new NiceButton("Udregn", "IDacceptButton", this::readyContinue);
            Button delete = new NiceButton("Slet", "IDdeclineButton", this::delete);
            view.addButtons(selectVehicle, save, ready, csvButton, delete);
        }
        if(open)
            tableModel = new TableModel<>(ServiceType.Invoice, new ArrayList<>());
        else
            tableModel = new TableModel<>(ServiceType.Invoice, InvoiceModel.makeModels(model.getPayments()));
        invoicesController = new InvoicesInAgreementController(tableModel);
        view.getChildren().add(invoicesController.getView());

        //guardclause is because the value will be updated, and it must set the boolean to false, to avoid a premature button clicking
        if (!open) 
            return;
        model.rkiBooleanProperty().set(false);
        accept(model);
        if(!((CustomerModel)modelParam.getCustomer()).getEmpty())
            getRKiAndRate();
        
    }
    //
    public void readyContinue(ActionEvent event) {
        if(signum == 0) {
            tableModel.removeAllRows();
            List<Invoice> invoices = new ArrayList<>();
            for(Invoice inv : LoanCalculator.låneBeregner(model))
                invoices.add(inv);
            tableModel.addAllRows(RowModel.makeRowModels(ServiceType.Invoice, InvoiceModel.makeModels(invoices)));
            model.setPayments(invoices);
        }
        //checking what rki is and if the customer can get a loan
        else if(signum == 1) {
            if(model.getRki() == Rating.D) {
                Alerter.warning("Dårlig Rki kan ikke låne", "Kunden skal levere hele beløbet selv");
                return;
            }
            if(Facade.getInstance().getLoggedIn().getLoanLimit() > model.getVehicle().getPrice() - model.getStartValue() && Facade.getInstance().getLoggedIn().getOccupation() != Occupation.Manager)
                Alerter.warning("For stort beløb", "Gem aftalen, og en med større godkedelse beløb, kan godkende den");
            ServiceSingleton.getInstance().query(new Request<>(ServiceType.AgreementClosed, CRUDType.Create, model,
            (update) -> {
                if(update != null) 
                    Platform.runLater( () ->
                        Facade.getInstance().setCenter(new OpenAgreementsController())
                    );
            }));
        }
    }    
    
    public void cash(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request<Agreement, Boolean>(ServiceType.AgreementClosed, CRUDType.Create, model,
        (update) -> {
            if(update) 
                Platform.runLater( () ->
                    Facade.getInstance().setCenter(new OpenAgreementsController())
                );
        }));
    }
    //choose employee
    public void chooseEmployee(ActionEvent event) {
        Employee result = Facade.getInstance().openModalResult(new SelectEmployeeController());
        if(result != null) {
            employeeController.setModel(result);
            model.setEmployee(result);
        }
    }
    //choose vehicle
    public void chooseVehicle(ActionEvent event) {
        Vehicle result = Facade.getInstance().openModalResult(new SelectVehicleController());
        if(result != null) {
            vehicleController.setModel(result);
            model.setVehicle(result);
        }
    }
    //choose customer 
    public void chooseCustomer(ActionEvent event) {
        Customer result = Facade.getInstance().openModalResult(new SelectCustomersController());
        if(result != null) {
            customerController.setModel(result);
            model.setCustomer(result);
            getRKiAndRate();
        }
    }
    //delete agreement
    public void delete(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request<>(ServiceType.AgreementOpen, CRUDType.Delete, model,
                (delete) -> {
                    if ((boolean) delete)
                        Platform.runLater(
                                () -> Facade.getInstance().setCenter(new OpenAgreementsController()));
                }));
    }
    //csv file
    public void csv(ActionEvent event) {
        String fileName = model.getCustomer().getName() + "_" + model.getStart() + "_" + model.getVehicle().getName();
        FileMethods.makeCSV(fileName, model.getPayments(), (Stage)view.getScene().getWindow());
    }
    //save agreement
    public void save(ActionEvent event) {
        if(model.getId() == 0) 
            ServiceSingleton.getInstance().query(new Request<Agreement, Agreement>(ServiceType.AgreementOpen, CRUDType.Create, model, 
            (create) -> {
                if(create != null) 
                    Platform.runLater( () ->
                        Facade.getInstance().setCenter(new OpenAgreementsController())
                    );
            }));
        else 
            ServiceSingleton.getInstance().query(new Request<Agreement, Boolean>(ServiceType.AgreementOpen, CRUDType.Update, model,
            (update) -> {
                if(update) 
                    Platform.runLater( () ->
                        Facade.getInstance().setCenter(new OpenAgreementsController())
                    );
            }));
    }
    //validation af agreement
    @Override
    public void accept(AgreementValidationModel agreement) {
        boolean choices = agreement.vehicleBooleanProperty().get() && agreement.customerBooleanProperty().get() && agreement.employeeBooleanProperty().get();
        if(choices && agreement.buyoutBooleanProperty().get()) {
            ready.setText("Køb uden lån");   
            ready.setDisable(false);
            ready.setOnAction(this::cash);
        }   
        else {
            ready.setText("Udregn");   
            ready.setOnAction(this::readyContinue);
            if(choices && agreement.fixedTermsBooleanProperty().get() && agreement.startValueBooleanProperty().get() && agreement.startBooleanProperty().get()
            && agreement.rkiBooleanProperty().get() && agreement.daysRateBooleanProperty().get() ) 
                signum = 0;
            else {
                ready.setText("udfyld formel");   
                signum = -1;
            }
            if(agreement.invoicesBooleanProperty().get()) {
                ready.setText("Godkend");
                signum = 1;
            }
            ready.setDisable(signum == -1);
            csvButton.setDisable(signum != 1);
            Date newDate = new Date(agreement.getStart().getTime());
            newDate = Date.valueOf(newDate.toLocalDate().plusMonths(agreement.getFixedTerms()));
            agreement.setEnd(newDate);
        }
    }
    //få rki og dagsrente 
    private void getRKiAndRate() {
        ServiceSingleton.getInstance().query(new Request<Agreement, Agreement>(ServiceType.Rate, CRUDType.Read, model, 
        (newAgreement) -> { 
            Platform.runLater( () -> {
                model.setRki(Rating.D);
                model.setRki((newAgreement).getRki());
                model.setDaysRate(0d);
                model.setDaysRate((newAgreement).getDaysRate());
            });
        }));
    }

}
