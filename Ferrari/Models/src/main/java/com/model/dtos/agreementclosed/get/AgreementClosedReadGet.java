package com.model.dtos.agreementclosed.get;

import java.sql.Date;

public class AgreementClosedReadGet {
    private int agreementId;
    private int fixedTerms;
    private double startValue;
    private Date startAgreement;
    private String rki;
    private int customerId;
    private int employeeId;
    private int saleId;
    private double totalRate;
    private Date start;
    private Date end;
    private double endPrice;
    private String saleName;
    private double salePrice;
    private byte[] vehicleImage;


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

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public byte[] getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(byte[] vehicleImage) {
        this.vehicleImage = vehicleImage;
    }
}
