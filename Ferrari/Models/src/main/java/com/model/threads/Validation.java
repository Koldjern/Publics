package com.model.threads;

import java.util.ArrayList;
//anders
//keeper of messages
public class Validation<T> {
    private ArrayList<String> messages;
    private ActionParameter<T> errorAction;


    public Validation() {
        messages = new ArrayList<>();
    }
    public Validation(ActionParameter<T> errorAction) {
        this();
        this.errorAction = errorAction;
    }
    public void addMessage(String message) {
        messages.add(message);
    }
    public ArrayList<String> getMessages() {
        return messages;
    }
    public ActionParameter<T> getErrorAction() {
        return errorAction;
    }
}
