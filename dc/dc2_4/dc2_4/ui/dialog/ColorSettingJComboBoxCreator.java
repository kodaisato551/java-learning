package dc2_4.ui.dialog;

import dc2_4.setting.ColorListItem;
import dc2_4.setting.SupportedSettings;

import javax.swing.*;

public class ColorSettingJComboBoxCreator {

    public static JComboBox<ColorListItem> create() {
        DefaultComboBoxModel<ColorListItem> model = new DefaultComboBoxModel<>(SupportedSettings.COLOR_LIST_ITEMS.toArray(new ColorListItem[0]));
        JComboBox<ColorListItem> comboBox = new JComboBox<>(model);
        comboBox.setRenderer(new ColorSettingItemPanelRenderer());
        return comboBox;
    }
}
