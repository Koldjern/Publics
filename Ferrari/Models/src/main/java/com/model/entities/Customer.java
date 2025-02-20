package com.model.entities;

public class Customer extends Information{
    private String address;
    private String cpr;
    private City city;
    //Magnus

    // Kunstruktor for Customer klassen
    // kalder superklassens konstruktor
    public Customer(int id, String name, String phoneNumber, String email, String address, String cpr, City city) {
        super(id, name, phoneNumber, email);
        this.address = address;
        this.cpr = cpr;
        this.city = city;
    }

    // Gettere og settere for Customer klassen
    public Customer() {}
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
     
    // Metode til at kopiere en customer
    public void copy(Customer other) {
        setEmail(other.getEmail());
        setName(other.getName());
        setPhoneNumber(other.getPhoneNumber());
        setAddress(other.getAddress());
        setCpr(other.getCpr());
        setCity(other.getCity());
    }



}