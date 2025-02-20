package com.presentation.mvc.views;

import java.util.List;

import com.model.Pair;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public interface View {
    public void addButtons(Button... buttons);
    public void unbindAll();
    public Pane getPane();
    public void addProperties(List<Property> props);
    public void addListener(Pair<Property, ChangeListener> listener);
}
