package dc2_2.ui;

import dc2_2.setting.DefaultProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DCFrame extends JFrame {

    private MenuBar menuBar;
    private Menu menu;
    private MenuItem menuItem;

    private ActionListener GO_PROPERTY_SETTING = e -> {
        PropertyDialog dialog = new PropertyDialog();
        dialog.setVisible(true);
    };

    DCFrame() {
        super("Digital Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(DefaultProperties.WINDOW_WIDTH, DefaultProperties.WINDOW_HEIGHT);
        DCPanel panel = new DCPanel();
        setVisible(true);
        getContentPane().add(panel);
        setLayouts();
        setListeners();
    }

    private void setLayouts() {
        menuBar = new MenuBar();
        menu = new Menu("Setting");
        menuItem = new MenuItem("Property");
        menu.add(menuItem);
        menuBar.add(menu);
        setMenuBar(menuBar);
    }

    private void setListeners() {
        menuItem.addActionListener(GO_PROPERTY_SETTING);
    }


    /**
     * アプリケーションを実行
     */
    private void exec() {
        Timer timer = new Timer(1000, e -> {
            repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        new DCFrame().exec();
    }

}
