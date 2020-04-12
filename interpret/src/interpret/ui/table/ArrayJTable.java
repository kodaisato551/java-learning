package interpret.ui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * 配列の表を表す
 */
public class ArrayJTable extends JTable {

    private ArrayTableCellRenderer arrayTableCellRenderer = new ArrayTableCellRenderer();
    private ArrayTableCellEditor arrayTableCellEditor = new ArrayTableCellEditor();

    public ArrayJTable(DefaultTableModel defaultTableModel) {
        super(defaultTableModel);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer tableCellRenderer;
        Object object = this.getValueAt(row, column);

        if (object instanceof JComponent) {
            tableCellRenderer = arrayTableCellRenderer;
        } else {
            tableCellRenderer = super.getCellRenderer(row, column);
        }

        return tableCellRenderer;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor tableCellEditor;
        Object object = this.getValueAt(row, column);

        if (object instanceof JComponent) {
            tableCellEditor = arrayTableCellEditor;
        } else {
            tableCellEditor = super.getCellEditor(row, column);
        }

        return tableCellEditor;
    }
}
