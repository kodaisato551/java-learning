package dc2_1;

import javax.swing.*;

public class DCFrame extends JFrame {

    private DCFrame() {
        super("Digital Clock");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(DefaultSetting.DEFAULT_WINDOW_WIDTH, DefaultSetting.DEFAULT_WINDOW_HEIGHT);
        DCPanel panel = new DCPanel();
        setVisible(true);
        getContentPane().add(panel);
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
