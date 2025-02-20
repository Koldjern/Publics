package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.models.table.RowModel;
import javafx.scene.control.TableColumn;
//anders
//column for GUI thats why rowmodel in the column value, so it can get everything
public class GuiColumn<T> extends TableColumn<RowModel<T>, RowModel<T>> {
    //text for cells
    private String rowsText;
    public GuiColumn(String text) {
        setText(text);
    }
    public String getRowsText() {
        return rowsText;
    }
    public void setRowsText(String rowsText) {
        this.rowsText = rowsText;
    }

}
