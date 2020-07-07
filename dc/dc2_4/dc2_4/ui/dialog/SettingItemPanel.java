package dc2_4.ui.dialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 設定項目のパネル。
 * ダイアログで開くときの設定名と設定項目
 */
public class SettingItemPanel<T> extends JPanel {
    private String settingName;
    private List<T> supportedSettingList;

    //setting list
    private DefaultListModel<T> defaultListModel;
    private JList<T> supportedSettingJList;

    SettingItemPanel(String settingName, List<T> supportedSettingList) {
        this.settingName = settingName;
        this.supportedSettingList = supportedSettingList;
        setLayout(new FlowLayout());
        JLabel settingNameJLabel = new JLabel(settingName);
        add(settingNameJLabel);


        add(supportedSettingJList);

    }


}
