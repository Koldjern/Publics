package com.presentation.mvc.models.Invoice;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.model.entities.Invoice;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//karl
//InvoiceModel class that extends Invoice and makes objects eable to be used to bind
public class InvoiceModel extends Invoice{
    private IntegerProperty numberProperty;
    private ObjectProperty<Date> dateStartProperty;
    private ObjectProperty<Date> dateEndProperty;
    private DoubleProperty plusProperty;
    private DoubleProperty minusProperty;
    private DoubleProperty ultimoProperty;
    private DoubleProperty primoProperty;
    private StringProperty detailsProperty;
    private DoubleProperty payedProperty;
    //constructor
    public InvoiceModel() {
        numberProperty = new SimpleIntegerProperty();
        dateStartProperty = new SimpleObjectProperty<>();
        dateEndProperty = new SimpleObjectProperty<>();
        plusProperty = new SimpleDoubleProperty();
        minusProperty = new SimpleDoubleProperty();
        ultimoProperty = new SimpleDoubleProperty();
        payedProperty = new SimpleDoubleProperty();
        primoProperty = new SimpleDoubleProperty();
        detailsProperty = new SimpleStringProperty();
    }
    //constructor that takes an invoice and sets the values to the properties
    public InvoiceModel(Invoice other) {
        this();
        payedProperty.set(other.getPayed());
        numberProperty.set(other.getNumber());
        dateStartProperty.set(other.getDatestart());
        dateEndProperty.set(other.getDateend());
        plusProperty.set(other.getPlus());
        minusProperty.set(other.getMinus());
        ultimoProperty.set(other.getUltimovalue());
        primoProperty.set(other.getPrimoprice());
        detailsProperty.set(other.getDetails());
    }
    public IntegerProperty numberProperty() {
        return numberProperty;
    }
    public ObjectProperty<Date> dateStartProperty() {
        return dateStartProperty;
    }
    public ObjectProperty<Date> dateEndProperty() {
        return dateEndProperty;
    }


    public DoubleProperty plusProperty() {
        return plusProperty;
    }

    public DoubleProperty minusProperty() {
        return minusProperty;
    }

    public DoubleProperty ultimoProperty() {
        return ultimoProperty;
    }

    public DoubleProperty primoProperty() {
        return primoProperty;
    }

    public StringProperty detailsProperty() {
        return detailsProperty;
    }
    public DoubleProperty payedProperty() {
        return payedProperty;
    }
    @Override
    public double getPayed() {
        return payedProperty.get();
    }
    @Override
    public void setPayed(double payed) {
        payedProperty.set(payed);
    }
    @Override
    public void setNumber(int value) {
        numberProperty.set(value);
    }
    @Override
    public int getNumber() {
        return numberProperty.get();
    }
    @Override
    public Date getDatestart() {
        return dateStartProperty.get();
    }
    @Override
    public void setDatestart(Date dateStart) {
        dateStartProperty.set(dateStart);
    }
    @Override
    public Date getDateend() {
        return dateEndProperty.get();
    }
    @Override
    public void setDateend(Date dateEnd) {
        dateEndProperty.set(dateEnd);
    }
    @Override
    public double getPlus() {
        return plusProperty.get();
    }
    @Override
    public void setPlus(double plus) {
        plusProperty.set(plus);
    }
    @Override
    public double getMinus() {
        return minusProperty.get();
    }
    @Override
    public void setMinus(double minus) {
        minusProperty.set(minus);
    }
    @Override
    public double getUltimovalue() {
        return ultimoProperty.get();
    }
    @Override
    public void setUltimovalue(double ultimoValue) {
        ultimoProperty.set(ultimoValue);
    }
    @Override
    public double getPrimoprice() {
        return primoProperty.get();
    }
    @Override
    public void setPrimoprice(double primoPrice) {
        primoProperty.set(primoPrice);
    }
    @Override
    public String getDetails() {
        return detailsProperty.get();
    }
    @Override
    public void setDetails(String details) {
        detailsProperty.set(details);
    }
     public static List<InvoiceModel> makeModels(List<Invoice> invoices) {
        List<InvoiceModel> models = new ArrayList<>();
        for (Invoice invoice : invoices)
            models.add(new InvoiceModel(invoice));
        return models;
    }
    public static List<InvoiceModel> makeModels(Invoice[] invoices) {
        List<InvoiceModel> models = new ArrayList<>();
        for (Invoice invoice : invoices)
            models.add(new InvoiceModel(invoice));
        return models;
    }
    public static List<Object> makeModelsAsObjects(List<Invoice> invoices) {
        List<Object> models = new ArrayList<>();
        for(Invoice invoice : invoices)
            models.add(new InvoiceModel(invoice));
        return models;
    } 
    public static List<Object> makeModelsAsObjects(Invoice[] invoices) {
        List<Object> models = new ArrayList<>();
        for(Invoice invoice : invoices)
            models.add(new InvoiceModel(invoice));
        return models;
    } 
}
