package com.data.dao;

import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.EmployeeActions;
import com.model.dtos.employee.get.EmployeeCreateGet;
import com.model.dtos.employee.get.EmployeeLoginGet;
import com.model.dtos.employee.get.EmployeeReadGet;
import com.model.dtos.employee.set.EmployeeCheckSet;
import com.model.dtos.employee.set.EmployeeCheckUpdateSet;
import com.model.dtos.employee.set.EmployeeCreateSet;
import com.model.dtos.employee.set.EmployeeDeleteSet;
import com.model.dtos.employee.set.EmployeeLoginSet;
import com.model.dtos.employee.set.EmployeeReadSet;
import com.model.dtos.employee.set.EmployeeUpdateSelfSet;
import com.model.dtos.employee.set.EmployeeUpdateSet;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import java.util.List;
//Anders
public class EmployeeData implements EmployeeActions {
    private ConnectionData db;
    public EmployeeData(ConnectionData db) {
        this.db = db;
    }

    @Override
    public Employee create(Employee e) {
        EmployeeCreateSet set = new EmployeeCreateSet(e.getName(), e.getPhoneNumber(), e.getEmail(), e.getOccupation().toString(),
        e.getLoanLimit(), e.getImage(), e.getPassword());
        EmployeeCreateGet get = db.querySingle("{call Person.uspEmployeeInsert(?,?,?,?,?,?,?)}", EmployeeCreateGet::new, set);
        if(get == null)
            return null;
        e.setId(get.getInformationId());
        return e;
    }

// no password will be given since thats knowledge only the user should have, it will be given in a login
    @Override
    public Employee read(Integer id) {
        return getEmployee(db.querySingle("{call Person.uspEmployeeGet(?)}", EmployeeReadGet::new, new EmployeeReadSet(id)));
    }

    // no password will be given since thats knowledge only the user should have, it will be given in a login
    @Override
    public List<Employee> readAll(Void nothing) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Person.uspEmployeeGetAll()}", EmployeeReadGet::new), this::getEmployee);
    }

    @Override
    public Boolean update(Employee e) {
        return db.executeQuery("{call Person.uspEmployeeUpdate(?,?,?,?,?,?,?)}", new EmployeeUpdateSet(e.getName(), e.getPhoneNumber(), e.getEmail(), e.getOccupation().toString(), e.getLoanLimit(), e.getImage()));
    }

    @Override
    public Boolean delete(Employee employee) {
        return db.executeQuery("{call Person.uspEmployeeDelete(?)}", new EmployeeDeleteSet(employee.getId()));
    }

    @Override
    public Employee login(Employee login) {
        EmployeeLoginSet set = new EmployeeLoginSet(login.getEmail(), login.getPassword());
        EmployeeLoginGet get = db.querySingle("{call Person.uspLogin(?,?)}", EmployeeLoginGet::new, set);
        if(get == null)
            return null;
        login.setId(get.getInformationId());
        login.setImage(get.getEmployeeImage());
        login.setLoanLimit(get.getLimit());
        login.setName(get.getInformationName());
        login.setOccupation(Occupation.valueOf(get.getOccupation()));
        login.setPhoneNumber(get.getPhoneNumber());
        return login;
    }

    @Override
    public Boolean updateSelf(Employee e) {
        EmployeeUpdateSelfSet set = new EmployeeUpdateSelfSet(e.getName(), e.getPhoneNumber(), e.getEmail(), e.getOccupation().toString(),
        e.getLoanLimit(), e.getImage(), e.getPassword());
        return db.executeQuery("{call Person.uspUpdateSelfEmployee(?,?,?,?,?,?,?)}", set);
    }

    @Override
    public boolean check(Employee check) {
        return db.executeQuery("{call Person.uspEmployeeCheckEmail(?)}", new EmployeeCheckSet(check.getEmail()));
    }

    @Override
    public boolean checkUpdate(Employee check) {
        return db.executeQuery("{call Person.uspEmployeeCheckEmailUpdate(?,?)}", new EmployeeCheckUpdateSet(check.getEmail(), check.getId()));
    }
    private Employee getEmployee(EmployeeReadGet get) {
        return new Employee(
            get.getId(), get.getInformationName(), get.getPhoneNumber(), get.getEmail(), Occupation.valueOf(get.getOccupation()), get.getLimit(), get.getEmployeeImage()
        );
    }
}
