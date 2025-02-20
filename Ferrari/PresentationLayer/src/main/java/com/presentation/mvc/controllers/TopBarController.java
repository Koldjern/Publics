package com.presentation.mvc.controllers;

import com.presentation.mvc.views.View;
import com.presentation.mvc.views.topbar.TopbarView;

public class TopBarController extends Controller{
    private View view;
    public TopBarController() {
        view = new TopbarView();
    }

}
