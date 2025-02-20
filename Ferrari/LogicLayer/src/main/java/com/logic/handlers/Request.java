package com.logic.handlers;


import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.ActionParameter;
import com.model.threads.Validation;
//anders
public class Request <T, U> {
    private T object;
    private final CRUDType crud;
    private final ServiceType type;
    private ActionParameter<U> setter;
    private Validation<Request <T, U>> validation;
    public Request(ServiceType type, CRUDType crud) {
        this.crud = crud;
        this.type = type;
    }
    public Request(ServiceType type, CRUDType crud, T object) {
        this(type, crud);
        this.object = object;
    }
    public Request(ServiceType type, CRUDType crud, ActionParameter<U> setter) {
        this(type, crud);
        this.setter = setter;
    }
    public Request(ServiceType type, CRUDType crud, T object, ActionParameter<U> setter) {
        this(type, crud, object);
        this.setter = setter;
    }
    public Request(ServiceType type, CRUDType crud, T object, Validation<Request <T, U>> validation) {
        this(type, crud, object);
        this.validation = validation;
    }
    public Request(ServiceType type, CRUDType crud, T object, ActionParameter<U> setter, Validation<Request <T, U>> validation) {
        this(type, crud, object, setter);
        this.validation = validation;
    }

    public CRUDType getCrud() {
        return crud;
    }

    public ActionParameter<U> getSetter() {
        return setter;
    }

    public void setSetter(ActionParameter<U> action) {
        setter = action;
    }
    public ServiceType getType() {
        return type;
    }
    public T getObject() {
        return object;
    }
    public void setObject(T object) {
        this.object = object;
    }
    public Validation<Request <T, U>> getValidation() {
        return validation;
    }
    public boolean anyErrors() {
        return !validation.getMessages().isEmpty();
    }
}
