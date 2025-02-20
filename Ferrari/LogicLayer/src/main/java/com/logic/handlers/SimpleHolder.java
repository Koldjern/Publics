package com.logic.handlers;
//anders
public class SimpleHolder extends HandlerHolder {
    public SimpleHolder(HandlerObject... handlers) {
        super(handlers);
    }

    @Override
    public void query(Request request) {
        root.query(request);
    }
}
