import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements MouseMotionListener {
    private Racket racket;
    public static final int WIDTH = 360;
    public static final int HEIGHT = 480;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);
        // ラケットを作成
        racket = new Racket();
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(); // マウスのX座標
        racket.move(x); // ラケットを移動
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        racket.draw(g);
    }
}
