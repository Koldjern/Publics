package com.data.dao.interfaces;

import com.data.actions.general.*;
import com.model.entities.Customer;

import java.util.List;

public interface CustomerActions extends Read<Customer, Integer>, ReadAll<List<Customer>, Void>,
        Update<Boolean, Customer>, Delete<Boolean, Customer>, Create<Customer, Customer> {
    
}
