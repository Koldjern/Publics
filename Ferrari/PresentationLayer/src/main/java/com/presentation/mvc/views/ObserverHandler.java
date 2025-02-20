package com.presentation.mvc.views;

import java.util.ArrayList;
import java.util.List;

import com.model.Pair;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

public class ObserverHandler {
    private List<Property> properties;
    private List<Pair<Property, ChangeListener>> changes;

    public void addListener(Pair<Property, ChangeListener> listener) {
        if(changes == null)
            changes = new ArrayList<>();
        changes.add(listener);
    }

    public void addProperties(List<Property> props) {
        properties = new ArrayList<>(props);
    }
    public void unbindAll() {
        properties.forEach((x) -> x.unbind());
        changes.forEach((x) -> x.getFirst().removeListener(x.getSecond()));
    }
}
