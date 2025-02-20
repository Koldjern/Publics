package com.model.dtos.vehicle.set;

public class VehicleReadSet {
    private int id;
    public VehicleReadSet(int id) {
        this.id = id;
    }
    private int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
}
