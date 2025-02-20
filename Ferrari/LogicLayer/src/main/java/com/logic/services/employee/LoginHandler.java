package com.logic.services.employee;

import com.data.actions.specifics.UserExtra;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.entities.Employee;
//anders
public class LoginHandler extends HandlerObject {
    private UserExtra data;
    public LoginHandler(UserExtra data) {
        this.data = data;
    }
    public LoginHandler(UserExtra data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Login;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.login((Employee)request.getObject()));
    }
}
