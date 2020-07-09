package dc2_4.ui.dialog;

import dc2_4.setting.ColorListItem;
import dc2_4.setting.SupportedSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ColorSettingListSampleFrame extends JFrame {

    private JList<ColorListItem> jList;
    private JPanel panel;
    private DefaultListModel<ColorListItem> model = new DefaultListModel<>();

    ColorSettingListSampleFrame() {
        super();
        setBounds(0, 0, 300, 300);
        panel = new JPanel();
        initJList();
        panel.add(new JScrollPane(jList),
                BorderLayout.CENTER);
        add(panel);
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        ColorSettingListSampleFrame frame = new ColorSettingListSampleFrame();
        frame.setVisible(true);
    }

    private void initJList() {
        jList = new JList<>(model);
        for (Map.Entry<String, Color> entry : SupportedSettings.SUPPORTED_COLOR.entrySet()) {
            model.addElement(new ColorListItem(entry.getValue(), entry.getKey()));
        }
        jList.setCellRenderer(new ColorSettingItemPanelRenderer());
    }
}
