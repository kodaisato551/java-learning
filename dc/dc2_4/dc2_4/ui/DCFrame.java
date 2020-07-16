package dc2_4.ui;

import dc2_4.setting.DefaultProperties;
import dc2_4.setting.Setting;
import dc2_4.setting.SettingLoader;
import dc2_4.ui.dialog.PropertyDialog;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This clock represents Digital Clock.
 */
public class DCFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private DCPanel dcPanel;

    private static final Setting currentSetting = Setting.getInstance();

    private ActionListener GO_PROPERTY_SETTING = e -> {
        PropertyDialog dialog = new PropertyDialog();
        dialog.setVisible(true);
    };

    DCFrame() {
        super("Digital Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(DefaultProperties.WINDOW_WIDTH, DefaultProperties.WINDOW_HEIGHT);
        SettingLoader.loadPrefs();
        setLocation(currentSetting.getCurrentPoint());

        setLayouts();
        setListeners();
        addWindowCloseEvent();
        dcPanel = new DCPanel(this);
        getContentPane().add(dcPanel);
        setVisible(true);


    }

    public static void main(String[] args) {
        new DCFrame().exec();
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
     * windowがCloseするイベントを登録する
     */
    private void addWindowCloseEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                currentSetting.setCurrentPoint(getLocation());
                SettingLoader.savePrefs();
                System.exit(0);
            }
        });
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

}
