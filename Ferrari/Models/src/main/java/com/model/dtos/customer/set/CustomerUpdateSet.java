package com.model.dtos.customer.set;

public class CustomerUpdateSet {
    private int id;
    private String name;
    private String phonenumber;
    private String email;
    private String address;
    private String cpr;
    private int cityZip;

    public CustomerUpdateSet(String name, String phonenumber, String email, String address, String cpr, int cityZip, int id) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.cpr = cpr;
        this.cityZip = cityZip;
    }

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public int getCityZip() {
        return cityZip;
    }

    public void setCityZip(int cityZip) {
        this.cityZip = cityZip;
    }
}
