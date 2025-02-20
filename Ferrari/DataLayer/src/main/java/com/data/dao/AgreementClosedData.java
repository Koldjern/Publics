package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.AgreementClosedActions;
import com.data.dao.interfaces.CustomerActions;
import com.data.dao.interfaces.EmployeeActions;
import com.data.dao.interfaces.InvoiceActions;
import com.model.dtos.agreementclosed.get.AgreementClosedCreateGet;
import com.model.dtos.agreementclosed.get.AgreementClosedReadGet;
import com.model.dtos.agreementclosed.set.AgreementClosedCreateSet;
import com.model.dtos.agreementclosed.set.AgreementClosedDeleteSet;
import com.model.dtos.agreementclosed.set.AgreementClosedReadSet;
import com.model.entities.Agreement;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;
//Karl
//klasse der håndterer data for lukkede aftaler
public class AgreementClosedData implements AgreementClosedActions{
    private ConnectionData db;
    private InvoiceActions invoiceData;
    private CustomerActions customerData;
    private EmployeeActions employeeData;
    public AgreementClosedData(ConnectionData db, InvoiceActions invoiceData, CustomerActions customerData, EmployeeActions employeeData) {
        this.db = db;
        this.invoiceData = invoiceData;
        this.customerData = customerData;
        this.employeeData = employeeData;
    }
    //opretter en aftale
    @Override
    public Agreement create(Agreement agreement) {
        if(agreement.getId() != 0)
            delete(agreement);
        AgreementClosedCreateSet set = new AgreementClosedCreateSet(agreement.getFixedTerms(), agreement.getStartValue(), agreement.getStartAgreement(),
        agreement.getRki().toString(), agreement.getCustomer().getId(), agreement.getEmployee().getId(), 
        agreement.getTotalRate(), agreement.getStart(), agreement.getEnd(), agreement.getVehicle().getName(), 
        agreement.getVehicle().getPrice(), agreement.getEndPrice(), agreement.getVehicle().getImage());
        AgreementClosedCreateGet get = db.querySingle("{call Trade.uspClosedAgreementInsert(?,?,?,?,?,?,?,?,?,?,?,?,?)}", AgreementClosedCreateGet::new, set);
        if(get == null)
            return null;
        agreement.setId(get.getId());
        agreement.getVehicle().setId(get.getSaleId());
        for(Invoice invoice : agreement.getPayments())
            invoiceData.create(invoice);
        return agreement;
    }
    //læser en aftale 
    @Override
    public Agreement read(Integer id) {
        return getAgreement(db.querySingle("{call Trade.uspClosedAgreementGet(?)}", AgreementClosedReadGet::new, new AgreementClosedReadSet(id)));
    }
    //læser alle aftaler
    @Override
    public List<Agreement> readAll(Void v) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Trade.uspClosedAgreementGetAll()}", AgreementClosedReadGet::new), this::getAgreement);
    }
    //sletter en aftale
    @Override
    public Boolean delete(Agreement agreement) {
        return db.executeQuery("{call Trade.uspClosedAgreementDelete(?)}", new AgreementClosedDeleteSet(agreement.getId()));
    }
    private Agreement getAgreement(AgreementClosedReadGet get) {
        return new Agreement(
            get.getAgreementId(),
            get.getFixedTerms(),
            get.getStartValue(),
            get.getStartAgreement(),
            Rating.valueOf(get.getRki()),
            customerData.read(get.getCustomerId()),
            employeeData.read(get.getEmployeeId()),
            get.getStart(),
            get.getEnd(),
            new Vehicle(get.getSaleId(), get.getSaleName(), get.getSalePrice(), get.getVehicleImage()),
            get.getEndPrice(),
            get.getTotalRate(),
            invoiceData.read((get.getAgreementId())
        ));
    }
}
