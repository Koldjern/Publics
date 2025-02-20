package com.data.dao;

import com.data.ConnectionData;
import com.data.dao.interfaces.RatesActions;
import com.model.entities.Agreement;
import com.rki.bank.InterestRate;
import com.rki.rki.CreditRator;
//Jakob
public class RatesData implements RatesActions {
// Klasse for dataoperation CRUD på rate
    private ConnectionData db;
// Konstruktør som har db som parameter  
    public RatesData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Agreement read(Agreement agreement) {
// Opretter nyt agreement objekt
        Agreement newAgreement = new Agreement();
// Sætter dagsrente i det nye agreement objekt
        newAgreement.setDaysRate(InterestRate.i().todaysRate());
// Henter kreditvurdering i det nye agreement objekt
        newAgreement.setRki(CreditRator.i().rate(agreement.getCustomer().getCpr()));
// returner den nye agreement
        return newAgreement;
    }
}
