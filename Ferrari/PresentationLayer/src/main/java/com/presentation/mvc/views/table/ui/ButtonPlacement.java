package com.presentation.mvc.views.table.ui;

import javafx.scene.control.Button;
//anders
//int placement is for where it should be put above the table
//maybe it should be among controllers
public class ButtonPlacement extends Button {
    private int placement;
    // button for columns, set disabled as default
    public ButtonPlacement(String text, int placement) {
        super(text);
        getStyleClass().add("niceButton");
        this.placement = placement;
        setDisable(true);
    }
    public int getPlacement() {
        return placement;
    }
    public void setPlacement(int placement) {
        this.placement = placement;
    }
}
