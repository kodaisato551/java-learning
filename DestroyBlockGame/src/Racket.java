import java.awt.*;

public class Racket {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 5;
    private int centerPos;

    public Racket() {
        centerPos = MainPanel.WIDTH / 2;
    }

    /**
     * ラケットの描画
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT,
                WIDTH, HEIGHT);
    }

    /**
     * ラケットの移動
     *
     * @param pos 移動先座標
     */
    public void move(int pos) {
        centerPos = pos;
        if (centerPos < WIDTH / 2) {
            centerPos = WIDTH / 2;
        } else if (centerPos > MainPanel.WIDTH - WIDTH / 2) {
            centerPos = MainPanel.WIDTH - WIDTH / 2;
        }
    }

    /**
     * ボールとの当たり判定
     *
     * @return true when collide with ball.
     */
    public boolean collideWith(Ball ball) {
        // ラケットの矩形
        Rectangle racketRect = new Rectangle(
                centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT,
                WIDTH, HEIGHT);
        // ボールの矩形
        Rectangle ballRect = new Rectangle(
                ball.getX(), ball.getY(),
                Ball.getSize(), Ball.getSize());

        // ラケットとボールの矩形領域が重なったら当たっている
        if (racketRect.intersects(ballRect)) {
            return true;
        }

        return false;
    }

}
