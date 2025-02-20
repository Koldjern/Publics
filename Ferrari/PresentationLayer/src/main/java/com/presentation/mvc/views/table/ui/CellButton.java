package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.controllers.table.CellController;
import javafx.scene.control.Button;
//anders
//button in the cells, sets its action to cell because it inherits the eventhandler interface
public class CellButton<T> extends Button {
    public CellButton(CellController<T> cell, String text) {
        super(text);
        getStyleClass().add("cellButton");
        setOnAction(cell);
    }
}
