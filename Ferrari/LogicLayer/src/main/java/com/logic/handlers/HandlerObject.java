package com.logic.handlers;
//anders
public abstract class HandlerObject implements Handler {
    private int order;
    protected HandlerObject next;
    protected HandlerObject before;
    private HandlerHolder holder;
    public void query(Request request) {
        if(check(request)) {
            MoveForward();
            action(request);
        }
        else if(next != null)
            next.query(request);
    }
    //can add at any part of the "chain"
    public void addHandler(HandlerObject handler) {
        HandlerObject last = next;
        if (last == null) {
            setNext(handler);
            return;
        }
        while(last.next != null)
            last = last.next;
        last.setNext(handler);
    }
    protected abstract boolean check(Request request);
    protected abstract void action(Request request);
    private void MoveForward() {
        order++;
        if(before == null || before.order >= order)
            return;
        HandlerObject spot = before;
        while (spot.before != null && spot.before.order < order)
            spot = spot.before;
        switchSpot(spot);
        if(before == null)
            holder.setRoot(this);
    }
    private void switchSpot(HandlerObject spot) {
        // connects last spots nodes with each other
        if(next != null)
            next.before = before;
        before.next = next;
        // sets this to its spot
        before = spot.before;
        if(before != null)
            before.next = this;
        spot.before = this;
        next = spot;
    }
    public void setNext(HandlerObject other) {
        next = other;
        other.before = this;
    }
    public void setHolder(HandlerHolder holder) {
        this.holder = holder;
    }

}
