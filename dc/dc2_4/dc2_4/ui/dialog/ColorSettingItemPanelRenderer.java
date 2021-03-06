package dc2_4.ui.dialog;

import dc2_4.setting.ColorListItem;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Color setting items used in Property dialog
 */
class ColorSettingItemPanelRenderer extends JPanel implements ListCellRenderer<ColorListItem> {
    //ui
    private JPanel colorTip;
    private JLabel colorLabel;

    public ColorSettingItemPanelRenderer() {
        colorTip = new JPanel();
        colorTip.setBorder(new BevelBorder(BevelBorder.LOWERED));
        colorLabel = new JLabel();
        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);
        add(colorTip);
        add(colorLabel);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ColorListItem> list, ColorListItem value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        colorTip.setBackground(value.getColor());
        colorLabel.setText(value.getColorName());
        if (isSelected) {
            setForeground(Color.red);
            setBackground(Color.cyan);
        } else {
            setForeground(Color.black);
            setBackground(Color.white);
        }
        return this;
    }
}
