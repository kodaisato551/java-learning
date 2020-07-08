package dc2_4.ui.dialog;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ColorPanel extends JPanel {
    private Color color;
    private String colorName;

    //ui
    private JPanel colorTip;

    ColorPanel(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
        setLayout(new FlowLayout());
        colorTip.setBorder(new BevelBorder(BevelBorder.LOWERED));
        colorTip.setBackground(color);
        add(colorTip);
        add(new JLabel(colorName));
    }
}
