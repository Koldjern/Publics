package com.presentation.tools.facade;

import com.presentation.mvc.controllers.Controller;

//anders
//methods signatures fit with borderpanes main actions
public interface ViewSetup {
    public void setCenter(Controller c);
    public void setTop(Controller c);
    public void setLeft(Controller c);
    public void setRight(Controller c);
    public void setBottom(Controller c);

}
