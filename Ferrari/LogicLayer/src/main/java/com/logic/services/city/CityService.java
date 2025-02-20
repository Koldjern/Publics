package com.logic.services.city;

import com.data.actions.Data;
import com.data.dao.interfaces.CityActions;
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
// anders
//city has a lot of services it wont be using them tho because everything will be set on the db script, so only getall is used
public class CityService extends HandlerObject {
    private HandlerHolder holder;

    public CityService(CityActions data) {
        //creates all handlers and adds them to each other
        holder = new SimpleHolder(
                new SimpleReadAllHandler(data)
        );
    }

    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.City;
    }

    @Override
    public void action(Request request) {
        // go down new COR with CRUD
        holder.query(request);
    }
    
}
