package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.ui.CellCheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
//anders
public class CheckboxFactory<T> extends NodeFactory<T>{

    @Override
    public Node createNode(CellController<T> cell) {
        //makes a property which column will listen to, so it can check if button should be disabled
        BooleanProperty booleanProperty = new SimpleBooleanProperty();
        booleanProperty.addListener(getController());
        //adds a listener to when the items in the row change, because if several checkboxes exist they have to be reset upon one call
        cell.itemProperty().addListener(new ChangeListener<RowModel<T>>() {
            @Override
            public void changed(ObservableValue<? extends RowModel<T>> observable, RowModel<T> oldValue, RowModel<T> newValue) {
                if(oldValue != null)
                    booleanProperty.unbindBidirectional(oldValue.getProperty(getController().getNr()));
                if(newValue != null) {
                    booleanProperty.set(newValue.getProperty(getController().getNr()).get());
                    booleanProperty.bindBidirectional(newValue.getProperty(getController().getNr()));
                }
            }
        });
        //Adds cells to the column button
        getController().getButton().addEventHandler(ActionEvent.ACTION, cell);
        cell.setBooleanProperty(booleanProperty);
        return new CellCheckBox(getController().getRowText(), booleanProperty);
    }

}
