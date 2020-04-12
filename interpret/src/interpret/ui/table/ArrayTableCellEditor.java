package interpret.ui.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ArrayTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JComponent component = null;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        component = (JComponent) value;
        return component;
    }

    @Override
    public Object getCellEditorValue() {
        return component;
    }
}
