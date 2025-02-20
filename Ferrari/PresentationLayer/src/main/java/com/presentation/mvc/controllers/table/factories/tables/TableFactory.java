package com.presentation.mvc.controllers.table.factories.tables;

import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
//anders
//for child tables binds them to rowmodel observablelists
public class TableFactory {
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static GuiTable readyTable(CellController controller, ServiceType type, GuiTable table) {
        table.getStyleClass().add("tableInCell");
        controller.itemProperty().addListener(new ChangeListener<RowModel>() {
            @Override
            public void changed(ObservableValue<? extends RowModel> observable, RowModel oldValue, RowModel newValue) {
                if(newValue != null)
                    table.setItems(((TableModel)newValue.getItems().get(type)).getRows());
                else
                    table.setItems(null);

            }
        });
        return table;
    }
}
