package com.data.dao;

import java.util.List;
import com.data.ConnectionData;
import com.data.actions.general.ReadAll;
import com.data.dao.interfaces.AgreementOpenActions;
import com.data.dao.interfaces.CustomerActions;
import com.data.dao.interfaces.EmployeeActions;
import com.model.dtos.agreementclosed.set.AgreementClosedReadSet;
import com.model.dtos.agreementopen.get.AgreementOpenCreateGet;
import com.model.dtos.agreementopen.get.AgreementOpenReadGet;
import com.model.dtos.agreementopen.set.AgreementOpenCreateSet;
import com.model.dtos.agreementopen.set.AgreementOpenDelete;
import com.model.dtos.agreementopen.set.AgreementOpenUpdateSet;
import com.model.entities.Agreement;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;

//Karl

//klasse der håndterer data for åbne aftaler og laver stored procedures til databasen
public class AgreementOpenData implements AgreementOpenActions {
    private ConnectionData db;
    private CustomerActions customerData;
    private EmployeeActions employeeData;
    public AgreementOpenData(ConnectionData db, CustomerActions customerData, EmployeeActions employeeData) {
        this.db = db;
        this.customerData = customerData;
        this.employeeData = employeeData;
    }
    //opretter en aftale
    @Override
    public Agreement create(Agreement agreement) {
        AgreementOpenCreateSet set = new AgreementOpenCreateSet(agreement.getFixedTerms(), agreement.getStartValue(), agreement.getStartAgreement(),
        agreement.getRki().toString(), agreement.getCustomer().getId(), agreement.getEmployee().getId(), agreement.getVehicle().getId(), agreement.getTotalRate());
        AgreementOpenCreateGet get = db.querySingle("{call Trade.uspOpenAgreementInsert(?,?,?,?,?,?,?,?)}", AgreementOpenCreateGet::new, set);
        if(get == null)
            return null;
        agreement.setId(get.getId());
        return agreement;
    }
    //læser en aftale
    @Override
    public Agreement read(Integer id) {
        return getAgreement(db.querySingle("{call Trade.uspOpenAgreementGet(?)}", AgreementOpenReadGet::new, new AgreementClosedReadSet(id)));
    }
    //læser alle aftaler
    @Override
    public List<Agreement> readAll(Void v) {
        return ReadAll.DtoToModels(db.queryMultiple("{call Trade.uspOpenAgreementGetAll()}", AgreementOpenReadGet::new), this::getAgreement);
    }
    //opdaterer en aftale
    @Override
    public Boolean update(Agreement a) {
        return db.executeQuery("{call Trade.uspOpenAgreementUpdate(?,?,?,?,?,?,?,?,?)}", new AgreementOpenUpdateSet(a.getId(), a.getFixedTerms(), a.getStartValue(), a.getStartAgreement(), a.getRki().toString(), a.getCustomer().getId(), a.getEmployee().getId(), a.getVehicle().getId(), a.getTotalRate()));
    }
    //sletter en aftale
    @Override
    public Boolean delete(Agreement agreement) {
        return db.executeQuery("{call Trade.uspOpenAgreementDelete(?)}", new AgreementOpenDelete(agreement.getId()));
    }
    private Agreement getAgreement(AgreementOpenReadGet get) {
        return new Agreement(
            get.getAgreementId(),
            get.getFixedTerms(),
            get.getStartValue(),
            get.getStartAgreement(),
            Rating.valueOf(get.getRki()),
            customerData.read(get.getCustomerId()),
            employeeData.read(get.getEmployeeId()),
            new Vehicle(get.getVehicleId(), get.getVehicleName(), get.getPrice(), get.getVehicleImage()),
            get.getTotalRate()
        );
    }
}
