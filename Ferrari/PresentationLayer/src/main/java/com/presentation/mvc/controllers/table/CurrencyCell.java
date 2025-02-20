package com.presentation.mvc.controllers.table;

import java.text.DecimalFormat;
import com.presentation.mvc.models.table.RowModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
//anders
//makes a currencycell, very simple just adds .KR
public class CurrencyCell<T> extends TableCell<RowModel<T>, Number> {
    private final DecimalFormat format = new DecimalFormat("#.00");
    @Override
    protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(format.format(item) + ".Kr");
        }
    }
    public static <T> Callback<TableColumn<RowModel<T>, Number>, TableCell<RowModel<T>, Number>> forTableColumn() {
        return (column) -> new CurrencyCell<T>();
    }
}
