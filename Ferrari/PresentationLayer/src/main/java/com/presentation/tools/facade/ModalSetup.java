package com.presentation.tools.facade;

import com.presentation.mvc.controllers.modals.ModalController;
//anders 
//wants a modalcontroller to open a modal view
public interface ModalSetup {
    public <T> void openModal(ModalController<T> controller);
    public <T> T openModalResult(ModalController<T> controller);

}
