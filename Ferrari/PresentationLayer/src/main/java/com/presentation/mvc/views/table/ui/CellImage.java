package com.presentation.mvc.views.table.ui;

import java.io.ByteArrayInputStream;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//anders 
//sets images in the cells, set to 150 becuase images could get big
public class CellImage extends ImageView{
        public CellImage(ObjectProperty<byte[]> property) {
        getStyleClass().add("cellImage");
            setFitHeight(150);
            setPreserveRatio(true);
        property.addListener(new ChangeListener<byte[]>() {
            @Override
            public void changed(ObservableValue<? extends byte[]> observable, byte[] oldValue, byte[] newValue) {
                if(newValue != null)
                    imageProperty().set(new Image(new ByteArrayInputStream(newValue), 0, 150, true, true));
                else
                    imageProperty().set(null);
            }
        });
    }
}
