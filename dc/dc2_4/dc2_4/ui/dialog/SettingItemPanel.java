package dc2_4.ui.dialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);

        ColorPanel colorPanel = new ColorPanel(Color.BLACK, "black");
        ColorPanel colorPanel2 = new ColorPanel(Color.RED, "red");
        List<ColorPanel> list = new ArrayList<>();

        list.add(colorPanel);
        list.add(colorPanel2);
        SettingItemPanel<ColorPanel> panel = new SettingItemPanel<>("hoge", list);

        frame.add(panel);
    }


}
