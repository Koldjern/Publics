package com.presentation.mvc.models.table;

import com.logic.services.enums.ServiceType;
import javafx.collections.ObservableList;
import java.util.List;
//anders
public class TableModel <T>{
    private ObservableList<RowModel<T>> rows;
    private ServiceType type;
    public TableModel(ServiceType type, List<T> objects) {
        this.type = type;
        this.rows = RowModel.makeRowModels(type, objects);
    }
    public TableModel(ServiceType type, T[] objects) {
        this.type = type;
        this.rows = RowModel.makeRowModels(type, objects);
    }
    public ServiceType getType() {
        return type;
    }
    public void removeRow(RowModel<T> row) {
        rows.remove(row);
    }
    public void addRow(RowModel<T> row) {
        rows.add(row);
    }
    public void removeAllRows() {
        rows.removeAll(rows);
    }
    public void addAllRows(List<RowModel<T>> addRows) {
        rows.addAll(addRows);
    }
    public ObservableList<RowModel<T>> getRows() {
        return rows;
    }
}
