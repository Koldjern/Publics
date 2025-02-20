package com.presentation.mvc.models.vehicle;

import java.util.ArrayList;
import java.util.List;
import com.model.entities.Vehicle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//jakob
public class VehicleModel extends Vehicle {
    private IntegerProperty vehicleIdProp;
    private StringProperty vehicleNameProp;
    private DoubleProperty priceProp;
    private ObjectProperty<byte[]> imageProp;
    private boolean empty;
    public VehicleModel() {
// Konstruktør
        vehicleIdProp = new SimpleIntegerProperty();
        vehicleNameProp = new SimpleStringProperty();
        priceProp = new SimpleDoubleProperty();  
        imageProp = new SimpleObjectProperty<>();
        empty = false;
    }
    public VehicleModel(boolean empty) {
// Tjekker om modellen er tom
        this();
        this.empty = empty;
    }
// Konstruktør til at konventere det eksisterende vehicle objekt
    public VehicleModel(Vehicle vehicle) {
        this();
        setId(vehicle.getId());
        setName(vehicle.getName());
        setPrice(vehicle.getPrice());
        setImage(vehicle.getImage());
    }
    public boolean getEmpty() {
        return empty;
    }
    @Override
    public byte[] getImage() {
// metode til billede
        return imageProp.get();
    }
    @Override
    public void setImage(byte[] image) {
        if(image != null)
            imageProp.set(image);
        else
            imageProp.set(new byte[0]);
    }
    @Override
    public int getId() {
// metode til at få Id'et
        return vehicleIdProp.get();
    } 
    @Override
    public void setId(int vehicleIdProp) {
        this.vehicleIdProp.set(vehicleIdProp);
    }
    @Override
    public String getName() {
        return vehicleNameProp.get();
    }
    @Override
    public void setName(String name) {
        this.vehicleNameProp.set(name);
    }
    @Override
    public double getPrice() {
        return priceProp.get();
    }
    @Override
    public void setPrice(double price) {
        this.priceProp.set(price);
    }
    public IntegerProperty vehicleIdProperty() {
        return vehicleIdProp;
    }
    public StringProperty nameProperty() {
        return vehicleNameProp;
    }
    public DoubleProperty priceProperty() {
        return priceProp;
    }
    public ObjectProperty<byte[]> imageProperty() {
        return imageProp;
    }

    public static List<VehicleModel> makeModels(List<Vehicle> vehicles) {
        List<VehicleModel> models = new ArrayList<>();
        for (Vehicle vehicle : vehicles)
            models.add(new VehicleModel(vehicle));
        return models;
    }
    public static List<Object> makeModelsAsObjects(List<Vehicle> vehicles) {
        List<Object> models = new ArrayList<>();
        for(Vehicle vehicle : vehicles)
            models.add(new VehicleModel(vehicle));
        return models;
    }

    public void unbindAll() {
        vehicleIdProp.unbind();
        vehicleNameProp.unbind();
        priceProp.unbind();
        imageProp.unbind();
    }

    public String toString() {
        return getName() + " " + getPrice();

    }
}
