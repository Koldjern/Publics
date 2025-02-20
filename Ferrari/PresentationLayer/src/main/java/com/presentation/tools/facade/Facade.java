package com.presentation.tools.facade;

import com.model.entities.Employee;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.login.LoginController;
import com.presentation.mvc.controllers.modals.ModalController;
//anders
//Facade is for the frontend, sets gui and such using facade pattern, i does a little mediatoring at logoff tho
public class Facade implements ModalSetup, ViewSetup, LoginManager{
    private static Facade instance;
    private ViewSetup viewSetup;
    private ModalSetup modal;
    private LoginManager login;

    private Facade() {}
    public static Facade getInstance() {
        return instance == null ? instance = new Facade() : instance;
    }
    public void setLogin(LoginManager login) {
        this.login = login;
    }
    public void setMainPane(ViewSetup viewSetup) {
        this.viewSetup = viewSetup;
    }
    public void setModal(ModalSetup modal) {
        this.modal = modal;
    }
    @Override
    public <T>void openModal(ModalController<T> controller) {
        if(modal != null)
            modal.openModal(controller);
    }
    @Override
    public <T> T openModalResult(ModalController<T> controller) {
        if(modal != null)
            return modal.openModalResult(controller);
        return null;
    }

    @Override
    public void setCenter(Controller c) {
        if(viewSetup != null)
            viewSetup.setCenter(c);
    }

    @Override
    public void setTop(Controller c) {
        if(viewSetup != null)
            viewSetup.setTop(c);
    }

    @Override
    public void setLeft(Controller c) {
        if(viewSetup != null)
            viewSetup.setLeft(c);
    }

    @Override
    public void setRight(Controller c) {
        if(viewSetup != null)
            viewSetup.setRight(c);
    }
    @Override
    public void setBottom(Controller c) {
        if(viewSetup != null)
            viewSetup.setBottom(c);
    }

    @Override
    public void login(Employee employee) {
        if (login != null)
            login.login(employee);
    }

    @Override
    public Employee getLoggedIn() {
        if (login != null)
            return login.getLoggedIn();
        return null;
    }
    // does a little mediating using the gui setters and the login 
    public void logOff() {
        setCenter(new LoginController());
        setLeft(null);
        setRight(null);
        login(null);
    }
}
