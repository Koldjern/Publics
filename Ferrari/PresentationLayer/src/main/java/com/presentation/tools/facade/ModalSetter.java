package com.presentation.tools.facade;

import com.presentation.App;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.tools.ScreenWatcher;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//anders
public class ModalSetter implements ModalSetup {
    private Stage mainStage;
    private double x;
    private double y;

    public ModalSetter(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public <T> void openModal(ModalController<T> controller) {
        try {
            Stage stage = new Stage();
            controller.setStage(stage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(controller.getView(), Color.TRANSPARENT);
            scene.getStylesheets().add(App.class.getResource("stylesheet1.css").toExternalForm());
            controller.getView().getStyleClass().add("Modal");
            stage.setScene(scene);
            //sets watcher to watch the modal stage
            ScreenWatcher.getInstance().setStage(stage);
            stage.initOwner(mainStage);
            //to make the modal movable without its default window style
            //to get a draggin relevant to click start location
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });
            //sets its movement
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                }
            });
            stage.showAndWait();
            ScreenWatcher.getInstance().setStage(mainStage);

        } 
        catch (Exception e) {
        }
    }
    //openmodal stops the first stages thread, then the modal one closes its result has been set, so getresult gets the value
    public <T> T openModalResult(ModalController<T> controller) {
        try {
            openModal(controller);
            return controller.getResult();
        } 
        catch (Exception e) {
            return null;
        }
    }

}
