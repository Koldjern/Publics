package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.ColumnController;
import javafx.scene.Node;
//anders
// abstract class to make gui
public abstract class NodeFactory<T> {
     private ColumnController<T> controller;

     public void setController(ColumnController<T> controller) {
          this.controller = controller;
     }

     public ColumnController<T> getController() {
          return controller;
     }
     //creates gui as implemented
     public abstract Node createNode(CellController<T> cell);

}
