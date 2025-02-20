package com.presentation.mvc.controllers.leftnavbar;

import com.presentation.mvc.models.leftnavbar.NavButtonModel;
import com.presentation.mvc.views.leftnavbar.NavButtonView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
//anders
//setup the navbutton
public class NavButtonController {
    private NavButtonView view;

    public NavButtonController(NavButtonModel model, EventHandler<MouseEvent> buttonAction) {
        view = new NavButtonView(model, buttonAction);
    }
    public NavButtonView getView() {
        return view;
    }
}
