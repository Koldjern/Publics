package com.presentation.mvc.views.generalgui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
//anders
//just adds some nice to haves
public class NiceButton extends Button {
    public NiceButton(String text, String css, EventHandler<ActionEvent> clickEvent) {
        this(text, clickEvent);
        if(css.charAt(0) == 'I' && css.charAt(1) == 'D')
            setId(css);
        else
            getStyleClass().add(css);
    }
    public NiceButton(String text, EventHandler<ActionEvent> clickEvent) {
        super(text);
        setOnAction(clickEvent);
        getStyleClass().add("niceButton");
        
    }
}
