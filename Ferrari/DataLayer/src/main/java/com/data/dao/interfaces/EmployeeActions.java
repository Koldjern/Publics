package com.data.dao.interfaces;

import java.util.List;
import com.data.actions.general.*;
import com.data.actions.specifics.CheckData;
import com.data.actions.specifics.UserExtra;
import com.model.entities.Employee;

public interface EmployeeActions extends 
Create<Employee, Employee>, 
Read<Employee, Integer>,
ReadAll<List<Employee>, Void>, 
Update<Boolean, Employee>, 
Delete<Boolean, Employee>, 
UserExtra<Employee, Employee>,
CheckData<Employee> {}
