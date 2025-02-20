package com.presentation.mvc.models.agreements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.rki.rki.Rating;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
//karl
//AgreementModel class that extends Agreement and makes objects eable to be used to bind 
public class AgreementModel extends Agreement{
    private IntegerProperty fixedTermsProp;
    private DoubleProperty startValueProp;
    private ObjectProperty<Date> startAgreementProp;
    private ObjectProperty<Date> endProp;
    private ObjectProperty<Date> startProp;
    private ObjectProperty<Rating> RKiProp;
    private ObjectProperty<Customer> customerProp;
    private ObjectProperty<Employee> employeeProp;
    private ObjectProperty<Vehicle> vehicleProp;
    private ObjectProperty<List<Invoice>> invoicesProp;
    private DoubleProperty daysRateProp;
    private DoubleProperty totalRateProp;
    private DoubleProperty endPriceProp;

    //constructor 
    public AgreementModel() {
        fixedTermsProp = new SimpleIntegerProperty();
        startValueProp = new SimpleDoubleProperty();
        startAgreementProp = new SimpleObjectProperty<Date>();
        endProp = new SimpleObjectProperty<Date>();
        startProp = new SimpleObjectProperty<Date>();
        RKiProp = new SimpleObjectProperty<Rating>();
        customerProp = new SimpleObjectProperty<Customer>();
        employeeProp = new SimpleObjectProperty<Employee>();
        vehicleProp = new SimpleObjectProperty<Vehicle>();
        daysRateProp = new SimpleDoubleProperty();
        totalRateProp = new SimpleDoubleProperty();
        endPriceProp = new SimpleDoubleProperty();
        invoicesProp = new SimpleObjectProperty<List<Invoice>>(new ArrayList<>());
        setCustomer(new CustomerModel(true));
        setEmployee(new EmployeeModel(true));
        setVehicle(new VehicleModel(true));
    }
    //constructor that takes an agreement and sets the values to the properties
    public AgreementModel(Agreement agreement) {
        this();
        setId(agreement.getId());
        fixedTermsProp.set(agreement.getFixedTerms());
        startValueProp.set(agreement.getStartValue());
        startAgreementProp.set(agreement.getStartAgreement());
        setTotalRate(agreement.getTotalRate());
        setEnd(agreement.getEnd());
        setStart(agreement.getStart());
        setRki(agreement.getRki());
        setCustomer(new CustomerModel(agreement.getCustomer()));
        setEmployee(new EmployeeModel(agreement.getEmployee()));
        setVehicle(new VehicleModel(agreement.getVehicle()));
        setPayments(agreement.getPayments());
        setEndPrice(agreement.getEndPrice());
    }

    @Override
    public int getFixedTerms() {
        return fixedTermsProp.get();
    }
    @Override
    public double getStartValue() {
        return startValueProp.get();
    }
    @Override
    public Date getStartAgreement() {
        return startAgreementProp.get();
    }
    @Override
    public Rating getRki() {
        return RKiProp.get();
    }
    @Override
    public Customer getCustomer() {
        return customerProp.get();
    }
    @Override
    public Employee getEmployee() {
        return employeeProp.get();
    }
    public Vehicle getVehicle() {
        return vehicleProp.get();
    }
    @Override
    public void setFixedTerms(int fixedterms) {
        fixedTermsProp.set(fixedterms);
    }
    @Override
    public void setStartValue(double startvalue) {
        startValueProp.set(startvalue);
    }
    @Override
    public void setStartAgreement(Date startagreement) {
        startAgreementProp.set(startagreement);
    }
    @Override
    public void setRki(Rating Rki) {
        RKiProp.set(Rki);
    }
    @Override
    public double getDaysRate() {
        return daysRateProp.get();
    }
    public Date getStart() {
        return startProp.get();
    }

    public void setStart(Date start) {
        startProp.set(start);
    }

    public Date getEnd() {
        return endProp.get();
    }

    public void setEnd(Date end) {
        endProp.set(end);
    }
    @Override
    public void setDaysRate(double daysRate) {
        daysRateProp.set(daysRate);
    }
    @Override
    public double getTotalRate() {
        return totalRateProp.get();
    }
    @Override
    public void setTotalRate(double rate) {
        totalRateProp.set(rate);
    }
    @Override
    public double getEndPrice() {
        return endPriceProp.get();
    }
    @Override
    public void setEndPrice(double rate) {
        endPriceProp.set(rate);
    }
    @Override
    public void setCustomer(Customer customer) {
        if(customer != null)
            customerProp.set(customer);
        else
            customerProp.set(new Customer());

    }
    @Override
    public void setEmployee(Employee employee) {
        if(employee != null)
            employeeProp.set(employee);
        else
            employeeProp.set(new Employee());
    }
    @Override
    public void setVehicle(Vehicle vehicle) {
        if(vehicle != null)
        vehicleProp.set(vehicle);
    else
        vehicleProp.set(new Vehicle());
    }
    public void setPayments(List<Invoice> payments) {
        if(payments != null)
        invoicesProp.set(payments);
    }
    public List<Invoice> getPayments() {
        return invoicesProp.get();
    }
    public ObjectProperty<List<Invoice>> invoiceProperty() {
        return invoicesProp;
    }
    public IntegerProperty fixedTermsProperty() {
        return fixedTermsProp;
    }
    public DoubleProperty startValueProperty() {
        return startValueProp;
    }
    public DoubleProperty daysRateProperty() {
        return daysRateProp;
    }
    public DoubleProperty totalRateProperty() {
        return totalRateProp;
    }
    public DoubleProperty endPriceProperty() {
        return endPriceProp;
    }
    public ObjectProperty<Date> startAgreementProperty() {
        return startAgreementProp;
    }
    public ObjectProperty<Date> endProperty() {
        return endProp;
    }
    public ObjectProperty<Date> startProperty() {
        return startProp;
    }
    public ObjectProperty<Rating> RKiProperty() {
        return RKiProp;
    }
    public ObjectProperty<Customer> customerProperty() {
        return customerProp;
    }
    public ObjectProperty<Employee> employeeProperty() {
        return employeeProp;
    }
    public ObjectProperty<Vehicle> vehicleProperty() {
        return vehicleProp;
    }
    public static List<AgreementModel> makeModels(List<Agreement> agreements) {
        List<AgreementModel> models = new ArrayList<>();
        for (Agreement agreement : agreements)
            models.add(new AgreementModel(agreement));
        return models;
    }
    public static List<Object> makeModelsAsObjects(List<Agreement> agreements) {
        List<Object> models = new ArrayList<>();
        for(Agreement agreement : agreements)
            models.add(new AgreementModel(agreement));
        return models;
    }
        
}
