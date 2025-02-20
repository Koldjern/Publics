package com.model.entities;

import com.model.enums.Occupation;

//Anders
public class Employee extends Information {
    private Occupation occupation;
    private String password;
    private Double loanLimit;
    private byte[] image;

    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
        image = new byte[0];
    }

    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation, double loanLimit, byte[] image) {
        this(id, name, phoneNumber, email, occupation);
        this.loanLimit = loanLimit;
        this.image = image;
    }
    public Employee() {}

    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
    public Occupation getOccupation() {
        return occupation;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public double getLoanLimit() {
        return loanLimit;
    }
    
    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }
    public void copy(Employee other) {
        setLoanLimit(other.getLoanLimit());
        setOccupation(other.getOccupation());
        setPassword(other.getPassword());
        setImage(other.getImage());
        setEmail(other.getEmail());
        setName(other.getName());
        setPhoneNumber(other.getPhoneNumber());
    }
}
