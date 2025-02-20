package com.presentation.tools.facade;

import com.model.entities.Employee;
//anders
//login interface for Facade, so it could be setup with different kinds of logins
public interface LoginManager {
    public void login (Employee employee);
    public Employee getLoggedIn();
}
