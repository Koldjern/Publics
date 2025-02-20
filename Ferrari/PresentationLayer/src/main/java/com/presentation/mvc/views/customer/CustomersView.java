package com.presentation.mvc.views.customer;

import com.presentation.mvc.views.ViewVBox;
import com.presentation.mvc.views.generalgui.NiceHBox;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.control.Button;
//magnus
public class CustomersView extends ViewVBox {
    public CustomersView() {
        getStyleClass().add("Kunder");
    }
    public CustomersView(Button newUser) {
        getStyleClass().add("Kunder");
        getChildren().add(new NiceHBox("leftContainer", newUser));
    }
    public void setTable(GuiTable table) {
        table.setup(this);
    }
    @Override
    public void addButtons(Button... buttons) {
        
    }
    
}
