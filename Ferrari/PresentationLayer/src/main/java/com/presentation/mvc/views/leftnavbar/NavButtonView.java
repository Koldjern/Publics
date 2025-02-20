package com.presentation.mvc.views.leftnavbar;

import org.controlsfx.glyphfont.Glyph;
import com.presentation.mvc.models.leftnavbar.NavButtonModel;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
//anders
//buttons in the navbar on the left of the screen, shows a text and a icon, gets a action from the controller so this is like a button
public class NavButtonView extends HBox {
    public NavButtonView(NavButtonModel model, EventHandler<MouseEvent> buttonAction) {
        super(new Label(model.getLabel()), new Glyph(model.getFontFamily(), model.getIcon()));
        getStyleClass().add("navButton");
        setOnMousePressed(buttonAction);
    }
}
