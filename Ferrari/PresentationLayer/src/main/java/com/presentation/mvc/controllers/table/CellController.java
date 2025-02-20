package com.presentation.mvc.controllers.table;

import com.presentation.mvc.controllers.table.commands.CellCommand;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.models.table.RowModel;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
//anders
// node is the view and rowmodel is the model, the node will show if a model is present
public class CellController<T> extends TableCell<RowModel<T>, RowModel<T>> implements EventHandler<ActionEvent> {
    //graphic object in the cell
    private final Node node;
    private CellCommand<T> command;
    //booleanproperty to link the column with the checkbox
    private BooleanProperty booleanProperty;
    public CellController(NodeFactory<T> factory, CellCommand<T> command) {
        this.command = command;
        node = factory.createNode(this);
    }

    //mouse click event, checks booleans for if they were checked
    @Override
    public void handle(ActionEvent event) {
        if(getItem() == null || !getBoolean())
            return;
        command.invoke(getItem());
    }

    public Boolean getBoolean() {
        if (booleanProperty == null)
            return true;
        boolean bool = booleanProperty.get();
        booleanProperty.set(false);
        return bool;
    }
    @Override
    public void updateItem(RowModel<T> item, boolean empty) {
        super.updateItem(item, empty);
        if (empty)
            setGraphic(null);
        else
            setGraphic(node);
    };

    public void setBooleanProperty(BooleanProperty property) {
        booleanProperty = property;
    }
}
