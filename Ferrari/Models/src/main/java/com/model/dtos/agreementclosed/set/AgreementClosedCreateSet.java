package com.model.dtos.agreementclosed.set;

import java.sql.Date;

public class AgreementClosedCreateSet {
    private int fixedTerms;
    private double startValue;
    private Date startAgreement;
    private String rki;
    private int customerId;
    private int employeeId;
    private double totalRate;
    private Date start;
    private Date end;
    private String vehicleName;
    private double vehiclePrice;
    private double endPrice;
    private byte[] vehicleImage;

    public AgreementClosedCreateSet(int fixedTerms, double startValue, Date startAgreement, String rki, int customerId, int employeeId, double totalRate, Date start, Date end, String vehicleName, double vehiclePrice, double endPrice, byte[] vehicleImage) {
        this.fixedTerms = fixedTerms;
        this.startValue = startValue;
        this.startAgreement = startAgreement;
        this.rki = rki;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.totalRate = totalRate;
        this.start = start;
        this.end = end;
        this.vehicleName = vehicleName;
        this.vehiclePrice = vehiclePrice;
        this.endPrice = endPrice;
        this.vehicleImage = vehicleImage;
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

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public byte[] getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(byte[] vehicleImage) {
        this.vehicleImage = vehicleImage;
    }
}
