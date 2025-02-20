package com.logic.handlers;
//anders
public abstract class HandlerHolder implements Handler {
    protected HandlerObject root;

    public HandlerHolder(HandlerObject... handlers) {
        for(HandlerObject handler : handlers) {
            addHandler(handler);
        }
    }

    public void setRoot(HandlerObject handler) {
        root = handler;
    }
    public HandlerObject getRoot() {
        return root;
    }

    public void addHandler(HandlerObject handler) {
        if (root == null)
            root = handler;
        else
            root.addHandler(handler);
        handler.setHolder(this);
    }
}
