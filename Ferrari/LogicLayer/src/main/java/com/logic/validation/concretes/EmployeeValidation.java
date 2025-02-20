package com.logic.validation.concretes;

import com.data.dao.interfaces.EmployeeActions;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
//anders
public class EmployeeValidation extends HandlerObject {
    private EmployeeActions data;
    public EmployeeValidation(EmployeeActions data) {
        this.data = data;
    }
    public EmployeeValidation(HandlerObject handler) {
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Employee;
    }

    @Override
    protected void action(Request request) {
        //validation example could be done in other classes which this will own
        Employee employee = (Employee)request.getObject();
        if(employee.getName().length() < 5)
            request.getValidation().addMessage("navn er for kort, minimum 5 bogstaver");
        if(employee.getName().length() > 40)
            request.getValidation().addMessage("navn er for langt");
        if (!employee.getEmail().contains("@") || employee.getEmail().length() < 5) 
            request.getValidation().addMessage("Ikke en email");
        if(employee.getEmail().length() > 35)
            request.getValidation().addMessage("email er for lang");
        if(employee.getPhoneNumber().length() > 20)
            request.getValidation().addMessage("tlfnummer er for langt");
        if(employee.getPhoneNumber().length() < 8)
            request.getValidation().addMessage("tlfnummer er for kort");

        if(!data.check(employee) && !data.checkUpdate(employee))
            request.getValidation().addMessage("email er i brug");
    }
}
