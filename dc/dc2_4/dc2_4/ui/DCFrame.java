package dc2_4.ui;

import dc2_4.setting.DefaultProperties;
import dc2_4.ui.dialog.PropertyDialog;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * TODO メニューをAWTにしない。
 */
public class DCFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private DCPanel dcPanel;

    private ActionListener GO_PROPERTY_SETTING = e -> {
        PropertyDialog dialog = new PropertyDialog();
        dialog.setVisible(true);
    };

    DCFrame() {
        super("Digital Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(DefaultProperties.WINDOW_WIDTH, DefaultProperties.WINDOW_HEIGHT);
        dcPanel = new DCPanel(this);
        setVisible(true);
        getContentPane().add(dcPanel);
        setLayouts();
        setListeners();
    }

    private void setLayouts() {
        menuBar = new JMenuBar();
        menu = new JMenu("Setting");
        menuItem = new JMenuItem("Property");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void setListeners() {
        menuItem.addActionListener(GO_PROPERTY_SETTING);
    }


    /**
     * アプリケーションを実行
     */
    private void exec() {
        Timer timer = new Timer(1000, e -> {
            //setSize(dcPanel.getCalFrameSize());
            repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        new DCFrame().exec();
    }

}
