package com.presentation.mvc.controllers.table;

import com.presentation.mvc.controllers.table.commands.CellCommand;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.ui.GuiColumn;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
//anders
//a little too beefy too many conditions, listen to checkboxes shouldnt be here if i had more time, makes the cells and holds the guicolumn 
public class ColumnController<T> implements Callback<TableColumn<RowModel<T>, RowModel<T>>, TableCell<RowModel<T>, RowModel<T>>>, ChangeListener<Boolean> {
    private int selected;
    private Button button;
    private NodeFactory<T> nodeFactory;
    private CellCommand<T> command;
    private final GuiColumn<T> view;
    private String rowText;
    private int nr;
    private ColumnController(String text, NodeFactory<T> nodeFactory, String rowsText) {
        this.nodeFactory = nodeFactory;
        nodeFactory.setController(this);
        view = new GuiColumn<T>(text);
        this.rowText = rowsText;
        view.setCellFactory(this);
        //can only implement one callback, so this is a dynamic object to keep it in the same class
        view.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel<T>, RowModel<T>>, ObservableValue<RowModel<T>>>() {
            @Override
            public ObservableValue<RowModel<T>> call(TableColumn.CellDataFeatures<RowModel<T>, RowModel<T>> column) {
                return new SimpleObjectProperty<RowModel<T>>(column.getValue());
            }
        });
    }
    //tableincell version constructor
    public ColumnController(NodeFactory<T> nodeFactory, String text) {
        this(text, nodeFactory, "");
    }
    //Image version constructor
    public ColumnController(NodeFactory<T> nodeFactory, String text, int nr) {
        this(text, nodeFactory, "");
        this.nr = nr;
    }
    //Button version constructor
    public ColumnController(NodeFactory<T> nodeFactory, String text, CellCommand<T> command, String rowsText) {
        this(text, nodeFactory, rowsText);
        this.command = command;
    }
    //checkbox version constructor
    public ColumnController(NodeFactory<T> nodeFactory, String rowsText, String text, CellCommand<T> command, Button button, int nr) {
        this(text, nodeFactory, rowsText);
        this.command = command;
        this.button = button;
        this.nr = nr;
    }
    @Override
    public TableCell<RowModel<T>, RowModel<T>> call(TableColumn<RowModel<T>, RowModel<T>> param) {
        return new CellController<T>(nodeFactory, command);
    }
    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
        selected += newValue ? 1 : -1;
        button.setDisable(!(selected > 0));
    }
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public GuiColumn<T> getView () {
        return view;
    }
    public int getNr() {
        return nr;
    }
    public NodeFactory<T> getFactory() {
        return nodeFactory;
    }
    public void setFactory(NodeFactory<T> nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    public String getRowText() {
        return rowText;
    }
}
