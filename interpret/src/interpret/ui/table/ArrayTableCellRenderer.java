package interpret.ui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ArrayTableCellRenderer extends DefaultTableCellRenderer {

    // 行の高さ
    private int rowHeight = 0;

    public ArrayTableCellRenderer() {
        super();
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        JComponent component = (JComponent) value;

        // 一番高さのあるコンポーネントに合わせて行の高さを設定する。
        Dimension dimension = component.getPreferredSize();
        if (dimension.height > rowHeight) {
            table.setRowHeight(dimension.height);
            rowHeight = dimension.height;
        }

        return component;
    }
}
