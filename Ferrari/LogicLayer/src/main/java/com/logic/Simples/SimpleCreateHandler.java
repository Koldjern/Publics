package com.logic.Simples;

import com.data.actions.Data;
import com.data.actions.general.Create;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
//anders
public class SimpleCreateHandler extends HandlerObject {
    private Create data;
    public SimpleCreateHandler(Create data) {
        this.data = data;
    }
    public SimpleCreateHandler(Create data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Create;
    }

    @Override
    protected void action(Request request) {
        if(request.getSetter() != null)
            request.getSetter().action(data.create(request.getObject()));
        else 
            data.create(request.getObject());
    }
}
