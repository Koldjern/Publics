package com.presentation.mvc.views.leftnavbar;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.List;
//anders
//the sidebar which holds all the buttons
public class LeftNavBarView extends VBox {
    public LeftNavBarView(List<NavButtonView> buttons) {
        super(new Label("Sider"));
        getStyleClass().add("navBar");
        getChildren().addAll(buttons);
    }
}
