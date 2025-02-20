package com.model.entities;

import java.sql.Date;
//karl
//constructor for invoice
public class Invoice {
    private Agreement agreement;
    private int number;
    private Date dateStart;
    private Date dateEnd;
    private double plus;
    private double minus;
    private double ultimoValue;
    private double primoPrice;
    private String details;
    private double payed;

    public Invoice () {

    }
    public Invoice(Agreement agreement, int number, Date datestart, Date dateend, double plus, double minus,
            double ultimovalue, double primoprice, double payed, String details) {
        this.agreement = agreement;
        this.number = number;
        this.dateStart = datestart;
        this.dateEnd = dateend;
        this.plus = plus;
        this.minus = minus;
        this.ultimoValue = ultimovalue;
        this.primoPrice = primoprice;
        this.details = details;
        this.payed = payed;
    }
    public Invoice(int number, Date datestart, Date dateend, double plus, double minus,
        double ultimovalue, double primoprice, double payed, String details) {
        this.number = number;
        this.dateStart = datestart;
        this.dateEnd = dateend;
        this.plus = plus;
        this.minus = minus;
        this.ultimoValue = ultimovalue;
        this.primoPrice = primoprice;
        this.details = details;
        this.payed = payed;
    }
    
    public Invoice( int number, Date datestart, Date dateend, double plus, double minus,
            double ultimovalue, double primoprice, String details) {
        this.number = number;
        this.dateStart = datestart;
        this.dateEnd = dateend;
        this.plus = plus;
        this.minus = minus;
        this.ultimoValue = ultimovalue;
        this.primoPrice = primoprice;
        this.details = details;
    }

    public Agreement getAgreement() {
        return agreement;
    }
    //gettere og settere
    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDatestart() {
        return dateStart;
    }

    public void setDatestart(Date datestart) {
        this.dateStart = datestart;
    }

    public Date getDateend() {
        return dateEnd;
    }

    public void setDateend(Date dateend) {
        this.dateEnd = dateend;
    }

    public double getPlus() {
        return plus;
    }

    public void setPlus(double plus) {
        this.plus = plus;
    }
    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public double getMinus() {
        return minus;
    }

    public void setMinus(double minus) {
        this.minus = minus;
    }

    public double getUltimovalue() {
        return ultimoValue;
    }

    public void setUltimovalue(double ultimovalue) {
        this.ultimoValue = ultimovalue;
    }

    public double getPrimoprice() {
        return primoPrice;
    }

    public void setPrimoprice(double primoprice) {
        this.primoPrice = primoprice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    @Override
    public boolean equals(Object other) {
        Invoice o = (Invoice) other;
        double min = 0.1;
        boolean payedBool = o.payed >= payed - min || o.payed <= payed + min;
        boolean plusBool = o.plus >= plus - min || o.plus <= plus + min;
        boolean minusBool = o.minus >= minus - min || o.minus <= minus + min;
        boolean ultimoBool = o.ultimoValue >= ultimoValue - min || o.ultimoValue <= ultimoValue + min;
        boolean primoBool = o.primoPrice >= primoPrice - min || o.primoPrice <= primoPrice + min;
        return o.number == number && o.dateStart.equals(dateStart) && o.dateEnd.equals(dateEnd)
                && plusBool && minusBool && ultimoBool && primoBool &&
                o.details.equals(details) && payedBool;
    }
}
