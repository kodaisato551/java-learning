import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private JPanel mOverall = new JPanel(new BorderLayout());
    private JPanel mHeader = new JPanel(new FlowLayout());
//    private JTextField mClockField = new JTextField();

    private DCPanel mDCPanel = new DCPanel();

    public UI() {
        mDCPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mOverall.add(mDCPanel, BorderLayout.NORTH);
        setVisible(true);
        setSize(DefaultSetting.DEFAULT_WINDOW_WIDTH, DefaultSetting.DEFAULT_WINDOW_HEIGHT);
        getContentPane().add(mOverall);
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
