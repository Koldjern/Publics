package com.data.dao.interfaces;

import com.data.actions.general.*;
import com.model.entities.Invoice;

import java.util.List;


public interface InvoiceActions extends Read<List<Invoice>, Integer>, ReadAll<List<Invoice>, Void>,
        Update<Boolean, Invoice>, Delete<Boolean, Invoice>, Create<Invoice, Invoice> {
    
}
