package com.logic.validation.concretes;

import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Customer;
//Magnus
// klasse til at validere en kunde
public class CustomerValidation extends HandlerObject{
    public CustomerValidation() {
    }
    // konstruktor til CustomerValidation med HandlerObject
    public CustomerValidation(HandlerObject handler) {
        setNext(handler);
    }

    // metode til at tjekke om request er en kunde
    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Customer;
    }
    
    // metode til at udføre handlinger på en kunde
    @Override
    protected void action(Request request) {
        Customer customer = (Customer) request.getObject();
        if(customer.getAddress().length() < 5)
            request.getValidation().addMessage("Ikke en gyldig adresse");
        if(customer.getAddress().length() > 20)
            request.getValidation().addMessage("Ikke en gyldig adresse");
        if(customer.getCpr().length() != 10)
            request.getValidation().addMessage("Ikke et gyldigt cpr, skal være 10 cifre");
        if(customer.getPhoneNumber().length() < 8)
            request.getValidation().addMessage("Ikke et gyldigt nr., skal være 8 cifre");
        if ((customer.getEmail().length() < 5) || (!customer.getEmail().contains("@")))
            request.getValidation().addMessage("Ikke en gyldig email");
        
        
    }
}
