package com.presentation.mvc.models.agreements;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import com.model.entities.Agreement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
//karl
//agreements validation model that extends agreementmodel and adds functionality to validate the agreement
public class AgreementValidationModel extends AgreementModel {
    private BooleanProperty fixedTermsProp;
    private BooleanProperty startValueProp;
    private BooleanProperty endProp;
    private BooleanProperty startProp;
    private BooleanProperty RkiProp;
    private BooleanProperty daysRateProp;
    private BooleanProperty customerProp;
    private BooleanProperty employeeProp;
    private BooleanProperty vehicleProp;
    private BooleanProperty invoicesProp;
    private BooleanProperty buyOutProp;

    public AgreementValidationModel(Consumer<AgreementValidationModel> onChange) {
        super();
        setup(onChange);
    }
    public AgreementValidationModel(Agreement agreement, Consumer<AgreementValidationModel> onChange) {
        super(agreement);
        setup(onChange);
    }
    //setup method that listens to changes and automatically updates the properties
    private void setup(Consumer<AgreementValidationModel> onChange) {
        fixedTermsProp = new SimpleBooleanProperty(getFixedTerms() > 0);
        startValueProp = new SimpleBooleanProperty(getStartValue() >= 0 && getStartValue() <= getVehicle().getPrice());

        endProp = new SimpleBooleanProperty(getEnd() != null && 
            (getEnd().toLocalDate().getYear() >= LocalDateTime.now().getYear() ||
            getEnd().toLocalDate().getMonthValue() >= LocalDateTime.now().getMonthValue() ||
            getEnd().toLocalDate().getDayOfMonth() >= LocalDateTime.now().getDayOfMonth()) 
        );
        startProp = new SimpleBooleanProperty(getStart() != null && 
            (getStart().toLocalDate().getYear() >= LocalDateTime.now().getYear() ||
            getStart().toLocalDate().getMonthValue() >= LocalDateTime.now().getMonthValue() ||
            getStart().toLocalDate().getDayOfMonth() >= LocalDateTime.now().getDayOfMonth()) 
        );
        RkiProp = new SimpleBooleanProperty(false);
        daysRateProp = new SimpleBooleanProperty(false);
        customerProp = new SimpleBooleanProperty(getCustomer() != null);
        employeeProp = new SimpleBooleanProperty(getEmployee() != null);
        vehicleProp = new SimpleBooleanProperty(getVehicle() != null);
        invoicesProp = new SimpleBooleanProperty(false);
        buyOutProp = new SimpleBooleanProperty(getVehicle() != null ? getVehicle().getPrice() <= getStartValue() : false);

        daysRateProperty().addListener((observe, oldVal, newVal) -> { 
            daysRateProp.set(newVal.doubleValue() != 0d);
            invoicesProp.set(false);
            onChange.accept(this);
        });
        RKiProperty().addListener((observe, oldVal, newVal) -> { 
            RkiProp.set(newVal != null);
            invoicesProp.set(false);
            onChange.accept(this);
        });
        //
        startProperty().addListener((observe, oldVal, newVal) -> { 
            startProp.set(newVal != null && 
            newVal.toLocalDate().getYear() >= LocalDateTime.now().getYear() ||
            newVal.toLocalDate().getMonthValue() >= LocalDateTime.now().getMonthValue() ||
            newVal.toLocalDate().getDayOfMonth() >= LocalDateTime.now().getDayOfMonth() );
            invoicesProp.set(false);
            onChange.accept(this);
        });
        endProperty().addListener((observe, oldVal, newVal) -> {
            endProp.set(getEnd() != null && 
            getEnd().toLocalDate().getYear() >= LocalDateTime.now().getYear() ||
            getEnd().toLocalDate().getMonthValue() >= LocalDateTime.now().getMonthValue() ||
            getEnd().toLocalDate().getDayOfMonth() >= LocalDateTime.now().getDayOfMonth() 
            );
            invoicesProp.set(false);
            onChange.accept(this);
        });
        startValueProperty().addListener((observe, oldVal, newVal) -> {
            startValueProp.set(newVal.doubleValue() >= 0 && newVal.doubleValue() <= getVehicle().getPrice());
            invoicesProp.set(false);
            onChange.accept(this);
        });
        fixedTermsProperty().addListener((observe, oldVal, newVal) -> { 
            fixedTermsProp.set(newVal.intValue() > 0);
            invoicesProp.set(false);
            onChange.accept(this);
        });
        vehicleProperty().addListener((observe, oldVal, newVal) -> {
            startValueProp.set(newVal.getPrice() >= getStartValue());
            vehicleProp.set(newVal != null);
            invoicesProp.set(false);
            onChange.accept(this);
        });
        customerProperty().addListener((observe, oldVal, newVal) -> {   
            customerProp.set(newVal != null);
            invoicesProp.set(false);
            onChange.accept(this);
        });
        employeeProperty().addListener((observe, oldVal, newVal) -> {
            employeeProp.set(newVal != null);
            onChange.accept(this);
        });
        invoiceProperty().addListener((observe, oldVal, newVal) -> {
            invoicesProp.set(newVal != null);
            onChange.accept(this);
        });
        startValueProperty().addListener((observe, oldVal, newVal) -> {
            buyOutProp.set(getVehicle() != null ? getVehicle().getPrice() <= newVal.doubleValue() : false);
            onChange.accept(this);
        });
        vehicleProperty().addListener((observe, oldVal, newVal) -> {
            buyOutProp.set(newVal != null ? newVal.getPrice() <= getStartValue() : false);
            onChange.accept(this);
        });
    }
    public BooleanProperty buyoutBooleanProperty() {
        return buyOutProp;
    }
    public BooleanProperty fixedTermsBooleanProperty() {
        return fixedTermsProp;
    }
    public BooleanProperty startValueBooleanProperty() {
        return startValueProp;
    }
    public BooleanProperty endBooleanProperty() {
        return endProp;
    }
    public BooleanProperty startBooleanProperty() {
        return startProp;
    }
    public BooleanProperty rkiBooleanProperty() {
        return RkiProp;
    }
    public BooleanProperty daysRateBooleanProperty() {
        return daysRateProp;
    }
    public BooleanProperty customerBooleanProperty() {
        return customerProp;
    }
    public BooleanProperty employeeBooleanProperty() {
        return employeeProp;
    }
    public BooleanProperty vehicleBooleanProperty() {
        return vehicleProp;
    }
    public BooleanProperty invoicesBooleanProperty() {
        return invoicesProp;
    }
}
