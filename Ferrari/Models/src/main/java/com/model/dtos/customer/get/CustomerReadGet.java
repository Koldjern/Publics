package com.model.dtos.customer.get;

public class CustomerReadGet {
    private int informationId;
    private String informationName;
    private String phonenumber;
    private String email;
    private String address;
    private String cpr;
    private String cityName;
    private int zip;
    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }
    public int getInformationId() {
        return informationId;
    }
    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getInformationName() {
        return informationName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCpr() {
        return cpr;
    }

    public String getCityName() {
        return cityName;
    }

    public int getZip() {
        return zip;
    }
}
