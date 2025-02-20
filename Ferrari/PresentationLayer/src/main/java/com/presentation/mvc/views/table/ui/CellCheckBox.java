package com.presentation.mvc.views.table.ui;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
//anders
//checkbox in cells, binds to the given boolean property, which comes from the column
public class CellCheckBox extends CheckBox {
    public CellCheckBox(String text, BooleanProperty property) {
        super(text);
        getStyleClass().add("cellCheck");
        selectedProperty().bindBidirectional(property);
    }
}
