package dc2_3.ui;

import dc2_3.setting.Setting;
import dc2_3.setting.SupportedSettings;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO FontTypeとFontTypeのデフォルト値をSettingから反映する
 */
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

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Font Type & Size", createLayoutForFontTab());
        tabbedPane.add("Font Color", createLayoutForFontColorTab());
        tabbedPane.add("Background Color", createLayoutForBgColorTab());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        initLayoutForButtonPane();
    }

    private void setListeners() {
        okButton.addActionListener(OK);
        cancelButton.addActionListener(e -> {
            dispose();
        });
    }

    private JPanel createLayoutForFontTab() {
        JPanel fontPanel = new JPanel();
        fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.Y_AXIS));

        JPanel fontTypePanel = new JPanel();
        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder fontTypeBorder = BorderFactory.createTitledBorder(lineBorder, "Font Type");
        fontTypePanel.setBorder(fontTypeBorder);
        fontTypeComboBox = new JComboBox<>();
        for (String fontTypeName : SupportedSettings.FONT_TYPE_LIST) {
            fontTypeComboBox.addItem(fontTypeName);
        }
        fontTypeComboBox.setSelectedIndex(Setting.getInstance().getSelectedFontTypeIndex());
        fontTypePanel.add(fontTypeComboBox);


        JPanel fontSizePanel = new JPanel();
        TitledBorder fontSizeBorder = BorderFactory.createTitledBorder(lineBorder, "Font Size");
        fontSizePanel.setBorder(fontSizeBorder);
        JLabel fontSizeLabel = new JLabel("Please input font size : ");
        fontSizeSpinnerModel = new SpinnerNumberModel();
        fontSizeSpinner = new JSpinner(fontSizeSpinnerModel);
        fontSizeSpinnerModel.setValue(Setting.getInstance().getCurrentFont().getSize());
        fontSizeSpinner.setPreferredSize(new Dimension(100, 20));
        fontSizePanel.add(fontSizeLabel);
        fontSizePanel.add(fontSizeSpinner);

        fontPanel.add(fontTypePanel);
        fontPanel.add(fontSizePanel);
        return fontPanel;
    }

    private JPanel createLayoutForFontColorTab() {
        JPanel fontColorPanel = new JPanel();
        fontColorChooser = new JColorChooser(Setting.getInstance().getFontColor());
        fontColorPanel.setLayout(new BorderLayout());
        fontColorPanel.add(fontColorChooser, BorderLayout.CENTER);
        JPanel headerFontPanel = new JPanel();
        fontColorNameLabel = new JLabel("Current Color : ");
        fontColorTip = new JPanel();
        fontColorTip.setBorder(new BevelBorder(BevelBorder.LOWERED));
        fontColorTip.setBackground(Setting.getInstance().getFontColor());
        headerFontPanel.add(fontColorNameLabel);
        headerFontPanel.add(fontColorTip);
        fontColorPanel.add(headerFontPanel, BorderLayout.NORTH);
        return fontColorPanel;
    }

    private JPanel createLayoutForBgColorTab() {
        JPanel bgColorPanel = new JPanel();
        bgColorChooser = new JColorChooser(Setting.getInstance().getBgColor());
        bgColorPanel.setLayout(new BorderLayout());
        bgColorPanel.add(bgColorChooser, BorderLayout.CENTER);
        JPanel headerBgPanel = new JPanel();
        bgColorNameLabel = new JLabel("Current Color : ");
        bgColorTip = new JPanel();
        bgColorTip.setBorder(new BevelBorder(BevelBorder.LOWERED));
        bgColorTip.setBackground(Setting.getInstance().getBgColor());
        headerBgPanel.add(bgColorNameLabel);
        headerBgPanel.add(bgColorTip);
        bgColorPanel.add(headerBgPanel, BorderLayout.NORTH);
        return bgColorPanel;
    }

    private void initLayoutForButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            okButton = new JButton("OK");
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
        {
            cancelButton = new JButton("Cancel");
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }


    public static void main(String[] args) {
        PropertyDialog dialog = new PropertyDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
