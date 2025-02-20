package com.presentation.tools.facade;

import com.presentation.mvc.controllers.Controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BorderPaneAdapter implements ViewSetup{
    private BorderPane mainPane;
    private Controller center;
    private Controller top;
    private Controller left;
    private Controller right;
    private Controller bottom;

    public BorderPaneAdapter(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
    @Override
    public void setCenter(Controller c) {
        if(!ready(center))
            return;
        center = c;
        mainPane.setCenter(c != null ? c.getView() : new HBox());
    }

    @Override
    public void setTop(Controller c) {
        if(!ready(top))
            return;
        top = c;
        mainPane.setTop(c != null ? c.getView() : new HBox());
    }

    @Override
    public void setLeft(Controller c) {
        if(!ready(left))
            return;
        left = c;
        mainPane.setLeft(c != null ? c.getView() : new HBox());
    }

    @Override
    public void setRight(Controller c) {
        if(!ready(right))
            return;
        right = c;
        mainPane.setRight(c != null ? c.getView() : new HBox());
    }
    @Override
    public void setBottom(Controller c) {
        if(!ready(bottom))
            return;
        bottom = c;
        mainPane.setBottom(c != null ? c.getView() : new HBox());
    }
    private boolean ready(Controller c) {
        if(mainPane == null)
            return false;
        if(c != null)
            c.unbindAll();
        return true;
    }
}
