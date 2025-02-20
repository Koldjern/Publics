package com.presentation.mvc.views.topbar;

import com.presentation.App;
import com.presentation.mvc.views.ViewHBox;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//anders
public class TopbarView extends ViewHBox {
    //view for the top bar, holds a picture at the right
    public TopbarView() {
        Image png = new Image(App.class.getResource("FerrariMiniLogo.png").toString());
        ImageView image = new ImageView(png);
        Double h = png.getHeight();
        Double w = png.getWidth();
        image.setFitHeight(h * 0.5);
        image.setFitWidth(w * 0.5);
        getChildren().add(image);
        getStyleClass().add("topbar");

    }

    @Override
    public void addButtons(Button... buttons) {
    }

    @Override
    public void unbindAll() {
    }
}
