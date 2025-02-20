package com.presentation.mvc.views;

import java.util.List;
import com.model.Pair;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class ViewHBox extends HBox implements View{
    private ObserverHandler handler = new ObserverHandler();
    @Override
    public Pane getPane() {
        return this;
    }
    @Override
    public void addListener(Pair<Property, ChangeListener> listener) {
        handler.addListener(listener);
    }

    @Override
    public void addProperties(List<Property> props) {
        handler.addProperties(props);
    }
    @Override
    public void unbindAll() {
        handler.unbindAll();
    }
}
