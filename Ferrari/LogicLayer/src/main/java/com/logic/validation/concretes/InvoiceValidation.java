package com.logic.validation.concretes;
import com.data.actions.specifics.CheckData;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Invoice;
//karl
//invoice validation der tjekker forskellige ting ved invoice
public class InvoiceValidation extends HandlerObject {
    private CheckData data;
    public InvoiceValidation(CheckData data) {
        this.data = data;
    }
    public InvoiceValidation(HandlerObject handler) {
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Invoice;
    }
    @Override
    //validation af faktura/ invoice
    protected void action(Request request) {
    Invoice invoice = (Invoice) request.getObject();
    if(invoice.getDetails().length() < 20)
        request.getValidation().addMessage("Detaljer er for kort, minimum 20 bogstaver");
    if(String.valueOf(invoice.getNumber()).length() < 0)
        request.getValidation().addMessage("Der skal helst betales lidt mere end 0kr");
    if(invoice.getDatestart() == null) {
        request.getValidation().addMessage("skal bruge start dato");}
    if(invoice.getDateend() == null) {
        request.getValidation().addMessage("skal bruge start dato.");}
    if(invoice.getDatestart() != null && invoice.getDateend() != null) {
    if(invoice.getDateend().before(invoice.getDatestart())) {
    request.getValidation().addMessage("det kan ikke ende før det er startet.");}
        } 
    }
}
