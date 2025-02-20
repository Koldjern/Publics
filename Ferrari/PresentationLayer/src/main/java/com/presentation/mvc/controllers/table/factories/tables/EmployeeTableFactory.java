package com.presentation.mvc.controllers.table.factories.tables;

import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
//anders not used
public class EmployeeTableFactory extends NodeFactory<EmployeeModel> {
    @Override
    public Node createNode(CellController<EmployeeModel> controller) {
        VBox box = new VBox();
        GuiTable<EmployeeModel> table = new EmployeeTable();
        table = TableFactory.readyTable(controller, ServiceType.Employee, table);
        table.setup(box);
        return table;
    }
}
