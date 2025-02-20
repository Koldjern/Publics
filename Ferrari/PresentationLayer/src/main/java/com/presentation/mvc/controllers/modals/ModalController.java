package com.presentation.mvc.controllers.modals;

import com.presentation.mvc.controllers.Controller;
import javafx.stage.Stage;
//anders
//modal can get a result and close the modal stage
public abstract class ModalController<T> extends Controller{
    private Stage stage;
    private T result;

    public void close() {
        stage.close();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
