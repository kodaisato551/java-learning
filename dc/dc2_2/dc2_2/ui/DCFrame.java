package dc2_2.ui;

import dc2_2.setting.DefaultProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DCFrame extends JFrame {

    private MenuBar menuBar;
    private Menu menu;

    private ActionListener GO_PROPERTY_SETTING = e -> {

    };

    private DCFrame() {
        super("Digital Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(DefaultProperties.DEFAULT_WINDOW_WIDTH, DefaultProperties.DEFAULT_WINDOW_HEIGHT);
        DCPanel panel = new DCPanel();
        setVisible(true);
        getContentPane().add(panel);
        setLayouts();
    }

    private void setLayouts() {
        menuBar = new MenuBar();
        menu = new Menu("Property Setting");
        menuBar.add(menu);
        setMenuBar(menuBar);
    }

    private void setListeners() {
        menu.addActionListener(GO_PROPERTY_SETTING);
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
