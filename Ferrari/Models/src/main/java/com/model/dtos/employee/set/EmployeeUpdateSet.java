package com.model.dtos.employee.set;

public class EmployeeUpdateSet {
    private String name;
    private String phonenumber;
    private String email;
    private String occupation;
    private double limit;
    private byte[] image;

    public EmployeeUpdateSet(String name, String phonenumber, String email, String occupation, double limit, byte[] image) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.occupation = occupation;
        this.limit = limit;
        this.image = image;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
