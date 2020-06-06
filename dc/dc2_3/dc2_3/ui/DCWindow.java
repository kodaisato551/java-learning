package dc2_3.ui;

import dc2_3.setting.DefaultProperties;

import javax.swing.*;

public class DCWindow extends JWindow {
    private final DCPanel dcPanel;


    public DCWindow() {
        setSize(DefaultProperties.WINDOW_WIDTH, DefaultProperties.WINDOW_HEIGHT);
        dcPanel = new DCPanel(this);
        setVisible(true);
        getContentPane().add(dcPanel);
        setLayouts();
        setListeners();
    }

    private void setLayouts() {

    }

    private void setListeners() {
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
        new DCWindow().exec();
    }

}
