package com.presentation.mvc.views.vehicle.modals;

import java.io.ByteArrayInputStream;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.ViewVBox;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
//jakob
public class VehicleView extends ViewVBox {
    public VehicleView(VehicleModel model) {
// Klassen opretter visning af vehicle og lytter hvis der er ændringer og opdatere View
        TextField name = new TextField(model.getName());
        model.nameProperty().bind(name.textProperty());

        TextField price = new TextField(String.valueOf(model.getPrice()));

        Bindings.bindBidirectional(price.textProperty(), model.priceProperty(), new NumberStringConverter());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        if(model.getImage() != null)
            imageView.setImage(new Image(new ByteArrayInputStream(model.getImage()), 0, 300, true, true));
        model.imageProperty().addListener(new ChangeListener<byte[]>() {
            @Override
            public void changed(ObservableValue<? extends byte[]> observable, byte[] oldValue, byte[] newValue) {
                Image img = new Image(new ByteArrayInputStream(newValue), 0, 300, true, true);
                imageView.setImage(img);
            }
        });
        getChildren().add(imageView);
        getChildren().addAll(
        new HBox(new Label("Billede:"), imageView,     
            new VBox(
                new HBox(new Label("Name:"), name),
                new HBox(new Label("Price:"), price)
            ))
    );
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}
