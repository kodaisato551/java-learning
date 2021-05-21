import clock.DCPanel;

import javax.swing.*;
import java.awt.*;

public class Executor extends JFrame {
    private static final int spacingSize = 4;
    private JPanel headerPanel = new JPanel(new GridLayout(1,spacingSize));
    private JPanel overallPane = new JPanel(new BorderLayout());
    private DCPanel dcPanel = new DCPanel();

    public Executor(){
        setTitle("DestroyBlockGame");
        setHeader();
        setResizable(false);
        MainPanel panel = new MainPanel();
        overallPane.add(headerPanel,BorderLayout.NORTH);
        overallPane.add(panel,BorderLayout.CENTER);
        getContentPane().add(overallPane);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setHeader(){
        for (int i = 0;i < spacingSize - 1; i++){
            JPanel spacing = new JPanel();
            spacing.setBackground(Color.LIGHT_GRAY);
            headerPanel.add(spacing);
        }
        headerPanel.add(dcPanel);
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
    public static void main(String[] args){
        new Executor().exec();
    }
}
