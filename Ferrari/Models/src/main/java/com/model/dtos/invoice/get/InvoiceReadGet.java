package com.model.dtos.invoice.get;

import java.sql.Date;

public class InvoiceReadGet {
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
