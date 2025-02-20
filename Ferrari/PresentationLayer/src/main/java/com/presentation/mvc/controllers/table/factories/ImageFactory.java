package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.ui.CellImage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
//anders
//binds model to cell image and show its byte[] as a image
public class ImageFactory<T> extends NodeFactory<T>{
    private String key;

    public ImageFactory(String key) {
        this.key = key;
    }
    @Override
    public Node createNode(CellController<T> cell) {
        ObjectProperty<byte[]> imageProperty = new SimpleObjectProperty<>();
        cell.itemProperty().addListener(new ChangeListener<RowModel<T>>() {
            @Override
            public void changed(ObservableValue<? extends RowModel<T>> observable, RowModel<T> oldValue, RowModel<T> newValue) {
                if(oldValue != null)
                    imageProperty.unbindBidirectional(oldValue.getImageProperty(key));
                if(newValue != null) {
                    imageProperty.set(newValue.getImageProperty(key).get());
                    imageProperty.bindBidirectional(newValue.getImageProperty(key));
                }
            }
        });
        return new CellImage(imageProperty);
    }

}