package dc2_4.ui.dialog;

import dc2_4.setting.Setting;
import dc2_4.setting.SupportedSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PropertyDialog extends JDialog {


    private JTabbedPane tabbedPane;

    //fontType
    private JComboBox<String> fontTypeComboBox;

    //fontSize
    private JSpinner fontSizeSpinner;
    private SpinnerNumberModel fontSizeSpinnerModel;

    //fontColor
    private JColorChooser fontColorChooser;
    private JLabel fontColorNameLabel;
    private JPanel fontColorTip;

    //backgroundColor
    private JColorChooser bgColorChooser;
    private JLabel bgColorNameLabel;
    private JPanel bgColorTip;

    //OKButton
    private JButton okButton;

    //cancelButton
    private JButton cancelButton;

    private final ActionListener OK = e -> {
        updateSettingForFontTypeAndSize();
        updateSettingForColor();
        dispose();
    };


    public PropertyDialog() {
        setLayouts();
        setListeners();
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
        Setting.getInstance().setFontColor(fontColorChooser.getColor());
        Setting.getInstance().setBgColor(bgColorChooser.getColor());
    }

    private void setLayouts() {
        setBounds(100, 100, 600, 500);
        setTitle("Property Dialog");
        setLayout(new GridBagLayout());


    }

    private void setListeners() {

    }


    public static void main(String[] args) {
        PropertyDialog dialog = new PropertyDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
