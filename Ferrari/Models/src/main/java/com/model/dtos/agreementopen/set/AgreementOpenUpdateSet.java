package com.model.dtos.agreementopen.set;

import java.sql.Date;

public class AgreementOpenUpdateSet {
    private int id;
    private int fixedTerms;
    private double startValue;
    private Date startAgreement;
    private String rki;
    private int customerId;
    private int employeeId;
    private int vehicleId;
    private double totalRate;

    public AgreementOpenUpdateSet(int id, int fixedTerms, double startValue, Date startAgreement, String rki, int customerId, int employeeId, int vehicleId, double totalRate) {
        this.id = id;
        this.fixedTerms = fixedTerms;
        this.startValue = startValue;
        this.startAgreement = startAgreement;
        this.rki = rki;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.vehicleId = vehicleId;
        this.totalRate = totalRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFixedTerms() {
        return fixedTerms;
    }

    public void setFixedTerms(int fixedTerms) {
        this.fixedTerms = fixedTerms;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public Date getStartAgreement() {
        return startAgreement;
    }

    public void setStartAgreement(Date startAgreement) {
        this.startAgreement = startAgreement;
    }

    public String getRki() {
        return rki;
    }

    public void setRki(String rki) {
        this.rki = rki;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }
}
