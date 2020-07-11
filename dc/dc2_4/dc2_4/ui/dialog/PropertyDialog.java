package dc2_4.ui.dialog;

import dc2_4.setting.ColorListItem;
import dc2_4.setting.Setting;
import dc2_4.setting.SupportedSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO color のデフォルトを設定されている状態にする
 * TODO SettingのcolorのselectedIndexをする
 * TODO　OK,Cancelを追加
 */
public class PropertyDialog extends JDialog {

    //layout
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel mainPanel = new JPanel();//This panel include Setting item such font type.
    private JPanel footerPanel = new JPanel();//This panel include Ok and Cancel button.

    //fontType
    private JComboBox<String> fontTypeComboBox;

    //fontSize
    private JSpinner fontSizeSpinner;
    private SpinnerNumberModel fontSizeSpinnerModel;

    //fontColor
    private JComboBox<ColorListItem> fontColorJComboBox;

    //backgroundColor
    private JComboBox<ColorListItem> bgColorJComboBox;
    private final ActionListener OK = e -> {
        updateSettingForFontTypeAndSize();
        updateSettingForColor();
        dispose();
    };
    private JLabel bgColorNameLabel;
    private JPanel bgColorTip;
    //OKButton
    private JButton okButton;
    //cancelButton
    private JButton cancelButton;


    public PropertyDialog() {
        setLayouts();
        setListeners();
    }

    public static void main(String[] args) {
        PropertyDialog dialog = new PropertyDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    /**
     * SettingのFontTypeとFontSizeの情報を更新する
     */
    private void updateSettingForFontTypeAndSize() {
        int index = fontTypeComboBox.getSelectedIndex();
        String fontType = SupportedSettings.FONT_TYPE_LIST.get(index);
        Setting.getInstance().setSelectedFontTypeIndex(index);
        int fontSize = (Integer) fontSizeSpinner.getValue();
        Setting.getInstance().setFont(fontType, fontSize);
    }

    /**
     * SettingのfontColorとbgColorの情報を更新する
     */
    private void updateSettingForColor() {
        ColorListItem fontColorItem = (ColorListItem) fontColorJComboBox.getSelectedItem();
        ColorListItem bgColorItem = (ColorListItem) bgColorJComboBox.getSelectedItem();

        Setting.getInstance().setFontColor(fontColorItem.getColor());
        Setting.getInstance().setBgColor(bgColorItem.getColor());
    }

    private void setLayouts() {
        setBounds(100, 100, 400, 400);
        setTitle("Property Dialog");
        setLayout(new BorderLayout());

        mainPanel.setLayout(layout);
        //Font type
        fontTypeComboBox = new JComboBox<>();
        for (String fontTypeName : SupportedSettings.FONT_TYPE_LIST) {
            fontTypeComboBox.addItem(fontTypeName);
        }
        fontTypeComboBox.setSelectedIndex(Setting.getInstance().getSelectedFontTypeIndex());
        setComponent(fontTypeComboBox, 0, 0);
        setComponent(new JLabel(" Font type"), 1, 0);

        //Font Size
        fontSizeSpinnerModel = new SpinnerNumberModel();
        fontSizeSpinner = new JSpinner(fontSizeSpinnerModel);
        fontSizeSpinnerModel.setValue(Setting.getInstance().getCurrentFont().getSize());
        fontSizeSpinner.setPreferredSize(new Dimension(100, 20));
        setComponent(fontSizeSpinner, 0, 1);
        setComponent(new JLabel(" Font Size"), 1, 1);

        //Font Color
        fontColorJComboBox = ColorSettingJComboBoxCreator.create();
//        fontColorJComboBox.setSelectedIndex(Setting.getInstance().getSelectedFontColorIndex());
        setComponent(fontColorJComboBox, 0, 2);
        setComponent(new JLabel(" Font color"), 1, 2);

        //Bg Color
        bgColorJComboBox = ColorSettingJComboBoxCreator.create();
//        bgColorJComboBox.setSelectedIndex(Setting.getInstance().getSelectedBgColorIndex());
        setComponent(bgColorJComboBox, 0, 3);
        setComponent(new JLabel(" BackGround color"), 1, 3);

        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

    }

    /**
     * GridBagレイアウトでmainPanelに渡されたcomponentを追加する
     *
     * @param component 　added component
     * @param x         added location with x-axis
     * @param y         added location with y-axis
     */
    private void setComponent(Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        layout.setConstraints(component, gbc);
        mainPanel.add(component);
    }

    private void setListeners() {

    }
}
