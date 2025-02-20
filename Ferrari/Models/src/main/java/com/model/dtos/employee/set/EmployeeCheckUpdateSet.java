package com.model.dtos.employee.set;

public class EmployeeCheckUpdateSet {
    private int id;
    private String email;

    public EmployeeCheckUpdateSet(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
