package com.logic.services.vehicle;

import com.data.actions.Data;
import com.data.dao.interfaces.VehicleActions;
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
//Jakob
// Klassen håndtere forespørgsler med COR
public class VehicleService extends HandlerObject {
    private HandlerHolder holder;

    public VehicleService(VehicleActions data) {
// Håndtere CRUD har Data som objekt
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
        return request.getType() == ServiceType.Vehicle;
    }

    @Override
    public void action(Request request) {
        holder.query(request);
    }
}
