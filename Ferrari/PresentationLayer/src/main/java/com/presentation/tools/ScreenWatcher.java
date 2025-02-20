package com.presentation.tools;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.stage.Screen;
import javafx.stage.Stage;
//anders
public class ScreenWatcher {
    private static ScreenWatcher instance;
    private Stage stage;
    //a little useless since most fx ui cant be forced to a size especially once loaded, setpref multiple time will sometimes not work, it will just lock on the first set
    private ObjectProperty<Screen> screenProperty;
    private ScreenWatcher() { screenProperty = new SimpleObjectProperty<>();
    }
    public static ScreenWatcher getInstance() {
        return instance == null ? instance = new ScreenWatcher() : instance;
    }
    public void setStage (Stage stage) {
        this.stage = stage;
        stage.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ObservableList<Screen> list = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
                if(list.isEmpty())
                    return;
                if(list.get(0) != screenProperty.get())
                    screenProperty.set(list.get(0));
            }
        });
    }
    public ObjectProperty<Screen> getScreenProperty() {
        return screenProperty;
    }

    public double getScreenHeightWithDecimal(double decimal) {
        // Get the height of the screen
        return getScreen().getBounds().getHeight() * decimal;
    }
    public double getScreenWidthWithDecimal(double decimal) {
        // Get the width of the screen
        return getScreen().getBounds().getWidth() * decimal;
    }
    public double getStageHeightWithDecimal(double decimal) {
        if(stage == null)
            return Screen.getPrimary().getBounds().getHeight() * decimal;
        double height = stage.getHeight() > 0 ? stage.getHeight() : Screen.getPrimary().getBounds().getHeight();
        return height * decimal;
    }
    private Screen getScreen() {
        ObservableList<Screen> list = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
        if(!list.isEmpty())
            return list.get(0);
        else
            return Screen.getPrimary();
    }
}
