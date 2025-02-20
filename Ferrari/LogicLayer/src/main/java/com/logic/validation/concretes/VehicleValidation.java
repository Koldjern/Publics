package com.logic.validation.concretes;

import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Vehicle;
//Jakob
public class VehicleValidation extends HandlerObject {
    public VehicleValidation() {
    }
    public VehicleValidation (HandlerObject handler) {
        setNext(handler);
    }

    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Vehicle;
    }

    @Override
    protected void action(Request request) {
        Vehicle vehicle = (Vehicle) request.getObject();
// Kører validering på vehicle, med forskellige begrænsninger på vehicles objekter
        if (vehicle.getId() < 0 )
            request.getValidation().addMessage("ID kan ikke være negativt, eller 0");
        if (vehicle.getId() < 10000)
            request.getValidation().addMessage("ID må ikke overskride 10000");
        if (vehicle.getName().length() < 5)
            request.getValidation().addMessage("Navn er for kort, minimum 5 bogstaver");
        if (vehicle.getName().length() > 40)
            request.getValidation().addMessage("Navn er for langt, ikke mere end 40 bogstaver");
        if (vehicle.getPrice() < 10000)
            request.getValidation().addMessage("Prisen skal være mere end 5 cifre.");
        if (vehicle.getPrice() > 100000000)
            request.getValidation().addMessage("Prisen er for høj, det kan ikke gå over i milliarder");
    }
}
