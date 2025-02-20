package com.data.dao.interfaces;

import java.util.List;

import com.data.actions.general.*;
import com.model.entities.Vehicle;

public interface VehicleActions extends Read<Vehicle, Integer>, ReadAll<List<Vehicle>, Void>, Update<Boolean, Vehicle>, 
Delete<Boolean, Vehicle>, Create<Vehicle, Vehicle>{
    
}
