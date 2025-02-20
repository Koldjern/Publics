package com.orm;

public class ParamObject<T> {
    private String name;
    private T value;
    public ParamObject(String name) {
        this.name = name;
    }
    public ParamObject(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public T getValue() {
        return value;
    }
}
