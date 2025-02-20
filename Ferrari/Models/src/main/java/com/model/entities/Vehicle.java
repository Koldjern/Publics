package com.model.entities;
//jakob
public class Vehicle {
    private int id;
    private String name;
    private double price;
    private byte[] image;

// Konstruktør 
    public Vehicle(int id, String name, double price, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public Vehicle(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Vehicle() {
        image = new byte[0];
    }
    // Get metode
    public int getId () {
        return id;
    }
// Set metode
    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public double getPrice () {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
    public void copy(Vehicle other) {
        setId(other.getId());
        setPrice(other.getPrice());
        setName(other.getName());
        setImage(other.getImage());
    }
}
