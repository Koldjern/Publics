package com.model;

public class Pair<T,U> {
    private T t;
    private U u;

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }
    public T getFirst() {
        return t;
    }
    public U getSecond() {
        return u;
    }
    public void setFirst(T t) {
        this.t = t;
    }
    public void setSecond(U u) {
        this.u = u;
    }
}
