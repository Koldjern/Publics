package com.model.dtos.agreementopen.get;

import java.sql.Date;


public class AgreementOpenReadGet {
    private int fixedTerms;
    private double startValue;
    private Date startAgreement;
    private String rki;
    private int customerId;
    private int employeeId;
    private int vehicleId;
    private double totalRate;
    private int agreementId;
    private String vehicleName;
    private byte[] vehicleImage;
    private double price;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public byte[] getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(byte[] vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
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
