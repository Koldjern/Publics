package com.model.dtos.employee.set;

public class EmployeeCreateSet {
    private String password;
    private String name;
    private String phonenumber;
    private String email;
    private String occupation;
    private Double limit;
    private byte[] image;

    public EmployeeCreateSet(String name, String phonenumber, String email, String occupation, Double limit, byte[] image, String password) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.occupation = occupation;
        this.limit = limit;
        this.image = image;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
