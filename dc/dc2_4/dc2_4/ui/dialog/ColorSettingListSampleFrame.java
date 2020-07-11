package dc2_4.ui.dialog;

import dc2_4.setting.ColorListItem;

import javax.swing.*;
import java.awt.*;

public class ColorSettingListSampleFrame extends JFrame {

    private JComboBox<ColorListItem> colorListItemJComboBox;
    private JPanel panel;
    private DefaultListModel<ColorListItem> model = new DefaultListModel<>();

    private ColorSettingListSampleFrame() {
        super();
        setBounds(0, 0, 300, 300);
        panel = new JPanel();
        colorListItemJComboBox = ColorSettingJComboBoxCreator.create();
        panel.add(new JScrollPane(colorListItemJComboBox),
                BorderLayout.CENTER);
        add(panel);
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        ColorSettingListSampleFrame frame = new ColorSettingListSampleFrame();
        frame.setVisible(true);
    }
}
