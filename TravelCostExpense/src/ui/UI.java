package ui;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private static final int spacingSize = 4;
    private JPanel mOverall = new JPanel(new BorderLayout());
    private JPanel mHeader = new JPanel(new GridLayout(1,spacingSize));
//    private JTextField mClockField = new JTextField();
    private DCPanel mDCPanel = new DCPanel();

    public UI() {
        setHeader();
        mOverall.add(mHeader, BorderLayout.NORTH);
        setVisible(true);
        setSize(DefaultSetting.DEFAULT_WINDOW_WIDTH, DefaultSetting.DEFAULT_WINDOW_HEIGHT);
        getContentPane().add(mOverall);
    }

    private void setHeader(){
        for (int i = 0;i < spacingSize - 1; i++){
            mHeader.add(new JPanel());
        }
        mHeader.add(mDCPanel);
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
        new UI().exec();
    }

}
