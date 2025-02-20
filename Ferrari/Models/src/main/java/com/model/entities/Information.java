package com.model.entities;
//Magnus
public class Information {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    // Kunstruktore for Information klassen 
    public Information(int id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Information(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Information(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Information(int id) {
        this.id = id;
    }
    public Information() {}

    // Gettere og settere for Information klassen
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
