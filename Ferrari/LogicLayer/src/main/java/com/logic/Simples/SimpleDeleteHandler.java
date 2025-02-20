package com.logic.Simples;

import com.data.actions.Data;
import com.data.actions.general.Delete;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
//anders
public class SimpleDeleteHandler extends HandlerObject {
    private Delete data;

    public SimpleDeleteHandler(Delete data) {
        this.data = data;

    }
    public SimpleDeleteHandler(Delete data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Delete;
    }

    @Override
    protected void action(Request request) {
        if(request.getSetter() != null)
            request.getSetter().action(data.delete(request.getObject()));
        else 
            data.delete(request.getObject());
    }
}
