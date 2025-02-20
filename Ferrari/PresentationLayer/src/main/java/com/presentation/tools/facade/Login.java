package com.presentation.tools.facade;

import com.model.entities.Employee;
//anders
// a simple login holder
public class Login implements LoginManager{
    private Employee employee;
    @Override
    public void login(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Employee getLoggedIn() {
        return employee;
    }
}
