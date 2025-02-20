package com.presentation.mvc.views.agreement;

import com.presentation.mvc.views.ViewVBox;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.scene.control.Button;
//karl
//class that adds buttons to the view
public class AgreementView extends ViewVBox {
    public AgreementView(){
        getStyleClass().add("Aftaler");
    }
    @Override
    public void addButtons(Button... buttons) {
        getChildren().addAll(new NiceHBox("leftContainer", buttons));
    }
    @Override
    public void unbindAll() {
    }
}
