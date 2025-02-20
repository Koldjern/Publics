package com.logic;

import com.data.ConnectionData;
import com.data.dao.AgreementClosedData;
import com.data.dao.AgreementOpenData;
import com.data.dao.CityData;
import com.data.dao.CustomerData;
import com.data.dao.EmployeeData;
import com.data.dao.InvoiceData;
import com.data.dao.RatesData;
import com.data.dao.VehicleData;
import com.data.dao.interfaces.*;
import com.logic.handlers.Handler;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.Request;
import com.logic.services.ServiceManager;
import com.logic.services.agreements.AgreementClosedService;
import com.logic.services.agreements.AgreementOpenService;
import com.logic.services.city.CityService;
import com.logic.services.customer.CustomerService;
import com.logic.services.employee.EmployeeService;
import com.logic.services.invoice.InvoiceService;
import com.logic.services.rates.RateService;
import com.logic.services.vehicle.VehicleService;
import com.logic.validation.ValidationManager;
import com.logic.validation.concretes.AgreementValidation;
import com.logic.validation.concretes.EmployeeValidation;
//anders
//singleton that has COR functionality, makes threads 
public class ServiceSingleton implements Handler {
    private static ServiceSingleton instance;
    private HandlerHolder validations;
    private HandlerHolder services;
    //singleton getter
    public static ServiceSingleton getInstance() {
        return instance == null ? instance = new ServiceSingleton() : instance;
    }
    //private ctr because singleton pattern
    private ServiceSingleton() {
        ConnectionData db = new ConnectionData();
        EmployeeActions employeeData = new EmployeeData(db);
        CustomerActions customerData = new CustomerData(db);
        InvoiceActions invoiceData = new InvoiceData(db);
        VehicleActions vehicleData = new VehicleData(db);
        CityActions cityData = new CityData(db);
        RatesActions rateData = new RatesData(db);
        AgreementClosedActions agreementClosed = new AgreementClosedData(db, invoiceData, customerData, employeeData);
        AgreementOpenActions agreementOpen = new AgreementOpenData(db, customerData, employeeData);
        //backend validation because easya nd better than nothing, but worse than frontend validation
        validations = new ValidationManager(
            new EmployeeValidation(employeeData), 
            new AgreementValidation()
            );
            services = new ServiceManager(
                new EmployeeService(employeeData),
            new RateService(rateData),
            new VehicleService(vehicleData),
            new AgreementOpenService(agreementOpen),
            new InvoiceService(invoiceData),
            new AgreementClosedService(agreementClosed),
            new CustomerService(customerData),
            new CityService(cityData)
        );
    }
    public void setValidations(HandlerHolder validations) {
        this.validations = validations;
    }
    public void setServices(HandlerHolder services) {
        this.services = services;
    }
    public HandlerHolder getServices() {
        return services;
    }
    // query will run validations and see if its goooood, if that's the case it will go to services and get the sql data which will go in the setter methods parameter inside SQLRequest
    @Override
    public void query(Request request) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (request.getValidation() != null) {
                    validations.query(request);
                    if (request.anyErrors()) {
                        request.getValidation().getErrorAction().action(request);
                        return;
                    }
                }
                if (services != null)
                    services.query(request);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    //object holder for return value of cor 
    private Object returner;
    //dosent run a thread just uses the cor and sets the value, must be done in a thread to be async
    public <T,U> U queryNoThread(Request<T,U> request) {
        if (services == null)
            return null;
        request.setSetter((value) -> returner = value);
        services.query(request);
        return (U)returner;
    }
}
