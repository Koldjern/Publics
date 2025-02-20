package com.logic.Simples;

import com.data.actions.Data;
import com.data.actions.general.Update;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
//anders
public class SimpleUpdateHandler extends HandlerObject {
    private Update data;
    public SimpleUpdateHandler(Update data) {
        this.data = data;
    }
    public SimpleUpdateHandler(Update data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Update;
    }

    @Override
    protected void action(Request request) {
        if(request.getSetter() != null)
            request.getSetter().action(data.update(request.getObject()));
        else 
            data.update(request.getObject());
    }
}
