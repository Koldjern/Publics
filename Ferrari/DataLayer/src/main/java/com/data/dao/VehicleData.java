package com.data.dao;

import java.util.List;
import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.VehicleActions;
import com.model.dtos.vehicle.get.VehicleCreateGet;
import com.model.dtos.vehicle.get.VehicleReadGet;
import com.model.dtos.vehicle.set.VehicleCreateSet;
import com.model.dtos.vehicle.set.VehicleDeleteSet;
import com.model.dtos.vehicle.set.VehicleReadSet;
import com.model.entities.Vehicle;

// Jakob DataAccessObeject
public class VehicleData implements VehicleActions {
// Opstiller reference til ConnectionData
    private ConnectionData db;
// Laver en db parameter 
    public VehicleData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Vehicle create(Vehicle vehicle) {
        VehicleCreateSet set = new VehicleCreateSet(vehicle.getName(), vehicle.getPrice(), vehicle.getImage());
        VehicleCreateGet get = db.querySingle("{call Trade.uspVehicleInsert(?,?,?)}", VehicleCreateGet::new, set);
        if(get == null)
            return null;
        vehicle.setId(get.getVehicleId());
        return vehicle;
    }

    @Override
    public Vehicle read(Integer id) {
        return getVehicle(db.querySingle("{call Trade.uspVehicleGet(?)}", VehicleReadGet::new, new VehicleReadSet(id)));
    }

    @Override
    public List<Vehicle> readAll(Void v) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Trade.uspVehicleGetAll()}", VehicleReadGet::new), this::getVehicle);
    }
    @Override
    public Boolean update(Vehicle vehicle) {
        return db.executeQuery("{call Trade.uspVehicleUpdate(?, ?, ?, ?)}", new VehicleReadSet(vehicle.getId()));
    }

    @Override
    public Boolean delete(Vehicle vehicle) {
        return db.executeQuery("{call Trade.uspVehicleDelete(?)}", new VehicleDeleteSet(vehicle.getId()));
    }
    private Vehicle getVehicle(VehicleReadGet get) {
        return new Vehicle(get.getId(), get.getVehicleName(), get.getPrice(), get.getVehicleImage());
    }
}

