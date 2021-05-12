import java.awt.*;
import java.util.Random;

public class Ball {
    private static final int SIZE = 8;
    private int x, y;//ball location
    private int vx, vy;//velocity of ball
    private Random rand;

    public Ball() {
        rand = new Random(System.currentTimeMillis());
        x = rand.nextInt(MainPanel.WIDTH - SIZE);
        y = 0;
        vx = 5;
        vy = 5;
    }

    /**
     * ボールを描画
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, SIZE, SIZE);
    }

    /**
     * ボールの移動
     *
     */
    public void move() {
        x += vx;
        y += vy;
        if (x < 0 || x > MainPanel.WIDTH - SIZE) {
            vx = -vx;
        }
        if (y < 0 || y > MainPanel.HEIGHT - SIZE) {
            vy = -vy;
        }
    }

    /**
     * Y軸方向にバウンド
     */
    public void boundY() {
        vy = -vy;
    }

    public static int getSize() {
        return SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
