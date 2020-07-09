package dc2_4.ui.dialog;

import dc2_4.setting.ColorListItem;
import dc2_4.setting.SupportedSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ColorSettingJListCreator {

    public static JList<ColorListItem> create() {
        DefaultListModel<ColorListItem> model = new DefaultListModel<>();
        for (Map.Entry<String, Color> entry : SupportedSettings.SUPPORTED_COLOR.entrySet()) {
            model.addElement(new ColorListItem(entry.getValue(), entry.getKey()));
        }
        JList<ColorListItem> list = new JList<>(model);
        list.setCellRenderer(new ColorSettingItemPanelRenderer());
        return list;
    }
}
