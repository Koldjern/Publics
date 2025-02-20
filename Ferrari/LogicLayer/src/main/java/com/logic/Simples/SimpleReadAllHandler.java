package com.logic.Simples;

import com.data.actions.Data;
import com.data.actions.general.ReadAll;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
//anders
public class SimpleReadAllHandler extends HandlerObject {
    private ReadAll data;
    public SimpleReadAllHandler(ReadAll data) {
        this.data = data;
    }
    public SimpleReadAllHandler(ReadAll data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.ReadAll;
    }

    @Override
    protected void action(Request request) {
        if(request.getSetter() != null)
            request.getSetter().action(data.readAll(request.getObject()));
        else 
            data.readAll(request.getObject());
    }
}
