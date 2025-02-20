package com.model.dtos.employee.set;

public class EmployeeDeleteSet {
    private int id;
    public EmployeeDeleteSet(int id) {
        this.id = id;
    }
    public int isId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
