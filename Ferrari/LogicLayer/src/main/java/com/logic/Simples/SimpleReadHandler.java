package com.logic.Simples;

import com.data.actions.Data;
import com.data.actions.general.Read;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
//anders
public class SimpleReadHandler extends HandlerObject {
    private Read data;
    public SimpleReadHandler(Read data) {
        this.data = data;
    }
    public SimpleReadHandler(Read data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Read;
    }

    @Override
    protected void action(Request request) {
        if(request.getSetter() != null)
            request.getSetter().action(data.read(request.getObject()));
        else 
            data.read(request.getObject());
    }
}
