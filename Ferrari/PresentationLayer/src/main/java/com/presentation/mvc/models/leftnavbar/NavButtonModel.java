package com.presentation.mvc.models.leftnavbar;
//anders
//model for navbuttons, keep their values, they never change
public class NavButtonModel {
    private String fontFamily, icon, label;

    public NavButtonModel(String fontFamily, String icon, String label) {
        this.fontFamily = fontFamily;
        this.icon = icon;
        this.label = label;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public String getIcon() {
        return icon;
    }

    public String getLabel() {
        return label;
    }
}
