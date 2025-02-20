package com.model.enums;
//anders
public enum Occupation {
    Manager("Chef"),
    Salesman("Sælger"),
    Admin("Administrator");
    private String title;
    private Occupation(String title) {
        this.title = title;
    }
    public String realString() {
        return name();
    }
    @Override
    public String toString() {
        return title;
    }
}

