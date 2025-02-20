package com.presentation.mvc.controllers;

import com.presentation.mvc.views.View;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
//anders
//not for polymorphic use just to reuse code
public abstract class Controller {
    private View view;
    public void setView(View view) {
        this.view  = view;
    }
    public Pane getView() {
        return view.getPane();
    }
    public void addButtons(Button... buttons) {
        view.addButtons(buttons);
    }
    public void unbindAll() {
        view.unbindAll();
    }


}
