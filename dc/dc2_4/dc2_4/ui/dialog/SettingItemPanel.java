package dc2_4.ui.dialog;

import javax.swing.*;
import javax.swing.border.BevelBorder;
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
        defaultListModel = new DefaultListModel<>();
        for (T elem : supportedSettingList) {
            defaultListModel.addElement(elem);
        }
        supportedSettingJList = new JList<>();
        supportedSettingJList.setModel(defaultListModel);
        add(supportedSettingJList);
    }


    public static class ColorPanel extends JPanel {
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

    public static void main(String[] args) {

    }


}
