package com.data.dao;
import java.util.List;
import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.InvoiceActions;
import com.model.dtos.invoice.get.InvoiceReadGet;
import com.model.dtos.invoice.set.InvoiceCreateSet;
import com.model.dtos.invoice.set.InvoiceDeleteSet;
import com.model.dtos.invoice.set.InvoiceReadSet;
import com.model.dtos.invoice.set.InvoiceUpdateSet;
import com.model.entities.Invoice;
//karl 
public class InvoiceData implements InvoiceActions {
    private ConnectionData db;
    public InvoiceData(ConnectionData db) {
        this.db = db;
    }
    //opretter en faktura i databasen
    @Override
    public Invoice create(Invoice i) {
        InvoiceCreateSet set = new InvoiceCreateSet(i.getNumber(), i.getDatestart(), i.getDateend(), i.getPlus(), i.getMinus(), 
        i.getUltimovalue(), i.getPrimoprice(), i.getPayed(), i.getDetails());
        return db.executeQuery("{call Trade.uspInvoiceInsert(?,?,?,?,?,?,?,?,?,?)}", set) ? i : null;
    }
    //læser en faktura
    @Override
    public List<Invoice> read(Integer id) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Trade.uspInvoiceGetOne(?)}", InvoiceReadGet::new, new InvoiceReadSet(id)), this::getInvoice);
    }
    //læser alle fakturaer
    @Override
    public List<Invoice> readAll(Void v) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Trade.uspInvoiceGetAll()}", InvoiceReadGet::new), this::getInvoice);
    }
    //opdaterer en faktura
    @Override
    public Boolean update(Invoice i) {
        return db.executeQuery("{call Trade.uspInvoiceUpdate(?,?,?,?,?,?,?,?)}", new InvoiceUpdateSet(i.getAgreement().getId(), i.getNumber(), i.getDatestart(), i.getDateend(), i.getPlus(), i.getMinus(), i.getUltimovalue(), i.getPrimoprice(), i.getPayed(), i.getDetails()));
    }
    //sletter en faktura
    @Override
    public Boolean delete(Invoice invoice) {
        return db.executeQuery("{call Trade.uspInvoiceDelete(?)}", new InvoiceDeleteSet(invoice.getAgreement().getId()));
    }
    private Invoice getInvoice(InvoiceReadGet get) {
        return new Invoice(get.getNumber(), get.getDateStart(), get.getDateEnd(), get.getPlus(), get.getMinus(), get.getUltimoValue(),
        get.getPrimoValue(), get.getDetails());
    }
    
}
