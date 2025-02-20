package com.model.dtos.invoice.set;

import java.sql.Date;

public class InvoiceUpdateSet {
    private int agreementId;
    private int number;
    private Date dateStart;
    private Date dateEnd;
    private double plus;
    private double minus;
    private double ultimoValue;
    private double primoValue;
    private double payed;
    private String details;

    public InvoiceUpdateSet(int agreementId, int number, Date dateStart, Date dateEnd, double plus, double minus, double ultimoValue, double primoValue, double payed, String details) {
        this.agreementId = agreementId;
        this.number = number;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.plus = plus;
        this.minus = minus;
        this.ultimoValue = ultimoValue;
        this.primoValue = primoValue;
        this.payed = payed;
        this.details = details;
    }

    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getPlus() {
        return plus;
    }

    public void setPlus(double plus) {
        this.plus = plus;
    }

    public double getMinus() {
        return minus;
    }

    public void setMinus(double minus) {
        this.minus = minus;
    }

    public double getUltimoValue() {
        return ultimoValue;
    }

    public void setUltimoValue(double ultimoValue) {
        this.ultimoValue = ultimoValue;
    }

    public double getPrimoValue() {
        return primoValue;
    }

    public void setPrimoValue(double primoValue) {
        this.primoValue = primoValue;
    }

    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
