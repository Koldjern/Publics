package com.logic.services.invoice;

import com.data.actions.Data;
import com.data.dao.interfaces.InvoiceActions;
import com.logic.Simples.*;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.services.enums.ServiceType;

//karl
// Handlers for invoice som bruger COR til at håndtere CRUD
public class InvoiceService extends HandlerObject {
    private HandlerHolder holder;


    public InvoiceService(InvoiceActions data) {
        // creates all handlers and adds them to each other
        holder = new SimpleHolder(
                new SimpleCreateHandler(data),
                new SimpleReadHandler(data),
                new SimpleReadAllHandler(data),
                new SimpleUpdateHandler(data),
                new SimpleDeleteHandler(data)
        );
    }
    // checks if the request is for invoice
    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.Invoice;
    }

    @Override
    public void action(Request request) {
        // go down new COR with CRUD
        holder.query(request);
    }
}