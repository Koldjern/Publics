package com.logic.services;

import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
//jakob
public class ServiceManager extends HandlerHolder {
    public ServiceManager(HandlerObject... handlers) {
        super(handlers);
    }
    @Override
    public void query(Request request) {
// Metoden håndtere forespørgsler fra Request klassen
        if(request.getCrud() != null && request.getType() != null && root != null)
            root.query(request);
    }
}
