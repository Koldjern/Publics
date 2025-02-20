package com.model.entities;
//anders
public class City {
    private int zip;
    private String name;

    public City() {
    }

    public City(int zip, String name) {
        this.zip = zip;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    @Override
    public String toString() {
        return "PostNr : " + zip + ". Navn : " + name;
    }
}
