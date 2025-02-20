package com.logic.services.agreements;

import com.data.actions.Data;
import com.data.dao.interfaces.AgreementOpenActions;
import com.logic.Simples.SimpleCreateHandler;
import com.logic.Simples.SimpleDeleteHandler;
import com.logic.Simples.SimpleReadAllHandler;
import com.logic.Simples.SimpleReadHandler;
import com.logic.Simples.SimpleUpdateHandler;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.services.enums.ServiceType;
//Karl
// Handlers for agreementOpen som bruger COR til at håndtere CRUD
public class AgreementOpenService extends HandlerObject {
    private HandlerHolder holder;

    public AgreementOpenService(AgreementOpenActions data) {
        //creates all handlers and adds them to each other
        holder = new SimpleHolder(
                new SimpleCreateHandler(data),
                new SimpleReadHandler(data),
                new SimpleReadAllHandler(data),
                new SimpleUpdateHandler(data),
                new SimpleDeleteHandler(data)
        );
    }

    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.AgreementOpen;
    }

    @Override
    public void action(Request request) {
        // go down new COR with CRUD
        holder.query(request);
    }
}
