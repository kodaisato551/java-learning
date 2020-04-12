package interpret.ui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SampleFrame extends JFrame {
    private static final String HEADER_TITLE[] = {"Column01", "Column02", "Column03", "Column04", "Column05"};

    private DefaultTableModel defaultTableModel = new DefaultTableModel(HEADER_TITLE, 5);

    private JRadioButton radioButton = new JRadioButton("RadioButton");
    private JCheckBox checkBox = new JCheckBox("CheckBox");
    private JButton button = new JButton("Button");
    private JComboBox<String> comboBox = new JComboBox<String>();

    private JPanel buttonPanel = new JPanel();
    private JPanel comboBoxPanel = new JPanel();


    public static void main(String[] args) {
        SampleFrame sampleFrame = new SampleFrame("sample");
        sampleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        sampleFrame.setVisible(true);
    }


    public SampleFrame(String title) {
        super();
        this.setTitle(title);
        this.setBounds(10, 10, 470, 220);

        comboBox.addItem("item01");
        comboBox.addItem("item02");
        comboBox.addItem("item03");

        buttonPanel.add(button);
        comboBoxPanel.add(comboBox);

        defaultTableModel.setValueAt(radioButton, 0, 0);
        defaultTableModel.setValueAt(checkBox, 0, 1);
        defaultTableModel.setValueAt(buttonPanel, 0, 2);
        defaultTableModel.setValueAt(comboBoxPanel, 0, 3);
        defaultTableModel.setValueAt("あいうえお", 0, 4);


        ArrayJTable table = new ArrayJTable(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(450, 170));

        JPanel jPanel = new JPanel();
        jPanel.add(jScrollPane);

        this.add(jPanel, BorderLayout.CENTER);
    }


}
