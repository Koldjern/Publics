package com.data.dao;

import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.CustomerActions;
import com.model.dtos.customer.get.CustomerCreateGet;
import com.model.dtos.customer.get.CustomerReadGet;
import com.model.dtos.customer.set.CustomerCreateSet;
import com.model.dtos.customer.set.CustomerDeleteSet;
import com.model.dtos.customer.set.CustomerReadSet;
import com.model.dtos.customer.set.CustomerUpdateSet;
import com.model.entities.City;
import com.model.entities.Customer;
import java.util.List;
//magnus
    public class CustomerData implements CustomerActions {
    // Database connection
    private ConnectionData db;

    // Constructor
    public CustomerData(ConnectionData db) {
        this.db = db;
    }

    // Metode til at oprette en kunde
    @Override
    public Customer create(Customer customer) {
        CustomerCreateSet set = new CustomerCreateSet(customer.getName(), customer.getPhoneNumber(), customer.getEmail(), customer.getAddress(), customer.getCpr(), customer.getCity().getZip());
        CustomerCreateGet get = db.querySingle("{call Person.uspCustomerInsert(?,?,?,?,?,?)}", CustomerCreateGet::new, set);
        if(get == null)
            return null;
        customer.setId(get.getInformationId());
        return customer;
    } 

    // Metode til at læse en kunde med et bestemt id
    @Override
    public Customer read(Integer id) {
        return getCustomer(db.querySingle("{call Person.uspCustomerGet(?)}", CustomerReadGet::new, new CustomerReadSet(id)));
    }

    // Metode til at læse alle kunder
    @Override
    public List<Customer> readAll(Void parameter) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Person.uspCustomerGetAll()}", CustomerReadGet::new), this::getCustomer);
    }

    // Metode til at opdatere en kunde
    @Override
    public Boolean update(Customer c) {
        return db.executeQuery("{call Person.uspCustomerUpdate(?,?,?,?,?,?,?)}", new CustomerUpdateSet(c.getName(), c.getPhoneNumber(), c.getEmail(), c.getAddress(), c.getCpr(), c.getCity().getZip(), c.getId()));
    }

    // Metode til at slette en kunde med et bestemt id
    @Override
    public Boolean delete(Customer customer) {
        return db.executeQuery("{call Person.uspCustomerDelete(?)}", new CustomerDeleteSet(customer.getId()));
    }
    private Customer getCustomer(CustomerReadGet get) {
        return new Customer(
            get.getInformationId(), get.getInformationName(), get.getPhonenumber(), get.getEmail(), get.getAddress(), 
            get.getCpr(), new City(get.getZip(), get.getCityName())
        );
    }
}
