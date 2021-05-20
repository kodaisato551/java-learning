import java.awt.*;

public class Ball {
    private static final int SIZE = 8;
    private int x;
    private int y;
    private int vx;
    private int vy;
    private boolean isGameOver;

    private final int blockBaseLineY;

    public Ball(int blockBaseLineY) {
        this.blockBaseLineY = blockBaseLineY;
        x = (MainPanel.WIDTH - SIZE) / 2;
//        y = (MainPanel.HEIGHT - Racket.HEIGHT - SIZE * 2);
        setBallLocation();
        y = (blockBaseLineY + MainPanel.HEIGHT)/2;
        vx = 5;
        vy = 5;
    }


    /**
     * ballの初期位置の計算
     */
    private void setBallLocation(){
        x = getRandInt(SIZE , MainPanel.WIDTH-SIZE);
    }

    private int getRandInt(int min, int max){
        return (int) (Math.random() * (max-min)) + min;
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

        if (y > MainPanel.HEIGHT - SIZE){
            isGameOver = true;
        }
        if (x < 0 || x > MainPanel.WIDTH - SIZE) {
            vx = -vx;
        }
        if (y < 0) {
            vy = -vy;
        }
    }

    /**
     * 斜めにバウンド
     */
    public void boundXY() {
        vx = -vx;
        vy = -vy;
    }

    /**
     * x軸方向にバウンド
     */
    public void boundX() {
        vx = -vx;
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

    public boolean isGameOver() {
        return isGameOver;
    }
}
