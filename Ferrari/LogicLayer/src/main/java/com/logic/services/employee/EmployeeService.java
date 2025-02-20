package com.logic.services.employee;

import com.data.actions.Data;
import com.data.actions.specifics.UserExtra;
import com.data.dao.interfaces.EmployeeActions;
import com.logic.Simples.*;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.services.enums.ServiceType;

//anders
public class EmployeeService extends HandlerObject {
    private HandlerHolder holder;

    public EmployeeService(EmployeeActions data) {
        //creates all handlers and adds them to each other
        holder = new SimpleHolder(
                new LoginHandler(data),
                new SimpleCreateHandler(data),
                new SimpleReadHandler(data),
                new SimpleReadAllHandler(data),
                new SimpleUpdateHandler(data),
                new SimpleDeleteHandler(data),
                new UpdateSelfHandler(data)
        );
    }

    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.Employee;
    }

    @Override
    public void action(Request request) {
        // go down new COR with CRUD
        holder.query(request);
    }
}
